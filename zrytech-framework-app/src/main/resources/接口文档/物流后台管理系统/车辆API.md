# 车辆 #

** 作者: CAT **

## 字段说明 ##

    带 * 的内容需要审核

    car
    
    id                  主键
    carNo               *（str）车牌号
    carLoad             *（int）车载量
    carUnit             *（str）车载量单位
    carUnitCN           *（str）车载量单位
    cartype             *（str）车辆类型
    cartypeCN           *（str）车辆类型
    mulStore            *（boolean）是否分仓
    storeQty            *（int）仓位数量

    driverId            （int）司机Id
    supercargoId        （int）压货人Id

    currentLongitude    （str）经度
    currentLatitude     （str）纬度
    currentAddress      （str）当前位置

    status              （str）车辆状态
    statusCN            （str）车辆状态

    createBy            （int）车主Id
    createDate          （str）创建日期
    isDelete            （boolean）是否删除

        
##状态##
    
    status      下架 --> 待审核 --> 上架
    cartype     待定
    carUnit     待定


<br>
<br>
<br>

# 车辆后台管理系统接口 #


## 1.车辆分页 ##

**URL**
>admin/car/page

**备注**

    状态等字段取值暂未确定。

**请求参数**

    {
    	"params": {
            "id":"",                  （选填）（int）车辆Id
            "createBy":"",            （选填）（int）车主Id
            "carNo": "",              （选填）（str）车牌号（采用迷糊搜索）
            "cartype": "",            （选填）（str）车辆类型
            "status": "",             （选填）（str）车辆状态
            "name": "",               （选填）（str）企业名称（采用迷糊搜索）
            "isDelete": ""            （选填）（boolean）删除标识【true | false】
    	},
        "page": {
    		"pageSize": "10",
    		"pageNum": "1"
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "total": 3,
            "list": [
                {
                    "id": 3,
                    "carNo": "京A10000",
                    "carLoad": 30,
                    "carUnit": "ton",
                    "carUnitCN": "车载量单位,待处理",
                    "carType": "trailer",
                    "carTypeCN": "车辆类型,待处理",
                    "driverId": 1,
                    "supercargoId": 2,
                    "currentLongitude": 3.123,
                    "currentLatitude": 5.111,
                    "currentAddress": "长沙",
                    "mulStore": false,
                    "storeQty": 1,
                    "status": "wait-check",
                    "statusCN": "车辆状态,待处理",
                    "isDelete": false,
                    "createBy": 1,
                    "createDate": "2019-02-18 15:09:21",
                    "carOwnerName": "车主张三",
                    "carOwner": null,                           // 请忽略该字段
                    "driverName": "司机李四",
                    "driver": null,                             // 请忽略该字段
                    "supercargoName": "压货人王五",
                    "supercargo": null                          // 请忽略该字段
                }
            ],
            "pageNo": 1,
            "pageSize": 1
        }
    }

<br>
<br>
<br>
    

## 2.车辆详情 ##

**URL**
>/admin/car/details

**备注**

    返回数据包含：
        1.车辆基本信息
        2.车主信息
        3.司机信息
        4.压货人信息

**请求参数**

    {
    	"params": {
            "id": ""              （必填）（int）车辆Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "id": 1,
            "carNo": "鄂A66666",
            "carLoad": 10,
            "carUnit": "ton",
            "carUnitCN": "车载量单位,待处理",
            "carType": "trailer",
            "carTypeCN": "车辆类型,待处理",
            "driverId": 1,
            "supercargoId": 2,
            "currentLongitude": 1.123,
            "currentLatitude": 3.111,
            "currentAddress": "武汉",
            "mulStore": false,
            "storeQty": 1,
            "status": "down",
            "statusCN": "车辆状态,待处理",
            "isDelete": false,
            "createBy": 1,
            "createDate": "2019-02-18 15:09:18",
            "carOwnerName": "车主张三",                 // 车主企业名称
            "carOwner": {                              // 车主详细信息
                "id": 1,
                "name": "车主张三",
                "creditCode": null,
                "businessLicense": null,
                "legalerName": null,
                "legalerIdCardNo": null,
                "legalerIdCardFront": null,
                "tel": null,
                "longitude": null,
                "latitude": null,
                "province": null,
                "city": null,
                "county": null,
                "addressDetail": null,
                "intro": null,
                "customerType": null,
                "avoidAudit": null,
                "closeRate": null,
                "favorableLevel": null,
                "status": null,
                "customerId": null,
                "createDate": null
            },
            "driverName": "司机李四",                   // 司机姓名
            "driver": {                                // 司机详细信息
                "id": 1,
                "name": "司机李四",
                "logo": null,
                "tel": null,
                "sex": null,
                "age": null,
                "idCard": null,
                "personType": null,
                "customerId": null,
                "drivingLicence": null,
                "status": null,
                "isDelete": null,
                "createBy": null,
                "createDate": null
            },
            "supercargoName": "压货人王五",             // 压货人姓名
            "supercargo": {                            // 压货人详细信息
                "id": 2,
                "name": "压货人王五",
                "logo": null,
                "tel": null,
                "sex": null,
                "age": null,
                "idCard": null,
                "personType": null,
                "customerId": null,
                "drivingLicence": null,
                "status": null,
                "isDelete": null,
                "createBy": null,
                "createDate": null
            }
        }
    }

<br>
<br>
<br>


## 3.车辆审核 ##

**URL**
>/admin/car/check

**备注**

    管理员审核车辆，车辆状态必须是待审核才可以操作车辆审核，审核结果为【 通过 | 不通过 】

**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）车辆Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果【pass | no_pass】
    	},
    	"token": ""
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": "成功"
    }


<br>
<br>
<br>



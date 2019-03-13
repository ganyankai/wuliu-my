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

	carOwnerId			（int）车主Id
    createBy            （int）创建人Id
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
            "carOwnerId":"",          （选填）（int）车主Id
            "name": "",               （选填）（str）车主企业名称（采用模糊搜索）
            "carNo": "",              （选填）（str）车牌号（采用模糊搜索）
            "carType": "",            （选填）（str）车辆类型
            "status": "",             （选填）（str）车辆状态
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
	        "total": 2,
	        "list": [
	            {
	                "id": 8,
	                "carNo": "鄂A20000",
	                "carLoad": 200,
	                "carUnit": "ton",
	                "carUnitCN": "车载量单位,待处理",
	                "carType": "car_type3",
	                "carTypeCN": "车辆类型,待处理",
	                "driverId": 1,
	                "supercargoId": 2,
	                "currentLongitude": null,
	                "currentLatitude": null,
	                "currentAddress": null,
	                "mulStore": false,
	                "storeQty": 1,
	                "status": "down",
	                "statusCN": "车辆状态,待处理",
	                "isDelete": false,
	                "createBy": 1,
	                "createDate": "2019-02-27 14:42:58",
	                "carOwnerName": "山东中石化科技有限公司",
	                "carOwner": null,
	                "driverName": "张",					// 司机姓名，可能为null
	                "driver": null,
	                "supercargoName": "张",				// 压货人姓名，可能为null
	                "supercargo": null,
	                "carOwnerId": 1
	            },
	        ],
	        "pageNo": 1,
	        "pageSize": 10
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
	        "carNo": "京A10000",
	        "carLoad": 1,
	        "carUnit": "ton",
	        "carUnitCN": "车载量单位,待处理",
	        "carType": "car_type1",
	        "carTypeCN": "车辆类型,待处理",
	        "driverId": 1,
	        "supercargoId": 2,
	        "currentLongitude": null,
	        "currentLatitude": null,
	        "currentAddress": null,
	        "mulStore": false,
	        "storeQty": 1,
	        "status": "wait_check",
	        "statusCN": "车辆状态,待处理",
	        "isDelete": false,
	        "createBy": 1,
	        "createDate": "2019-03-13 10:25:58",
	        "carOwnerName": "山东中石化科技有限公司",			【车主企业名称】
	        "carOwner": {									【车主信息】
	            "id": 1,
	            "name": "山东中石化科技有限公司",
	            "creditCode": "KDOO124657788X",
	            "businessLicense": "12",
	            "legalerName": "张敬录",
	            "legalerIdCardNo": "420922198011272852",
	            "legalerIdCardFront": "12",
	            "tel": "13163340532",
	            "longitude": 104.567,
	            "latitude": 234.123,
	            "province": "山东省",
	            "city": "济南",
	            "county": "安华县",
	            "addressDetail": "山东省济南市槐荫区",
	            "intro": "企业简介",
	            "customerType": "certification_organize",
	            "customerTypeCN": null,
	            "avoidAudit": false,
	            "closeRate": null,
	            "favorableLevel": null,
	            "status": "audit_pass",
	            "statusCN": null,
	            "customerId": 1,
	            "customer": null,
	            "createDate": "2019-03-06 14:11:06"
	        },
	        "driverName": "张",						【司机姓名，可能为null】
	        "driver": {								【司机信息，可能为null】
	            "id": 1,	
	            "name": "张",
	            "logo": null,
	            "tel": null,
	            "sex": null,
	            "age": null,
	            "idCard": null,
	            "personType": "driver",
	            "personTypeCN": "司机",
	            "customerId": null,
	            "drivingLicence": null,
	            "status": "wait_check",
	            "statusCN": "状态：待处理",
	            "isDelete": false,
	            "createBy": 1,
	            "createDate": null,
	            "carOwnerName": null,
	            "carOwner": null,
	            "carOwnerId": 1,
	            "isActive": null
	        },
	        "supercargoName": "张",					【压货人信息，可能为null】
	        "supercargo": {							【压货人信息，可能为null】
	            "id": 2,
	            "name": "张",
	            "logo": null,
	            "tel": "1",
	            "sex": null,
	            "age": null,
	            "idCard": null,
	            "personType": "supercargo",
	            "personTypeCN": "押货人",
	            "customerId": null,
	            "drivingLicence": null,
	            "status": "wait_check",
	            "statusCN": "状态：待处理",
	            "isDelete": false,
	            "createBy": null,
	            "createDate": "2019-02-26 10:17:01",
	            "carOwnerName": null,
	            "carOwner": null,
	            "carOwnerId": 1,
	            "isActive": null
	        },
	        "carOwnerId": 1
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
	    "data": "审核成功"
    }


<br>
<br>
<br>



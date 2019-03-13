# 前台 - 车辆车主端接口 #

**作者：CAT**


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

    carOwnerId          （int）车主Id 
    createBy            （int）创建人Id
    createDate          （str）创建日期
    isDelete            （boolean）是否删除


    1.车载量单位枚举待定
    2.车辆类型枚举待定
    

<br>
<br>
<br>


## 1.车主或车主子账号 - 添加车辆 ##

**备注**


**URL**
>/api/car/add 

**请求参数**

    {
    	"params": {
    		"carNo": "",              （必填）（str），车牌号
            "carLoad": "",            （必填）（int），核载量
            "carUnit": "",            （必填）（str），核载量单位
            "carType": "",            （必填）（str），车辆类型
            "mulStore": "",           （必填）（boolean），是否分仓 [true/false]
            "storeQty": ""            （必填）（int），仓位数量
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "添加成功"
    }

<br>
<br>
<br>


## 2.车主或车主子账号 - 查看车辆分页 ##

**备注**

    1.按照创建时间倒序
    2.查询当前登录账号车主的、未删除的车辆
    3.暂定以下过滤条件
    4.暂定以下返回值

**URL**
>/api/car/page 

**请求参数**

    {
    	"params": {
    		"carNo": "",              （选填）（str），车牌号（模糊搜索）
            "carType": "",            （选填）（str），车辆类型
            "status": "",             （选填）（str），车辆状态
            "createBy": ""            （选填）（int），创建人Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "total": 1,
            "list": [
                {
                    "id": 7,
                    "carNo": "鄂A10000",
                    "carLoad": 100,
                    "carUnit": "ton",
                    "carUnitCN": "车载量单位,待处理",
                    "carType": "car_type",
                    "carTypeCN": "车辆类型,待处理",
                    "driverId": null,
                    "supercargoId": null,
                    "currentLongitude": null,
                    "currentLatitude": null,
                    "currentAddress": null,
                    "mulStore": true,
                    "storeQty": 2,
                    "status": "down",
                    "statusCN": "车辆状态,待处理",
                    "isDelete": false,
                    "createBy": 1,
                    "createDate": "2019-02-27 14:14:07",
                    "carOwnerName": null,
                    "carOwner": null,
                    "driverName": null,                 // 司机姓名
                    "driver": null,
                    "supercargoName": null,             // 压货人姓名
                    "supercargo": null,
                    "carOwnerId": null
                }
            ],
            "pageNo": 1,
            "pageSize": 2
        }
    }

<br>
<br>
<br>


## 3.车主或车主子账号 - 查看车辆详情 ##

**备注**

    1.暂定以下返回值

**URL**
>/api/car/details 

**请求参数**

    {
    	"params": {
    		"id": ""              （必填）（int），车辆Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {                                   // 车辆基本信息
            "id": 7,
            "carNo": "鄂A10000",
            "carLoad": 100,
            "carUnit": "ton",
            "carUnitCN": "车载量单位,待处理",
            "carType": "car_type",
            "carTypeCN": "车辆类型,待处理",
            "driverId": 1,
            "supercargoId": 1,
            "currentLongitude": null,
            "currentLatitude": null,
            "currentAddress": null,
            "mulStore": true,
            "storeQty": 2,
            "status": "down",
            "statusCN": "车辆状态,待处理",
            "isDelete": false,
            "createBy": 1,
            "createDate": "2019-02-27 14:14:07",
            "carOwnerName": "车主张三",             // 车主姓名
            "carOwner": {                          // 车主基本信息
                "id": 1,
                "name": "车主张三",
                "creditCode": null,
                "businessLicense": null,
                "legalerName": "张三",
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
                "customerType": "customer_car",
                "customerTypeCN": null,
                "avoidAudit": null,
                "closeRate": null,
                "favorableLevel": null,
                "status": "1",
                "statusCN": null,
                "customerId": 1,
                "customer": null,
                "createDate": null
            },
            "driverName": "司机姓名",             // 司机姓名
            "driver": {                          // 司机基本信息
                "id": 1,
                "name": "张",
                "logo": null,
                "tel": null,
                "sex": null,
                "age": null,
                "idCard": null,
                "personType": null,
                "personTypeCN": "",
                "customerId": null,
                "drivingLicence": null,
                "status": "down",
                "statusCN": "状态：待处理",
                "isDelete": false,
                "createBy": 1,
                "createDate": null,
                "carOwnerName": null,
                "carOwner": null
            },
            "supercargoName": "压货人张",       // 压货人姓名
            "supercargo": {                    // 压货人基本信息
                "id": 1,
                "name": "张",
                "logo": null,
                "tel": null,
                "sex": null,
                "age": null,
                "idCard": null,
                "personType": null,
                "personTypeCN": "",
                "customerId": null,
                "drivingLicence": null,
                "status": "down",
                "statusCN": "状态：待处理",
                "isDelete": false,
                "createBy": 1,
                "createDate": null,
                "carOwnerName": null,
                "carOwner": null
            },
            "carOwnerId": 1
        }
    }

<br>
<br>
<br>


## 4.车主或车主子账号 - 修改车辆不需要审核的内容 ##

**备注**

    1.暂定以下不需要审核的内容字段
    修改字段为空处理方案，仅针对可以为空的字段
    2.driverId、supercargoId 为空时，将其数据置空（当前方案）
    3.driverId、supercargoId 为空时，不做修改

**URL**
>/api/car/updateNoCheck

**请求参数**

    {
    	"params": {
    		"id": "",             （必填）（int），车辆Id
            "driverId": "",       （选填）（int），司机Id
            "supercargoId": ""    （选填）（int），压货人Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "修改成功"
    }

<br>
<br>
<br>


## 5.车主或车主子账号 - 修改车辆需要审核的内容 ##

**备注**

    1.暂定以下需要审核的内容字段
    2.修改成功会同时将车辆状态改为已下架

**URL**
>/api/car/updateCheck

**请求参数**

    {
    	"params": {
    		"id": "",            （必填）（int），车辆Id
            "carLoad": "",       （必填）（int），核载量
            "carUnit": "",       （必填）（str），核载量单位
            "carType": "",       （必填）（str），车辆类型
            "mulStore": "",      （必填）（boolean），是否分仓 [true/false]
            "storeQty": ""       （必填）（int），仓位数量
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "修改成功"
    }

<br>
<br>
<br>


## 6.车主或车主子账号 - 删除车辆 ##

**备注**

    1.数据为软删除，删除后数据将不可再做操作，对车主全账号不可见

**URL**
>/api/car/delete

**请求参数**

    {
    	"params": {
    		"id": ""            （必填）（int），车辆Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "删除成功"
    }

<br>
<br>
<br>


## 7.车主或车主子账号 - 车辆提交审核 ##

**备注**

    1.暂定仅下架状态的车辆可以提交审核

**URL**
>/api/car/submitAudit

**请求参数**

    {
    	"params": {
    		"id": ""            （必填）（int），车辆Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "提交审核成功"
    }
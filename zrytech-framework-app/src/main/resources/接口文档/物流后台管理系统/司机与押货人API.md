# 司机与压货人 #

** 作者: CAT **

## 字段说明 ##

    carPerson

    id              主键
    name            姓名
    logo            LOGO
    tel             电话
    sex             性别
    age             年龄
    idCard          身份证号
    personType      类型
    customerId      客户Id
    drivingLicence  驾驶证
    status          状态
    createBy        创建人
    createDate      创建时间
    isDelete        删除标识



<br>
<br>
<br>

# 司机，押货人 后台管理系统接口文档 #

<br>
<br>
<br>

## 1.司机或压货人列表分页 ##

**备注**
    

**URL**
>admin/carPerson/page


**请求参数**

    {
    	"params": {
            "carOwnerName": "",     （选填）（str）企业名称
            "createBy": "",         （选填）（int）车主Id
            "id": "",               （选填）（int）主键
            "name": "",             （选填）（str）姓名
            "tel": "",              （选填）（str）电话
            "personType": "",       （选填）（str）类型
            "isDelete": "",         （选填）（str）删除标识
            "status": ""            （选填）（str）状态
    	},
        "page":{
    		"pageSize":"10",
    		"pageNum":"1"
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
                    "id": 1,
                    "name": "司机李四",
                    "logo": "logo",
                    "tel": "13612341234",
                    "sex": "男",
                    "age": 25,
                    "idCard": "456",
                    "personType": "driver",
                    "personTypeCN": "司机",
                    "customerId": 1,
                    "drivingLicence": "1",
                    "status": "up",
                    "statusCN": "状态：待处理",
                    "isDelete": false,
                    "createBy": 1,
                    "createDate": "2019-02-19 15:34:21",
                    "carOwnerName": "车主张三",              // 车主企业名称
                    "carOwner": null                        // 请忽略该字段
                }
            ],
            "pageNo": 1,
            "pageSize": 1
        }
    }

<br>
<br>
<br>


## 2.司机或压货人详情 ##

**备注**
    
    返回结果包含：
        1.司机或押货人基本信息
        2.车主信息
    
**URL**
>admin/carPerson/details


**请求参数**

    {
    	"params": {
            "id": ""               （必填）（int）主键，司机或者押货人Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "id": 1,
            "name": "司机李四",
            "logo": "logo",
            "tel": "13612341234",
            "sex": "男",
            "age": 25,
            "idCard": "456",
            "personType": "driver",
            "personTypeCN": "司机",
            "customerId": 1,
            "drivingLicence": "1",
            "status": "up",
            "statusCN": "状态：待处理",
            "isDelete": false,
            "createBy": 1,
            "createDate": "2019-02-19 15:34:21",
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
            }
        }
    }
    
<br>
<br>
<br>


## 3.司机或压货人审核 ##

**URL**
>admin/carPerson/check

**备注**

**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）司机或压货人Id
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
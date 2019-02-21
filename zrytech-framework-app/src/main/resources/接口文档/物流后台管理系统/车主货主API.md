# 车主货主后台管理系统接口 #

** 作者:CAT **

<br>
<br>


## 1.车主货主列表分页 ##

**URL**
>admin/carCargoOwner/page

**请求参数**

    {
    	"params": {
            "id":"1",                             (选填)（int）车主货主Id
    		"legalerName":"张",                   (选填)（str）车主货主法人名称
    		"customerType":"customer_car",        (选填)（str）车主货主类型
    		"name":"张",                          (选填)（str）车主货主企业名称
    		"tel":"1",                            (选填)（str）车主货主账号电话
    		"userAccount": "admin",               (选填)（str）车主货主登录名
    		"status":"1"                          (选填)（str）车主货主状态
    	},
        "page":{
            "pageNum": "",
            "pageSize":""
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
                    "customer": {                           【账号信息】
                        "id": 1,
                        "userAccount": "admin",
                        "tel": "13163340567",
                        "userName": "admin",
                        "logo": "123",
                        "appOpenid": "1",
                        "openid": "0",
                        "unionid": "45",
                        "extend": null,
                        "customerType": "customer_car",
                        "userStatus": null,
                        "createBy": 0,
                        "createDate": 1550634180000,
                        "isActive": false
                    },
                    "createDate": null
                }
            ],
            "pageNo": 1,
            "pageSize": 2
        }
    }

<br>
<br>
<br>


## 2.车主货主详情 ##

**备注**

    暂时返回数据如下，等模型确定再加

**URL**
>admin/carCargoOwner/details

**请求参数**

    {
    	"params": {
            "id": ""               (必填)（int）车主货主Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
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
            "customer": {                           【账号信息】
                "id": 1,
                "userAccount": "admin",
                "tel": "13163340567",
                "userName": "admin",
                "logo": "123",
                "appOpenid": "1",
                "openid": "0",
                "unionid": "45",
                "extend": null,
                "customerType": "customer_car",
                "userStatus": null,
                "createBy": 0,
                "createDate": 1550634180000,
                "isActive": false
            },
            "createDate": null
        }
    }
    
<br>
<br>
<br>


## 3.车主货主审核 ##

**URL**
>/admin/carCargoOwner/check

**备注**


**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）车主货主Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果
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


## 4.车主货主禁用与启用 ##

**URL**
>/admin/carCargoOwner/disabled

**备注**


**请求参数**

    {
    	"params": {
            "id": "",             （必填）（int）车主Id
            "result": ""          （必填）（boolean）处理结果[true/false]
    	},
    	"token": ""
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": "成功"
    }
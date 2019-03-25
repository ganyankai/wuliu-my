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
    
    
    
    
	


# 字典 #

    1.状态（status）：              未认证（uncertified）、已认证（certified）
	2.账号类型（customer_type）：   个人（person）、企业（organize）
    3.类型（type）：                车主（car_owner）、货主（cargo_owner）
	4.审批状态（approveStatus）：   待审批（approval_pending）、 审批通过（be_approved）、审批未通过（not_approved）

    1.审批结果（approve_result）:     同意（agree）、拒绝（refuse）



















	
## 1.管理员 - 货主分页 ##

**URL**
>/admin/carCargoOwner/cargoOwnerPage

**备注**


**请求参数**

    {
    	"params": {
            "id": "",             	（选填）（int）货主Id
	        "status": "",			（选填）（str）货主状态（字典取值）
	        "legalerName": "",		（选填）（str）法人姓名
	        "customerType": "",		（选填）（str）账号类型（字典取值）
	        "name": "",			    （选填）（str）企业名称
	        "tel": "",				（选填）（str）联系方式
	        "avoidAudit": "",	    （选填）（boolean）是否免审核
	        "approveStatus": "",	（选填）（str）审核状态（字典取值）
	        "customerTel": "",		（选填）（str）账号手机号
	        "userAccount": ""		（选填）（str）账号用户名
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "",             	
	        "status": "",			
	        "legalerName": "",		
	        "customerType": "",		
	        "name": "",			    
	        "tel": "",				
	        "avoidAudit": "",	    
	        "approveStatus": "",	
	        "customerTel": "",		
	        "userAccount": ""		
    	},
    	"token": "admin"
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
                    "customerType": "organize",
                    "customerTypeCN": "企业",
                    "avoidAudit": false,
                    "closeRate": null,
                    "favorableLevel": null,
                    "status": "audit_pass",
                    "statusCN": null,
                    "customerId": 1,
                    "customer": {
                        "id": 1,
                        "userName": "admin",
                        "userAccount": "admin",
                        "tel": "13163340538",
                        "userStatus": 1,
                        "langType": null,
                        "logo": "123",
                        "appOpenid": "1",
                        "openid": "0",
                        "unionid": "45",
                        "extend": null,
                        "customerType": "customer_cargo",
                        "createBy": 0,
                        "createDate": "2019-02-20 11:43:00",
                        "isActive": false,
                        "carOwner": null,
                        "cargoOwner": null
                    },
                    "createDate": "2019-03-06 14:11:06",
                    "approveStatus": "approval_pending",
                    "approveStatusCN": "待审批",
                    "type": "cargo_owner",
                    "typeCN": "货主"
                }
            ],
            "pageNo": 1,
            "pageSize": 10
        }
    }

<br><br>


## 2.管理员 - 车主分页 ##

**URL**
>/admin/carCargoOwner/carOwnerPage

**备注**


**请求参数**

    {
    	"params": {
            "id": "",             	（选填）（int）车主Id
	        "status": "",			（选填）（str）车主状态（字典取值）
	        "legalerName": "",		（选填）（str）法人姓名
	        "customerType": "",		（选填）（str）账号类型（字典取值）
	        "name": "",			    （选填）（str）企业名称
	        "tel": "",				（选填）（str）联系方式
	        "avoidAudit": "",	    （选填）（boolean）是否免审核
	        "approveStatus": "",	（选填）（str）审核状态（字典取值）
	        "customerTel": "",		（选填）（str）账号手机号
	        "userAccount": ""		（选填）（str）账号用户名
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "",             	
	        "status": "",			
	        "legalerName": "",		
	        "customerType": "",		
	        "name": "",			    
	        "tel": "",				
	        "avoidAudit": "",	    
	        "approveStatus": "",	
	        "customerTel": "",		
	        "userAccount": ""		
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "total": 1,
            "list": [
                {
                    "id": 10,
                    "name": "中软云科技有限公司",
                    "creditCode": "SKDOO124657788X",
                    "businessLicense": "18",
                    "legalerName": "张亮",
                    "legalerIdCardNo": "420922198011272852",
                    "legalerIdCardFront": "12",
                    "tel": "13163340532",
                    "longitude": 104.567,
                    "latitude": 234.123,
                    "province": "湖北省",
                    "city": "荆州市",
                    "county": "荆安村",
                    "addressDetail": "湖北省荆州市荆安村",
                    "intro": "企业简介",
                    "customerType": "organize",
                    "customerTypeCN": "企业",
                    "avoidAudit": false,
                    "closeRate": null,
                    "favorableLevel": null,
                    "status": "audit_process",
                    "statusCN": null,
                    "customerId": 21,
                    "customer": {
                        "id": 21,
                        "userName": null,
                        "userAccount": "pxynet",
                        "tel": "13476296651",
                        "userStatus": null,
                        "langType": null,
                        "logo": "12",
                        "appOpenid": null,
                        "openid": null,
                        "unionid": null,
                        "extend": null,
                        "customerType": "customer_car_owner",
                        "createBy": 0,
                        "createDate": "2019-03-14 17:18:55",
                        "isActive": false,
                        "carOwner": null,
                        "cargoOwner": null
                    },
                    "createDate": "2019-03-14 17:18:55",
                    "approveStatus": "be_approved",
                    "approveStatusCN": "审批通过",
                    "type": "car_owner",
                    "typeCN": "车主"
                }
            ],
            "pageNo": 1,
            "pageSize": 10
        }
    }

<br><br>


## 3.管理员 - 货主详情 ##

**URL**
>/admin/carCargoOwner/cargoOwnerDetails

**备注**


**请求参数**

    {
    	"params": {
            "id": "1",             	（必填）（int）货主Id
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "1",             	
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
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
            "customerType": "organize",
            "customerTypeCN": "企业",
            "avoidAudit": false,
            "closeRate": null,
            "favorableLevel": null,
            "status": "audit_pass",
            "statusCN": null,
            "customerId": 1,
            "customer": {
                "id": 1,
                "userName": "admin",
                "userAccount": "admin",
                "tel": "13163340538",
                "userStatus": 1,
                "langType": null,
                "logo": "123",
                "appOpenid": "1",
                "openid": "0",
                "unionid": "45",
                "extend": null,
                "customerType": "customer_cargo",
                "createBy": 0,
                "createDate": "2019-02-20 11:43:00",
                "isActive": false,
                "carOwner": null,
                "cargoOwner": null
            },
            "createDate": "2019-03-06 14:11:06",
            "approveStatus": "approval_pending",
            "approveStatusCN": "待审批",
            "type": "cargo_owner",
            "typeCN": "货主"
        }
    }

<br><br>


## 4.管理员 - 车主详情 ##

**URL**
>/admin/carCargoOwner/cargoOwnerDetails

**备注**


**请求参数**

    {
    	"params": {
            "id": "10",             	（必填）（int）车主Id
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "10",             	
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "id": 10,
            "name": "中软云科技有限公司",
            "creditCode": "SKDOO124657788X",
            "businessLicense": "18",
            "legalerName": "张亮",
            "legalerIdCardNo": "420922198011272852",
            "legalerIdCardFront": "12",
            "tel": "13163340532",
            "longitude": 104.567,
            "latitude": 234.123,
            "province": "湖北省",
            "city": "荆州市",
            "county": "荆安村",
            "addressDetail": "湖北省荆州市荆安村",
            "intro": "企业简介",
            "customerType": "organize",
            "customerTypeCN": "企业",
            "avoidAudit": false,
            "closeRate": null,
            "favorableLevel": null,
            "status": "audit_process",
            "statusCN": null,
            "customerId": 21,
            "customer": {
                "id": 21,
                "userName": null,
                "userAccount": "pxynet",
                "tel": "13476296651",
                "userStatus": null,
                "langType": null,
                "logo": "12",
                "appOpenid": null,
                "openid": null,
                "unionid": null,
                "extend": null,
                "customerType": "customer_car_owner",
                "createBy": 0,
                "createDate": "2019-03-14 17:18:55",
                "isActive": false,
                "carOwner": null,
                "cargoOwner": null
            },
            "createDate": "2019-03-14 17:18:55",
            "approveStatus": "be_approved",
            "approveStatusCN": "审批通过",
            "type": "car_owner",
            "typeCN": "车主"
        }
    }

<br><br>


## 5.管理员 - 货主审核 ##

**URL**
>/admin/carCargoOwner/approveCargoOwner

**备注**


**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）货主Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果（字典取值）
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "businessId": "1",
            "content": "审核通过", 
            "result": "pass" 
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "审批成功"
    }

<br><br>


## 6.管理员 - 车主审核 ##

**URL**
>/admin/carCargoOwner/approveCarOwner

**备注**


**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）车主Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "businessId": "10",
            "content": "审核通过", 
            "result": "pass" 
    	},
    	"token": "admin"
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": "审核成功"
    }

<br><br>


## 7.管理员 - 修改货主的免审核状态 ##

**URL**
>/admin/carCargoOwner/cargoOwnerUpdateAvoidAudit

**备注**


**请求参数**

    {
    	"params": {
            "id": "1",              （必填）（int）货主Id
            "avoidAudit": "false"   （必填）（boolean）是否免审核
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "1",              
            "avoidAudit": "false"
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "修改成功"
    }

<br><br>


## 8.管理员 - 修改车主的免审核状态 ##

**URL**
>/admin/carCargoOwner/carOwnerUpdateAvoidAudit

**备注**


**请求参数**

    {
    	"params": {
            "id": "1",              （必填）（int）车主Id
            "avoidAudit": "false"   （必填）（boolean）是否免审核
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "id": "1",              
            "avoidAudit": "false"
    	},
    	"token": "admin"
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "修改成功"
    }
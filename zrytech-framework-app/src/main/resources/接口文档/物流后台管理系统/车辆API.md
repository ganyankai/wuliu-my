## 车辆字段说明 ##

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
    approveStatus		（str）审批状态
    approveContent		（str）审批内容


<br>
<br>

# 后台管理系统接口 #

**作者:CAT**

<br>

# 车辆 #

## 1.管理员- 车辆分页 ##

**URL**
>/admin/car/page

**备注**

**请求参数**

    {
    	"params": {
            "carNo": "",              （选填）（str）车牌号（采用模糊搜索）
            "carOwnerId":"",          （选填）（int）车主Id
            "createBy":"",            （选填）（int）创建人Id
            "carOwnerName": "",       （选填）（str）车主企业名称（采用模糊搜索）
            "carType": "",            （选填）（str）车辆类型（字典取值）
            "isDelete": "",           （选填）（boolean）删除标识【true | false】
            "status": "",             （选填）（str）车辆状态（字典取值）
            "approveStatus": ""       （选填）（str）审批状态（字典取值）
    	},
        "page": {
    		"pageSize": "10",
    		"pageNum": "1"
    	},
    	"token": ""
	}
	
**测试入参**

    {
    	"params": {
            "carNo": "京",             
            "carOwnerId":"1",         
            "createBy":"1",           
            "carOwnerName": "公司",      
            "carType": "car_tractor",           
            "isDelete": "false",          
            "status": "uncertified",            
            "approveStatus": "approval_pending",      
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
	                "id": 12,
	                "carNo": "京A10012",
	                "carLoad": 100,
	                "carUnit": "ton",
	                "carType": "car_tractor",
	                "driverId": 17,
	                "supercargoId": 18,
	                "currentLongitude": null,
	                "currentLatitude": null,
	                "currentAddress": null,
	                "mulStore": false,
	                "storeQty": 1,
	                "status": "uncertified",
	                "isDelete": false,
	                "createBy": 1,
	                "createDate": "2019-03-13 15:14:41",
	                "carOwnerId": 1,
	                "carOwnerName": "货主公司",
	                "carOwner": null,
	                "driverName": "司机",
	                "driver": null,
	                "supercargoName": "压货人",
	                "supercargo": null,
	                "approveStatus": "approval_pending",
	                "approveContentCN": null,
	                "approveStatusCN": "待审批",
	                "carTypeCN": "牵引车",
	                "carUnitCN": "吨",
	                "statusCN": "未认证"
	            }
	        ],
	        "pageNo": 1,
	        "pageSize": 1
	    }
	}

<br>
<br>


## 2.管理员- 某一个车主的车辆分页 ##

**URL**
>/admin/car/oneCarOwnerCarPage

**备注**
	
	入参车主Id为必填项

**请求参数**

    {
    	"params": {
            "carNo": "",              （选填）（str）车牌号（采用模糊搜索）
            "carOwnerId":"",          （必填）（int）车主Id
            "createBy":"",            （选填）（int）创建人Id
            "carOwnerName": "",       （选填）（str）车主企业名称（采用模糊搜索）
            "carType": "",            （选填）（str）车辆类型（字典取值）
            "isDelete": "",           （选填）（boolean）删除标识【true | false】
            "status": "",             （选填）（str）车辆状态（字典取值）
            "approveStatus": ""       （选填）（str）审批状态（字典取值）
    	},
        "page": {
    		"pageSize": "10",
    		"pageNum": "1"
    	},
    	"token": ""
	}
	
**测试入参**

    {
    	"params": {
            "carNo": "京",             
            "carOwnerId":"1",         
            "createBy":"1",           
            "carOwnerName": "公司",      
            "carType": "car_tractor",           
            "isDelete": "false",          
            "status": "uncertified",            
            "approveStatus": "approval_pending",      
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
	                "id": 12,
	                "carNo": "京A10012",
	                "carLoad": 100,
	                "carUnit": "ton",
	                "carType": "car_tractor",
	                "driverId": 17,
	                "supercargoId": 18,
	                "currentLongitude": null,
	                "currentLatitude": null,
	                "currentAddress": null,
	                "mulStore": false,
	                "storeQty": 1,
	                "status": "uncertified",
	                "isDelete": false,
	                "createBy": 1,
	                "createDate": "2019-03-13 15:14:41",
	                "carOwnerId": 1,
	                "carOwnerName": "货主公司",
	                "carOwner": null,
	                "driverName": "司机",
	                "driver": null,
	                "supercargoName": "压货人",
	                "supercargo": null,
	                "approveStatus": "approval_pending",
	                "approveContentCN": null,
	                "approveStatusCN": "待审批",
	                "carTypeCN": "牵引车",
	                "carUnitCN": "吨",
	                "statusCN": "未认证"
	            }
	        ],
	        "pageNo": 1,
	        "pageSize": 1
	    }
	}

<br>
<br>
    
## 3.管理员- 待审批的车辆分页 ##

**URL**
>/admin/car/approvePendingCarPage

**备注**

	
**请求参数**

    {
    	"params": {
            "carNo": "",              （选填）（str）车牌号（采用模糊搜索）
            "carOwnerId":"",          （必填）（int）车主Id
            "createBy":"",            （选填）（int）创建人Id
            "carOwnerName": "",       （选填）（str）车主企业名称（采用模糊搜索）
            "carType": "",            （选填）（str）车辆类型（字典取值）
            "isDelete": "",           （选填）（boolean）删除标识【true | false】
            "status": ""              （选填）（str）车辆状态（字典取值）
    	},
        "page": {
    		"pageSize": "10",
    		"pageNum": "1"
    	},
    	"token": ""
	}
	
**测试入参**

    {
    	"params": {
            "carNo": "京",             
            "carOwnerId":"1",         
            "createBy":"1",           
            "carOwnerName": "公司",      
            "carType": "car_tractor",           
            "isDelete": "false",          
            "status": "uncertified"          
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
	                "id": 12,
	                "carNo": "京A10012",
	                "carLoad": 100,
	                "carUnit": "ton",
	                "carType": "car_tractor",
	                "driverId": 17,
	                "supercargoId": 18,
	                "currentLongitude": null,
	                "currentLatitude": null,
	                "currentAddress": null,
	                "mulStore": false,
	                "storeQty": 1,
	                "status": "uncertified",
	                "isDelete": false,
	                "createBy": 1,
	                "createDate": "2019-03-13 15:14:41",
	                "carOwnerId": 1,
	                "carOwnerName": "货主公司",
	                "carOwner": null,
	                "driverName": "司机",
	                "driver": null,
	                "supercargoName": "压货人",
	                "supercargo": null,
	                "approveStatus": "approval_pending",
	                "approveContentCN": null,
	                "approveStatusCN": "待审批",
	                "carTypeCN": "牵引车",
	                "carUnitCN": "吨",
	                "statusCN": "未认证"
	            }
	        ],
	        "pageNo": 1,
	        "pageSize": 1
	    }
	}

<br>
<br>

## 4.管理员 - 车辆详情 ##

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
	
**测试入参**

    {
    	"params": {
            "id": ""
    	},
    	"token": ""
	}
	
**响应示例**

	{
	    "code": 1,
	    "msg": "success",
	    "data": {										// 车辆信息
	        "id": 12,
	        "carNo": "京A10012",
	        "carLoad": 100,
	        "carUnit": "ton",
	        "carType": "car_tractor",
	        "driverId": 17,
	        "supercargoId": 18,
	        "currentLongitude": null,
	        "currentLatitude": null,
	        "currentAddress": null,
	        "mulStore": false,
	        "storeQty": 1,
	        "status": "uncertified",
	        "isDelete": false,
	        "createBy": 1,
	        "createDate": "2019-03-13 15:14:41",
	        "carOwnerId": 10,
	        "carOwnerName": "中软云科技有限公司",
	        "carOwner": {								// 车主信息
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
	            "avoidAudit": false,
	            "closeRate": null,
	            "favorableLevel": null,
	            "status": "audit_process",
	            "customerId": 21,
	            "customer": null,
	            "createDate": "2019-03-14 17:18:55",
	            "approveStatus": "be_approved",
	            "approveContentCN": null,
	            "type": "car_owner",
	            "approveStatusCN": "审批通过",
	            "customerTypeCN": "企业",
	            "typeCN": "车主",
	            "statusCN": null
	        },
	        "driverName": "司机",
	        "driver": {									// 司机信息
	            "id": 17,
	            "name": "司机",
	            "logo": null,
	            "tel": "13612341234",
	            "sex": "男",
	            "age": 18,
	            "idCard": "1",
	            "customerId": 27,
	            "drivingLicence": "1",
	            "isDelete": false,
	            "createBy": 10,
	            "carOwnerId": 10,
	            "createDate": "2019-03-14 18:22:21",
	            "status": "free",
	            "personType": "driver",
	            "approveStatus": "be_approved",
	            "isActive": null,
	            "carOwnerName": null,
	            "carOwner": null,
	            "approveStatusCN": "审批通过",
	            "personTypeCN": "司机",
	            "statusCN": "空闲"
	        },
	        "supercargoName": "压货人",
	        "supercargo": {									// 压货人信息
	            "id": 18,
	            "name": "压货人",
	            "logo": null,
	            "tel": "13612341234",
	            "sex": "男",
	            "age": 18,
	            "idCard": "1",
	            "customerId": null,
	            "drivingLicence": "1",
	            "isDelete": false,
	            "createBy": 10,
	            "carOwnerId": 10,
	            "createDate": "2019-03-19 16:24:18",
	            "status": "free",
	            "personType": "supercargo",
	            "approveStatus": "be_approved",
	            "isActive": null,
	            "carOwnerName": null,
	            "carOwner": null,
	            "approveStatusCN": "审批通过",
	            "personTypeCN": "压货人",
	            "statusCN": "空闲"
	        },
	        "approveStatus": "approval_pending",
	        "approveContentCN": {							// 车辆审核的内容
	            "id": null,
	            "carLoad": 100,
	            "carUnit": "ton",
	            "carType": "car_tractor",
	            "mulStore": false,
	            "storeQty": 1
	        },
	        "approveStatusCN": "待审批",
	        "carTypeCN": "牵引车",
	        "carUnitCN": "吨",
	        "statusCN": "未认证"
	    }
	}

<br>
<br>


## 5.管理员 - 车辆审核 ##

**URL**
>/admin/car/approve

**备注**

    管理员审批车辆，车辆审批状态必须是待审批核才可以操作车辆审批

**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）车辆Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果（字典取值）
    	},
    	"token": ""
	}

**测试入参**

    {
    	"params": {
            "businessId": "1", 
            "content": "审核意见,同意",   
            "result": "agree"     
    	},
    	"token": ""
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": "审批"
    }

<br>
<br>



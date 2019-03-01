# 前台 - 司机与压货人 车主端接口 #

**作者：CAT**


## 字段说明 ##

    带 * 的内容需要审核

    carPerson

    id              （int）主键
    name            （str）姓名
    logo            （str）LOGO
    tel             （str）电话
    sex             （str）性别
    age             （int）年龄
    idCard          （str）身份证号
    personType      （str）类型
    loginId         （int）账号Id
    customerId      （int）客户Id
    drivingLicence  （str）驾驶证
    status          （str）状态
    createBy        （int）创建人
    carOwnerId      （int）车主Id
    createDate      （str）创建时间
    isDelete        （boolean）是否删除

   
    枚举：
    personType 类型   
        1.driver        司机
        2.supercargo    压货人
    
    status 状态
        1.
        2.wait_check    待审核
        3.
    
    
    

<br>
<br>
<br>


## 1.车主或车主子账号 - 添加司机或压货人 ##

**备注**

    1.新添加的司机或压货人默认【待审核】状态，需后台审核
    2.司机需要创建登录账号
    3.压货人暂定不创建登录账号
    4.添加的账号默认【禁用】状态

**URL**
>/api/carPerson/add 

**请求参数**

    {
    	"params": {
    		"name": "",              （必填）（str），姓名
            "tel": "",               （必填）（str），手机号
            "sex": "",               （必填）（str），性别
            "age": "",               （必填）（int），年龄
            "idCard": "",            （必填）（str），身份证
            "personType": "",        （必填）（str），类型【driver/supercargo】
            "drivingLicence": "",    （必填）（str），驾驶证
            // 以下为账号信息，仅在类型为司机时需要填写
            "userAccount": "",       （选填）（str），账号
            "userTel": "",           （选填）（str），账号手机号
            "password": ""           （选填）（str），密码
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


## 2.车主或车主子账号 - 删除司机或压货人 ##

**备注**

    1.修改司机压货人删除标识为【true】，修改司机账号为【禁用】

**URL**
>/api/carPerson/delete 

**请求参数**

    {
    	"params": {
    		"id": ""               （必填）（int），司机或压货人Id
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


## 3.车主或车主子账号 - 司机与压货人启用禁用 ##

**备注**

**URL**
>/api/carPerson/enabled 

**请求参数**

    {
    	"params": {
    		"id": "",              （必填）（int），司机或压货人Id
            "enabled": false       （必填）（boolean），状态【true：启用 / false：禁用】
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


## 4.车主或车主子账号 - 修改司机或压货人不需要审核的内容 ##

**备注**

    1.暂定以下不需要审核的内容字段

**URL**
>/api/carPerson/updateNoCheck

**请求参数**

    {
    	"params": {
    		"id": "",             （必填）（int），司机或压货人Id
            "name": ""            （必填）（str），姓名
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


## 5.车主或车主子账号 - 修改司机或压货人需要审核的内容 ##

**备注**

    1.暂定以下需要审核的内容字段
    2.修改成功会同时将司机压货人状态改为【待审核】

**URL**
>/api/carPerson/updateCheck

**请求参数**

    {
    	"params": {
    		"id": "",                （必填）（int），司机或压货人Id
            "tel": "",               （必填）（str），手机号
            "sex": "",               （必填）（str），性别
            "age": "",               （必填）（int），年龄
            "idCard": "",            （必填）（str），身份证
            "drivingLicence": ""     （必填）（str），驾驶证
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


## 6.车主或车主子账号 - 司机压货人提交审核 ##

**备注**

**URL**
>/api/carPerson/submitAudit

**请求参数**

    {
    	"params": {
    		"id": ""            （必填）（int），司机或压货人Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": "提交审核成功"
    }
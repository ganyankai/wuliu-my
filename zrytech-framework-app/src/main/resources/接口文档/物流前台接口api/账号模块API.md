
**本文档接口提交方式统一为：POST**


### 1.子账号管理

*** 描述:提供新增子账号功能，权限控制子帐号选择中标车主、支付等功能。如果认证类型是个人，这不能新增子帐号


**URL**
>/childAccount/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
       "loginCounter":"",//(选传)(string) 登录账号
       "name": "张三" //(选传)(string) 登录名称
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


**响应示例**

``` json
{
"code":1,
"msg":"success",
"data":
{
"pageNum":1,
"pageSize":10,
"size":2,
"startRow":1,
"endRow":2,
"total":2,
"pages":1,
"list":
[
{
                            "id": 8,//账号Id
                            "loginCounter": "13163340532",//登录账号
                            "pwd": null,//登录密码
                            "name": "张三",//登录名称
                            "logo": 123,//头像id
                            "logoUrl": "",//头像Url地址
                            "referrer": ,//推荐人Id(货主id/车主Id)
                            "status":"",//状态;1:审核中(audit_process);2:审核拒绝(audit_refuse);3:审核通过(audit_pass)
                            "statusCN":"",//状态CN;1:审核中(audit_process);2:审核拒绝(audit_refuse);3:审核通过(audit_pass)
                            "createBy":,//创建人
                            "permissionIds":"",//权限Ids;中标和支付权限;
                            "createDate": "2018-01-10" //创建日期
}
],
"prePage":0,
"nextPage":0,
"isFirstPage":true,
"isLastPage":true,
"hasPreviousPage":false,
"hasNextPage":false,
"navigatePages":8,
"navigatepageNums":[1],
"navigateFirstPage":1,
"navigateLastPage":1,
"firstPage":1,
"lastPage":1
}
}
```



### 1.子账号添加

*** 描述:企业认证可以添加子账号


**URL**
>/childAccount/addAccount



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                             "loginCounter": "13163340532",//登录账号
                                 "pwd": null,//登录密码
                                 "name": "张三",//登录名称
                                 "logo": 123,//头像id
                                 "createBy":,//创建人
                                 "permissionIds":"",//权限Ids;中标和支付权限;
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


**响应示例**

``` json
{
    "code": 1,
    "msg": "success"
}
```



### 1.子账号详情

*** 描述:查看子账号详情

**URL**
>/childAccount/detail



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8,//账号Id                 
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


**响应示例**

``` json
{
    "code": 1,
    "msg": "success",
    "data":
    {
                                "id": 8,//账号Id
                                "loginCounter": "13163340532",//登录账号
                                "pwd": "",//登录密码
                                "name": "张三",//登录名称
                                "logo": 123,//头像id
                                "logoUrl": "",//头像Url地址
                                "referrer": ,//推荐人Id(货主id/车主Id)
                                "status":"",//状态;1:审核中(audit_process);2:审核拒绝(audit_refuse);3:审核通过(audit_pass)
                                "statusCN":"",//状态CN;1:审核中(audit_process);2:审核拒绝(audit_refuse);3:审核通过(audit_pass)
                                "createBy":,//创建人
                                "permissionIds":"",//权限Ids;中标和支付权限;
                                "createDate": "2018-01-10" //创建日期
    }
}
```




### 1.子账号修改

*** 描述:货主针对自己添加的子账号进行修改操作.

**URL**
>/childAccount/updateAccount



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8,//账号Id
                                      "loginCounter": "13163340532",//登录账号
                                      "name": "张三",//登录名称
                                      "logo": 123,//头像id
                                      "isActive":true,//启用或禁用;false:启用;true:禁用
                                      "permissionIds":"",//权限Ids;中标和支付权限;
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


**响应示例**

``` json
{
"code":1,
"msg":"success"
}
```



### 1.子账号删除

*** 描述:货主针对自己添加的子账号进行删除操作.

**URL**
>/childAccount/deleteAccount



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8//账号Id
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


**响应示例**

``` json
{
"code":1,
"msg":"success"
}
```


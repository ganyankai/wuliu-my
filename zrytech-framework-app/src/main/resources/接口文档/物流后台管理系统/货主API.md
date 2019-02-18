
**本文档接口提交方式统一为：POST**


### 1.货主列表信息

**URL**
>/shipper/page


**请求参数**

``` json
{
 "openid": "string",
 "page":{"pageNum":1, "pageSize":10},
 "params": 
{
	"loginCounter":"zhangsan",  //(选传)(string) 用户账户
    "status":""      //(选传)1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
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
   "id": 7,//登录账号id
   "loginCounter": "13163340532",//登录账号
   "pwd": null,//登录密码
   "name": "张三",//登录名称
   "logo": 123,//头像id
   "logoUrl": "",//头像Url地址
   "userType":"",//用户类型;货主或车主
   "userTypeCN":"",//用户类型;货主或车主
   "referrer": ,//推荐人Id(货主id/车主Id)
   "status":"",//状态;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
   "statusCN":"",//状态CN;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
   "createDate": "2018-01-10", //创建日期
   "isActive":false  //启用(false);禁用(true)
},
{
   "id": 8,//登录账号id
   "loginCounter": "13163340532",//登录账号
   "pwd": null,//登录密码
   "name": "张三",//登录名称
   "logo": 123,//头像id
   "userType":"",//用户类型;货主或车主
   "userTypeCN":"",//用户类型;货主或车主
   "logoUrl": "",//头像Url地址
   "referrer": ,//推荐人Id(货主id/车主Id)
   "status":"",//状态;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
   "statusCN":"",//状态CN;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
   "createDate": "2018-01-10", //创建日期
   "isActive":false  //启用(false);禁用(true)
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


### 1.货主详情

**URL**
>/shipper/id

**请求参数**
``` json
{
  "params": 
{
	"id":  //(必填)(string) 货主id
  }
}
```

**响应示例**
``` json
{
    "code": 1,
    "msg": "success",
    "data":  {
       "id": 8,//登录账号id
       "loginCounter": "13163340532",//登录账号
       "pwd": null,//登录密码
       "name": "张三",//登录名称
       "userType":"",//用户类型;货主或车主
       "userTypeCN":"",//用户类型;货主或车主
       "logo": 123,//头像id
       "logoUrl": "",//头像Url地址
       "referrer": ,//推荐人Id(货主id/车主Id)
       "status":"",//状态;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
       "statusCN":"",//状态CN;1:未认证(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
       "createDate": "2018-01-10", //创建日期
       "certification":{//个人或企业认证资料
                                                                  "id": 8,//认证资料Id
                                                                  "name": "",//企业名称
                                                                  "creditCode":"",//信用代码
                                                                  "businessLicense": "",//营业执照
                                                                  "businessLicenseUrl": "",//营业执照url
                                                                  "legalerName": "张三",//法人姓名
                                                                  "legalerIdCardNo": 123,//法人身份证号码
                                                                  "legalerIdCardFront": "",//法人身份证正面照
                                                                  "legalerIdCardFrontUrl": "",//法人身份证正面照url
                                                                  "tel": ,//联系电话
                                                                  "avoidAudit":true , //是否免审核;true:是;false:否
                                                                  "longitude":true,//经度
                                                                  "latitude":""//纬度
                                                                  "province":"",   //省份
                                                                  "city":"",   //城市
                                                                  "county":"",   //县
                                                                  "addressDetail":"",   //地址详情
                                                                  "intro":"",   //企业简介
                                                                  "customerType":"",   //类型
                                                                  "cusomerName":"",   //客户名称(Id)
                                                                  "createDate":"",   //创建日期
                                                                  "status":"",   //状态;字典key;1:认证中(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
                                                                  "statusCN":""   //状态;字典key;1:认证中(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
                                      }
                    }
}
```


### 1.货主启用禁用

***描述:对于注册的货主,后台管理人员可以对其进行启用禁用设置.

**URL**
>/shipper/enableOrUnable

**请求参数**
``` json
{
  "params": 
{
	"id":,  //(必填)(int) 货主id
	"isActive":true //2:启用(false);3:禁用(true)
  }
}
```

**响应示例**

``` json
{
    "code": 1,
    "msg": "success"
}
```



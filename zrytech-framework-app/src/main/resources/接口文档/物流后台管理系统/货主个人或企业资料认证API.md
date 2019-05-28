
**本文档接口提交方式统一为：POST**

注意:该接口已不再使用

### 1.个人/企业认证资料列表信息

*** 描述:个人/企业认证资料列表信息



**URL**
>/shipper/certificationPage

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
       "name":"",//(选传)(string) 企业名称
       "legalerName": "张三",//(选传)(string) 法人姓名
       "tel":"",//(选传)(string) 联系电话
       "status":""//(选传)(string) 状态
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
                            "status":,   //状态;字典key;1:认证中(audit_process);2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
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



### 1.个人/企业资料详情

*** 描述:后台针对个人或企业资料进行审核.

**URL**
>/shipper/detail



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8,//个人/企业Id                 
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
```



## 1.货主认证资料审核

**URL**

#### 描述:后台对提交的认证资料进行审核,状态有:未认证,认证拒绝,认证通过.


>/shipper/certificationAudit

**请求参数**

``` json
{
  "params": 
{
    "id":1,//(必填);货主或车主id;
    "status":"", //(必填);字典;2:认证拒绝(audit_refuse);3:认证通过(audit_pass)
    "avoidAudit":true , //是否免审核;true:是;false:否
    "describe":""//(选填);字典;如果是拒绝,需填写拒绝原因
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


## 1.货主设置是否免审核

**URL**

#### 描述:后台对提交的资料设置该用户是否免审核用户.


>/shipper/withOutAudit

**请求参数**

``` json
{
  "params": 
{
    "id":1,//(必填);货主或车主id;
    "avoidAudit":true , //是否免审核;true:是;false:否
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

**本文档接口提交方式统一为：POST**


### 1.线路分页列表信息

*** 描述:线路分页列表信息


**URL**
>/line/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
                                 "startProvince": "",//(选传)出发省
                                 "startCity": "",//(选传)出发市
                                 "startCountry": "",//(选传)出发县
                                 "endProvince": ,//(选传)到达省
                                 "endCity": "",//(选传)到达市
                                 "endCountry": "",//(选传)到达县
                                 "createBy"://(选传)创建人
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
                              "id":1,//线路ID
                              "startProvince": "",//出发省
                              "startCity": "",//出发市
                              "startCountry": "",//出发县
                              "endProvince": ,//到达省
                              "endCity": "",//到达市
                              "endCountry": "",//到达县
                              "createBy":,//创建人
                              "createDate":""//创建日期
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



### 1.线路详情

*** 描述:后台查看线路详情

**URL**
>/line/get

   

**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8//线路Id
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
         "id": 7,//货源id
         "startProvince": "",//出发省
         "startCity": "",//出发市
         "startCountry": "",//出发县
         "endProvince": ,//到达省
         "endCity": "",//到达市
         "endCountry": "",//到达县
         "createBy":,//创建人
         "createDate":""//创建日期
    }
}
```



=========================关注车主或货主API========================

### 1.关注列表信息

*** 描述:车主查看关注的车主或货主.


**URL**
>/focusOn/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
   "focusType": "",//(选传)(string)关注类型;关注的是路线还是车主;
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
                            "focuserId": 5,//关注人Id
                            "beFocuserId": 15,//被关注的人Id
                            "focusType": "",//关注类型;关注的是路线还是货主;
                            "focusTypeCN": "",//关注类型;关注的是路线还是货主;
                            "name":"",//被关注人的企业名称
                            "createDate":"" //创建时间
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

### 1.关注详情(后台)

*** 描述:查看关注详情信息

**URL**
>/focusOn/get

**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8//关注ID
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```

**响应示例**

``` json
{
"code":1,
"msg":"success",
"data":{
                              "id": 8,//账号Id
                              "focuserId": 5,//关注人Id
                              "beFocuserId": 15,//被关注的人Id
                              "focusType": "",//关注类型;关注的是路线还是货主;
                              "focusTypeCN": "",//关注类型;关注的是路线还是货主;
                              "name":"",//被关注人的企业名称
                              "createDate":"" //创建时间
}
}
```

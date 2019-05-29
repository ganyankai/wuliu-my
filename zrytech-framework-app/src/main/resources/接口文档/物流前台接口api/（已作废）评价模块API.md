
**本文档接口提交方式统一为：POST**


### 1.评论列表信息展示

*** 描述:针对已评价的运单进行查看.


**URL**
>/comments/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
       "billNo":"",//(选传)(string) 订单编号
       "appraiserId": 2, //(选传)(int) 评价人Id
       "appraiserById":3,//(选传)(int) 被评价人id
       "content":"",//(选传)(string) 评语
       "evaluateLevel":23,//(选传)(int) 评价等级
       "evaluateType":"" //(选传)(string) 评价类型
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
                            "id": 8,//消息Id
                            "billNo":"",// 订单编号
                            "appraiserName": 2, //评价人名称(Id)
                            "appraiserByIdName":3,//被评价人名称(id)
                            "content":"",// 评语
                            "evaluateLevel":23,//评价等级
                            "evaluateType":"", //评价类型
                            "createDate":""//创建时间
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


无
### 1.评价运单

*** 描述:针对已支付待评价的订单进行评价.


**URL**
>/comments/add



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                              "billNo":"",// 订单编号
                              "content":"",// 评语
                              "evaluateLevel":23,//评价等级
                              "evaluateType":"" //评价类型
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




### 1.评价详情

*** 描述:查看评价详情.

**URL**
>/comments/get



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8//评论Id
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
                              "billNo":"",// 订单编号
                              "appraiserName": 2, //评价人名称(Id)
                              "appraiserByIdName":3,//被评价人名称(id)
                              "content":"",// 评语
                              "evaluateLevel":4,//评价等级
                              "evaluateType":"", //评价类型
                              "createDate":""//创建时间
}
}
```



### 1.评价删除

*** 描述:删除评价.

**URL**
>/comments/delete


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8//评价Id
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


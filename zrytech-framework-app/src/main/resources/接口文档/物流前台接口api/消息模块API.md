
**本文档接口提交方式统一为：POST**


### 1.消息中心

*** 描述:消息列表展示


**URL**
>/message/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
       "msg_type":"",//(选传)(string) 登录账号
       "sender_id": "张三" //(选传)(int) 发送人Id
       "sender_type":"",//(选传)(string) 发送人类型
       "content":"",//(选传)(string) 发送内容
       "reveicer_id":23,//(选传)(int) 接受人Id
       "reveicer_type":"" //(选传)(string) 接受人类型
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
                            "msgType": "",//消息类型
                            "senderName":"",//发送人名称
                            "senderType": "",//发送人类型
                            "senderDate": "张三",//发送日期
                            "content": 123,//发送内容
                            "reveicerName": "",//接受人名称
                            "reveicerType": ,//接受人类型
                            "markRead":true,//是否已读;true:已读;false:未读
                            "readDate":""//读取日期
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



### 1.消息添加

*** 描述:添加消息(推送)


**URL**
>/message/add



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                               "content": 123,//发送内容
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




### 1.消息详情

*** 描述:查看消息详情.

**URL**
>/message/get



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8//消息Id
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
                              "id": 2,//消息Id
                              "msgType": "",//消息类型
                              "senderName":"",//发送人名称
                              "senderType": "",//发送人类型
                              "senderDate": "张三",//发送日期
                              "content": 123,//发送内容
                              "reveicerName": "",//接受人名称
                              "reveicerType": ,//接受人类型
                              "markRead":true,//是否已读;true:已读;false:未读
                              "readDate":""//读取日期
}
}
```



### 1.消息删除

*** 描述:删除消息.

**URL**
>/message/delete



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	                                  "id": 8//消息Id
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


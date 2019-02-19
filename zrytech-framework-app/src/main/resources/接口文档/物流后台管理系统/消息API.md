
**本文档接口提交方式统一为：POST**


### 1.消息分页列表信息

*** 描述:消息分页列表信息


**URL**
>/message/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
                                 "msgType": "",//(选传)消息类型
                                 "senderId": ,//(选传)发送人
                                 "content": "",//(选传)内容
                                 "reveicerId":,//(选传)接收人
                                 "markRead":"",//(选传)已读
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
                              "id":1,//消息ID
                              "msgType": "",//消息类型
                              "msgTypeCN": "",//消息类型
                              "senderId": ,//发送人
                              "senderType": "",//发送人类型
                              "senderTypeCN": "",//发送人类型
                              "senderDate":"",//发送日期
                              "content": "",//内容
                              "reveicerId":,//接收人
                              "reveicerType":"",//接收人类型
                              "reveicerTypeCN":"",//接收人类型
                              "markRead":"",//已读
                              "readDate":"",//查看日期
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



### 1.消息详情

*** 描述:查看消息详情

**URL**
>/message/get

   

**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 1//消息Id
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
                                       "id":1,//消息ID
                                       "msgType": "",//消息类型
                                       "msgTypeCN": "",//消息类型
                                       "senderId": ,//发送人
                                       "senderType": "",//发送人类型
                                       "senderTypeCN": "",//发送人类型
                                       "senderDate":"",//发送日期
                                       "content": "",//内容
                                       "reveicerId":,//接收人
                                       "reveicerType":"",//接收人类型
                                       "reveicerTypeCN":"",//接收人类型
                                       "markRead":"",//已读
                                       "readDate":"",//查看日期
    }
}
```


### 1.添加消息
   
   *** 描述:添加消息
   
   
   **URL**
   >/message/add
   
   
   
   **请求参数**
   
   ``` json
   {
     "openid": "string",
     "params": 
   {
                                  "content": ""//内容
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
   
   
 ### 1.消息删除
 
 *** 描述:消息删除
 
 
 **URL**
 >/message/delete
 
 
 
 **请求参数**
 
 ``` json
 {
   "openid": "string",
   "params": 
 {
                                "id":5 //消息ID
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
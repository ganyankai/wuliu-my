
**本文档接口提交方式统一为：POST**


### 1.货主常用地址类表信息

**URL**
>/usedAddress/page


**请求参数**

``` json
{
 "openid": "string",
 "page":{"pageNum":1, "pageSize":10},
 "params": 
{
    "cargoName":"",  //货物介质
	"beginProvince":"湖北省",  //(选传)(string) 出发省
	"beginCity": "黄冈",//(选传)(string)出发城市
	"beginCounty": "罗田" //(选传)(string)出发县
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
   "id": 7,//常用地址Id
   "cargoName":"",  //货物介质
   "customerId": 1,//客户Id
   "beginProvince": null,//出发省份
   "beginCity": "张三",//出发城市
   "beginCounty": 123,//出发县
   "endProvince": "",//到达省份
   "endCity":"",//到达市
   "endCounty":"",//到达县
   "createDate": "2018-01-10" //创建日期
},
{
        "id": 7,//常用地址Id
        "cargoName":"",  //货物介质
        "customerId": 1,//客户Id
        "beginProvince": null,//出发省份
        "beginCity": "张三",//出发城市
        "beginCounty": 123,//出发县
        "endProvince": "",//到达省份
        "endCity":"",//到达市
        "endCounty":"",//到达县
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



### 1.常用地址添加(前端调用:货主或车主)

**URL**
>/usedAddress/add

**请求参数**
``` json
{
  "params": 
{
                  "cargoName":"",  //货物介质
                  "beginProvince": null,//出发省份
                  "beginCity": "张三",//出发城市
                  "beginCounty": 123,//出发县
                  "endProvince": "",//到达省份
                  "endCity":"",//到达市
                  "endCounty":""//到达县
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




### 1.常用地址详情

**URL**
>/usedAddress/get

**请求参数**
``` json
{
  "params": 
{
	"id":7  //(必填)(string) 常用地址Id
  }
}
```

**响应示例**
``` json
{
    "code": 1,
    "msg": "success",
    "data":  {
              "id": 7,//常用地址Id
              "cargoName":"",  //货物介质
              "customerId": 1,//客户Id
              "beginProvince": null,//出发省份
              "beginCity": "张三",//出发城市
              "beginCounty": 123,//出发县
              "endProvince": "",//到达省份
              "endCity":"",//到达市
              "endCounty":"",//到达县
              "createDate": "2018-01-10" //创建日期
                    }
}
```


### 1.常用地址修改

***描述:后台针对常用地址进行修改操作.

**URL**
>/usedAddress/update

**请求参数**
``` json
{
  "params": 
{
	              "id": 7,//常用地址Id
	              "cargoName":"",  //货物介质
                  "customerId": 1,//客户Id
                  "beginProvince": null,//出发省份
                  "beginCity": "张三",//出发城市
                  "beginCounty": 123,//出发县
                  "endProvince": "",//到达省份
                  "endCity":"",//到达市
                  "endCounty":"",//到达县
                  "createDate": "2018-01-10" //创建日期
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


### 1.常用地址删除

***描述:删除常用地址

**URL**
>/usedAddress/delete

**请求参数**
``` json
{
  "params": 
{
	"id":12,  //(必填)(int) 地址id
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


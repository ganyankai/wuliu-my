
**本文档接口提交方式统一为：POST**


### 1.货主常用地址类表信息

**URL**
>/ofenLocation/page


**请求参数**

``` json
{
	  "params":{} ,
	  "page":{
		"pageNum": 1,   
	    "pageSize": 10
	   },
	  "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NCIsImlzcyI6Imh0dHBzOi8vd3d3Lmthbmdhcm9vYmFieWNhci5jb20iLCJzdWIiOiIxMzYxMTExMTEyMyIsImlhdCI6MTU1ODMxODg2NX0.dm783BZftTJPa-YNu6N-OvJRHvbl6D5DLXTnSoJMiBU"
	}
```



**响应示例**

``` json
{
	    "code": 1,
	    "msg": "success",
	    "data": {
		"total": 1,
		"list": [
		    {
			"id": 1,
			"name": "xm",
			"tel": "134",
			"province": "湖北",
			"city": "武汉",
			"county": null,
			"detailedAddress": null,
			"longitude": null,
			"latitude": null,
			"createDate": null,
			"cargoOwnerId": null
		    }
		]
	    }
	}
```



### 1.常用地址添加(前端调用:货主或车主)

**URL**
>/ofenLocation/add

**请求参数**
``` json
{
	  "params":{
		"id":5,
		"name":"xm",
		"tel":"134",
		"province":"湖北",
		"city":"武汉",
		
		"county":"洪山",
		"detailedAddress":"光谷贸易中心",
		"longitude":18,
		"latitude":19
	  } ,
	  
	  "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NCIsImlzcyI6Imh0dHBzOi8vd3d3Lmthbmdhcm9vYmFieWNhci5jb20iLCJzdWIiOiIxMzYxMTExMTEyMyIsImlhdCI6MTU1ODMxODg2NX0.dm783BZftTJPa-YNu6N-OvJRHvbl6D5DLXTnSoJMiBU"
	}
```

**响应示例**
``` json
{
	    "code": 1,
	    "msg": "success",
	    "data": "添加成功"
	}
```




### 1.常用地址详情

**URL**
>/ofenLocation/get

**请求参数**
``` json
{
  "params": 
{
	"id":1  //(必填)(string) 常用地址Id
  }
}
```

**响应示例**
``` json
{
	    "code": 1,
	    "msg": "success",
	    "data": {
		"id": 1,
		"name": "xmxmxm",
		"tel": "134",
		"province": "湖北",
		"city": "武汉",
		"county": "洪山",
		"detailedAddress": "光谷贸易中心",
		"longitude": 18,
		"latitude": 19,
		"createDate": null,
		"cargoOwnerId": 4
	    }
	}
```


### 1.常用地址修改

***描述:后台针对常用地址进行修改操作.

**URL**
>/ofenLocation/update

**请求参数**
``` json
{
	  "params":{
		"id":1,
		"name":"xmxmxm",
		"tel":"134",
		"province":"湖北",
		"city":"武汉",
		
		"county":"洪山",
		"detailedAddress":"光谷贸易中心",
		"longitude":18,
		"latitude":19
	  } ,
	  
	  "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NCIsImlzcyI6Imh0dHBzOi8vd3d3Lmthbmdhcm9vYmFieWNhci5jb20iLCJzdWIiOiIxMzYxMTExMTEyMyIsImlhdCI6MTU1ODMxODg2NX0.dm783BZftTJPa-YNu6N-OvJRHvbl6D5DLXTnSoJMiBU"
	}
```

**响应示例**

``` json
{
	    "code": 1,
	    "msg": "success",
	    "data": null
	}
```


### 1.常用地址删除

***描述:删除常用地址

**URL**
>/ofenLocation/delete

**请求参数**
``` json
{
	  "params":{
		"id":1
	  } ,
	  
	  "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NCIsImlzcyI6Imh0dHBzOi8vd3d3Lmthbmdhcm9vYmFieWNhci5jb20iLCJzdWIiOiIxMzYxMTExMTEyMyIsImlhdCI6MTU1ODMxODg2NX0.dm783BZftTJPa-YNu6N-OvJRHvbl6D5DLXTnSoJMiBU"
	}
```

**响应示例**

``` json
{
	    "code": 1,
	    "msg": "success",
	    "data": null
	}
```



**本文档接口提交方式统一为：POST**


### 1.关注人列表信息

**URL**
>/familiarCar/page


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
        "total": 2,
        "list": [
            {
                "id": 1,
                "cargoOwnnerId": 3,  //关注人id
                "carOwnnerId": 4,    //被关注人id
                "createDate": null
            },
            {
                "id": 2,
                "cargoOwnnerId": 3,
                "carOwnnerId": 5,
                "createDate": null
            }
        ]
    }
}
```



### 1.添加(前端调用:货主或车主)

**URL**
>/familiarCar/add

**请求参数**
``` json
{
	  "params":{
                "cargoOwnnerId": 3,  //关注人id
                "carOwnnerId": 4     //被关注人id
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








### 1.取消关注

***描述:取消关注

**URL**
>/familiarCar/delete

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


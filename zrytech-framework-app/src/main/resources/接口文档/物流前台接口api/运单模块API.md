
**本文档接口提交方式统一为：POST**


### 1.运单列表信息查看

*** 描述:货主发布货源由招标或抢标方式推送给车主,如果是抢标的话,车主抢标成功即生成运单,如果是招标的话
由车主报价给货主,货主选择中标的车主生成运单.


**URL**
>/font/waybill/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
  	  "billNo":"",   //(选传)(string) 订单编号
    "carNo":"",      //(选传)(string) 车牌号
    "startDate":"",      //(选传)(string) 运单开始时间
    "endDate":"",      //(选传)(string) 运单结束时间
    "name":23,      //(选传)(string) 司机姓名
    "status":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
    "organizeName":""      //(选传)(string) 车主公司
    "startPlace":"",  //(选传)(string)提货地
    "endPlace":""     //(选传)(string)到货地
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
       "id":1,//订单id
       "billNo": "S20181224123457",//订单编号
       "advanceMoeny": 1121,//预付金额
       "finalMoney": 1224,//尾款
       "totalMoney": 2345,//总金额
       "remark": "",//备注
       "qty": 23,//数量
       "payWay":"",//付款类型;货到付款(arrival_pay);尾款(tail_pay);首付(first_pay)
       "weightUnit": ,//重量单位
       "weightUnitCN": ,//重量单位
       "status":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "statusCN":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "createDate": "2018-01-10", //创建日期
       "proofImgs":"",//收货凭证
       "waybillDetails":[     //订单详情
         {
           "billNo": "S20181224123457",//订单编号
           "carId":,//车辆Id
           "driverId":,//司机Id
           "supercargoId":,//压货人Id
           "qty":,//运输数量
           "weightUnit":,//重量单位
           "flowName":"",//进度阶段名称
           "createDate":"", //创建时间
           "billLocations":[  //运单装卸地
               {
                 "waybillDetailId":,//运单详情ID
                 "waybillId":,//运单Id
                 "longitude":,//经度
                 "latitude":,//纬度
                 "province":"",//省
                 "city":"",//市
                 "county":"",//县
                 "addressDetail":"",//地址详情
                 "qty":,//装卸数量
                 "weightUnit":"",//重量单位
                 "type":"",//类型
                 "seqNo":,//序号
                 "remark":"",//说明
                 "loadDate":,//装卸日期
                 "endDate":,//截止日期
                 "status":,//状态
                 "createDate"://创建日期
                }
             ]
           }
       ]
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


### 1.运单统计

*** 描述:货主查进入个人中心,查看每种订单的统计数量.


**URL**
>/font/waybill/coundIndent



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	  "id":,   //(选传)(int) 当前登录货主Id
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
     "waitConfirmCount":123, //待确定运单
      "waitLoadingCount":145, //待装货运单
      "waitAcceptCount":23, //待收货
      "signPaiedCount":4, //已签收待支付
      "isEvaluationCount":6, //已支付待评价
      "completedCount":27, //已完成订单
}
}
```



### 1.运单详情

*** 描述:货主查看某个订单详情


**URL**
>/font/waybill/get



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	  "id":,   //(必传)(int) 运单Id
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
       "id":1,//订单id
       "billNo": "S20181224123457",//订单编号
       "advanceMoeny": 1121,//预付金额
       "finalMoney": 1224,//尾款
       "totalMoney": 2345,//总金额
       "remark": "",//备注
       "qty": 23,//数量
       "proofImgs":"",//收货凭证
       "payWay":"",//付款类型;货到付款(arrival_pay);尾款(tail_pay);首付(first_pay)
       "weightUnit": ,//重量单位
       "weightUnitCN": ,//重量单位
       "status":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "statusCN":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "createDate": "2018-01-10", //创建日期
       "waybillDetails":[     //订单详情
         {
           "billNo": "S20181224123457",//订单编号
           "carId":,//车辆Id
           "driverId":,//司机Id
           "supercargoId":,//压货人Id
           "qty":,//运输数量
           "weightUnit":,//重量单位
           "flowName":"",//进度阶段名称
           "createDate":"", //创建时间
           "billLocations":[  //运单装卸地
               {
                 "waybillDetailId":,//运单详情ID
                 "waybillId":,//运单Id
                 "longitude":,//经度
                 "latitude":,//纬度
                 "province":"",//省
                 "city":"",//市
                 "county":"",//县
                 "addressDetail":"",//地址详情
                 "qty":,//装卸数量
                 "weightUnit":"",//重量单位
                 "type":"",//类型
                 "seqNo":,//序号
                 "remark":"",//说明
                 "loadDate":,//装卸日期
                 "endDate":,//截止日期
                 "status":,//状态;
                 "createDate"://创建日期
                }
             ]
           }
       ]
}
}
```


## 5.订单有车主装货后,进入到待收货阶段,货主确认收货开始支付.(已签收待支付状态)

**URL**
>/font/waybill/signAccpet

**请求参数**

	{
	"params":{
		"id":"10"					//	（必传）订单ID
	},
	"token": "string"
	}

**响应示例**

	{
    "code": 1,
    "msg": "success",
    "data": "成功"
	}

----------

## 6.货主开始支付(运单变为:已支付待评价)

**URL**
>/font/waybill/payment

**请求参数**

	{
	"params":{
		"id":"10"					//	（必传）订单ID
	},
	"token": "string"
	}

**响应示例**

	{
    "code": 1,
    "msg": "success"	}



### 1.更改运单

*** 描述:主要提供确认后的运单修改功能，货主与车主在运输价格上经过线下沟通后，双方确认一个认可的价格，货主进行修改。

**URL**
>/font/waybill/changeUpdate



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	       "id":1,//订单id
           "totalMoney": 2345//总金额
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





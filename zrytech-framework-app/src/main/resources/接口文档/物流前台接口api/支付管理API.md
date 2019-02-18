
**本文档接口提交方式统一为：POST**


### 1.支付管理

*** 描述:主要针对未支付,历史支付订单信息查看.


**URL**
>/indent/payPage

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
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
       "weightUnit": ,//重量单位
       "weightUnitCN": ,//重量单位
       "status":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "statusCN":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
       "createDate": "2018-01-10", //创建日期
       "list":[     //订单详情
         {
           "billNo": "S20181224123457",//订单编号
           "carId":,//车辆Id
           "driverId":,//司机Id
           "supercargoId":,//压货人Id
           "qty":,//运输数量
           "weightUnit":,//重量单位
           "flowName":"",//进度阶段名称
           "createDate":"", //创建时间
           "list":[  //运单装卸地
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

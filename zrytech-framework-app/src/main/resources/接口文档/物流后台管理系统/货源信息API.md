
**本文档接口提交方式统一为：POST**


### 1.货源信息列表

*** 描述:货主登录物流查看自己发布的货源信息.


**URL**
>/goodsSource/page



**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
  	  "name":"",   //(选传)(string) 货物介质
    "startPlace":"",      //(选传)(string) 货物出发地
    "endPlace":"",      //(选传)(string) 货物出目的地
    "qty":23,      //(选传)(string) 货物数量
    "status":"", //状态;1:待审核(wait_audit);2:已上架(source_up);3:未上架(source_down);4:已过期(source_expired)
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
   "id": 7,//货源id
   "name": "",//货物介质
   "logo": 123,//logo头像
   "logoUrl": "",//logo头像url
   "qty": ,//数量
   "weightUnit": "",//重量单位
   "weightUnitCN": "",//重量单位CN
   "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "tenderWayCN":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "payTypeCN":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "mulShipment":true,//多点装货;true:表示是多点装货;false:否
   "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
   "matterPrice":12, //发标价
   "realPrice":34, //中标价
   "marketPrice":23, //市场价
   "priceUnit":"", //价格单位
   "priceUnitCN":"", //价格单位CN
   "priceType":"", //价格类型
   "priceTypeCN":"", //价格类型CN
   "start_province":"",//出发省
   "start_city":"",//出发市
   "start_country":"",//出发县
   "end_province":"",//到达省
   "end_city":"",//到达市
   "end_country":"",//到达县
   "line":"",//路线
   "canShare":true,//是否拼单;true是;false否;
   "status":"", //状态;1:待审核(wait_audit);2:已上架(source_up);3:未上架(source_down);4:已过期(source_expired)
   "arrivalDate":"", //送达日期
   "endDate":"",//截止日期
   "remark":"",//备注
   "createDate":"" //创建日期
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



### 1.货源详情

*** 描述:货主查看货源详情


**URL**
>/goodsSource/get



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	  "id":,   //(选传)(int) 货源Id
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
   "id": 7,//货源id
   "name": "",//货物介质
   "logo": 123,//logo头像
   "logoUrl": "",//logo头像url
   "qty": ,//数量
   "weightUnit": "",//重量单位
   "weightUnitCN": "",//重量单位CN
   "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "tenderWayCN":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "payTypeCN":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "mulShipment":true,//多点装货;true:表示是多点装货;false:否
   "mulShipmentList":[
         {
           "id":2,//装货ID
           "longitude":,//经度
           "latitude":,//纬度
           "province":"",//省
           "city":"",//市
           "county":"",//县
           "addressDetail":"",//详细地址
           "qty":,//装卸数量
           "type":"",//类型;
           "remark":"",//说明
           "status":"",//状态;
           "loadDate":"",//装卸日期
           "endDate":""//截止日期
         }
   ]
   "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
   "mulUnloadList":[
       {
                 "id":1,//卸货ID
                 "longitude":,//经度
                 "latitude":,//纬度
                 "province":"",//省
                 "city":"",//市
                 "county":"",//县
                 "addressDetail":"",//详细地址
                 "qty":,//装卸数量
                 "type":"",//类型;
                 "remark":"",//说明
                 "status":"",//状态
                 "loadDate":"",//装卸日期
                 "endDate":""//截止日期
               }
   ]
   "packaged":true, //是否有包装;true:表示是;false:否
   "matterPrice":12, //发标价
   "realPrice":34, //中标价
   "marketPrice":23, //市场价
   "priceUnit":"", //价格单位
   "priceUnitCN":"", //价格单位CN
   "priceType":"", //价格类型
   "priceTypeCN":"", //价格类型CN
   "start_province":"",//出发省
   "start_city":"",//出发市
   "start_country":"",//出发县
   "end_province":"",//到达省
   "end_city":"",//到达市
   "end_country":"",//到达县
   "line":"",//路线
   "canShare":true,//是否拼单;true是;false否;
   "status":"", //状态;1:待审核(wait_audit);2:已上架(source_up);3:未上架(source_down);4:已过期(source_expired)
   "arrivalDate":"", //送达日期
   "endDate":"",//截止日期
   "remark":"",//备注
   "createDate":"" //创建日期
}
}
```



### 1.货源审核(后台)

*** 描述:针对后台发布的货源后台进行审核,审核通过,通过招标和抢标的方式推送给车主;如果是免审核则不需后台审核直接推送
(抢标推送规则:介质和提货地址以及接货地址;招标:货主可以选择本运单标是包车固定价还是每公里每吨运费单价进行应标报价,车主投标报价)


**URL**
>/goodsSource/auditSource



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id":123,  //货源Id
  	 "status":"", //状态;1:拒绝就未上架(source_down);2:已上架(source_up);
  	 "describe":"" //(选填)(string)如果是拒绝需填拒绝理由
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
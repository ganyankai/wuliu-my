
**本文档接口提交方式统一为：POST**


### 1.货源信息列表

*** 描述:货主登录物流查看自己发布的货源信息,需要按货物数量和发布时间来排序,默认按当前定位城市搜索


**URL**
>/goodsSource/mySourcePage



**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
  	  "name":"",   //(选传)(string) 货物介质
      "startProvince":"",//(选传)(string)出发省
      "startCity":"",//(选传)(string)出发市
      "startCountry":"",//(选传)(string)出发县
      "endProvince":"",//(选传)(string)到达省
      "endCity":"",//(选传)(string)到达市
      "endCountry":"",//(选传)(string)到达县
      "qty":23,      //(选传)(string) 货物数量
      "tenderWay":"",//(选传)(string)发标方式;字典key:tender_mark(招标);bid_mark(抢标)
      "createBy":1   //(选传)创建人(int) 当前登录人ID
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
   "weightUnit": "",//重量单位;ton(吨);rise(升)
   "weightUnitCN": "",//重量单位CN;ton(吨);rise(升)
   "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "tenderWayCN":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "payTypeCN":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "mulShipment":true,//多点装货;true:表示是多点装货;false:否
   "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
   "matterPrice":12, //发标价
   "realPrice":34, //中标价
   "marketPrice":23, //市场价
   "priceUnit":"", //价格单位;price_yuan(人民币);price_dollar(美元)
   "priceUnitCN":"", //价格单位CN;price_yuan(人民币);price_dollar(美元)
   "priceType":"", //价格类型;fixed_price(包车固定价);perton_price(每吨运费单价);
   "priceTypeCN":"", //价格类型CN;fixed_price(包车固定价);perton_price(每吨运费单价);
   "startProvince":"",//出发省
   "startCity":"",//出发市
   "startCountry":"",//出发县
   "endProvince":"",//到达省
   "endCity":"",//到达市
   "endCountry":"",//到达县
   "line":"",//路线
   "canShare":true,//是否拼单;true是;false否;
   "status":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
   "statusCN":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
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


### 1.货源信息列表

*** 描述:货源大厅分页列表信息(针对招标类型);(默认按装货时间倒序进行排序、吨数、发布时间倒序)

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
      "startProvince":"",//(选传)(string)出发省
      "startCity":"",//(选传)(string)出发市
      "startCountry":"",//(选传)(string)出发县
      "endProvince":"",//(选传)(string)到达省
      "endCity":"",//(选传)(string)到达市
      "endCountry":"",//(选传)(string)到达县
      "qty":23,      //(选传)(string) 货物数量
      "status":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
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
   "weightUnit": "",//重量单位;ton(吨);rise(升)
   "weightUnitCN": "",//重量单位CN;ton(吨);rise(升)
   "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "tenderWayCN":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "payTypeCN":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "mulShipment":true,//多点装货;true:表示是多点装货;false:否
   "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
   "matterPrice":12, //发标价
   "realPrice":34, //中标价
   "marketPrice":23, //市场价
   "priceUnit":"", //价格单位;price_yuan(人民币);price_dollar(美元)
   "priceUnitCN":"", //价格单位CN;price_yuan(人民币);price_dollar(美元)
   "priceType":"", //价格类型;fixed_price(包车固定价);perton_price(每吨运费单价);
   "priceTypeCN":"", //价格类型CN;fixed_price(包车固定价);perton_price(每吨运费单价);
   "startProvince":"",//出发省
   "startCity":"",//出发市
   "startCountry":"",//出发县
   "endProvince":"",//到达省
   "endCity":"",//到达市
   "endCountry":"",//到达县
   "line":"",//路线
   "canShare":true,//是否拼单;true是;false否;
   "status":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
   "statusCN":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
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
   "weightUnit": "",//重量单位;ton(吨);rise(升)
   "weightUnitCN": "",//重量单位CN;ton(吨);rise(升)
   "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "tenderWayCN":"",//发标方式;招标(tender_mark);抢标(bid_mark)
   "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "payTypeCN":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
   "mulShipment":true,//多点装货;true:表示是多点装货;false:否
   "mulShipmentList":[
         {
           "id":1,//装货ID
           "longitude":,//经度
           "latitude":,//纬度
           "province":"",//省
           "city":"",//市
           "county":"",//县
           "addressDetail":"",//详细地址
           "qty":,//装卸数量
           "type":"",//类型;loading_type(装货类型);unloading_type(卸货类型)
           "remark":"",//说明
           "status":"",//状态;noting_loading(未装货);haved_loading(已装货)
           "loadDate":"",//装卸日期
           "endDate":""//截止日期
         }
   ]
   "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
   "mulUnloadList":[
       {
                 "id":2,//卸货ID
                 "longitude":,//经度
                 "latitude":,//纬度
                 "province":"",//省
                 "city":"",//市
                 "county":"",//县
                 "addressDetail":"",//详细地址
                 "qty":,//装卸数量
                 "type":"",//类型;loading_type(装货类型);unloading_type(卸货类型)
                 "remark":"",//说明
                 "status":"",//状态;noting_unloading(未卸货);haved_unloading(已卸货)
                 "loadDate":"",//装卸日期
                 "endDate":""//截止日期
               }
   ]
   "packaged":true, //是否有包装;true:表示是;false:否
   "matterPrice":12, //发标价
   "realPrice":34, //中标价
   "marketPrice":23, //市场价
   "priceUnit":"", //价格单位;price_yuan(人民币);price_dollar(美元)
   "priceUnitCN":"", //价格单位CN;price_yuan(人民币);price_dollar(美元)
   "priceType":"", //价格类型;fixed_price(包车固定价);perton_price(每吨运费单价);
   "priceTypeCN":"", //价格类型CN;fixed_price(包车固定价);perton_price(每吨运费单价);
   "start_province":"",//出发省
   "start_city":"",//出发市
   "start_country":"",//出发县
   "end_province":"",//到达省
   "end_city":"",//到达市
   "end_country":"",//到达县
   "line":"",//路线
   "canShare":true,//是否拼单;true是;false否;
   "status":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
   "statusCN":"", //状态;1:草稿(source_draft)2:审核中(wait_audit);3:竞标中(source_up);4:审核拒绝(source_refuse);5:中标(source_winning);6:已过期(source_expired);
   "arrivalDate":"", //送达日期
   "endDate":"",//截止日期
   "remark":"",//备注
   "createDate":"" //创建日期
}
}
```


### 1.发布货源

*** 描述:只有认证过后的个人/企业才能发布货源,货源分为招标和抢标.


**URL**
>/goodsSource/pushResource



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	   "name": "",//货物介质
        "logo": 123,//logo头像
        "qty": ,//数量
        "weightUnit": "",//重量单位;ton(吨);rise(升)
        "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
        "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
        "mulShipment":true,//多点装货;true:表示是多点装货;false:否
        "mulShipmentList":[
              {
                "longitude":,//经度
                "latitude":,//纬度
                "province":"",//省
                "city":"",//市
                "county":"",//县
                "addressDetail":"",//详细地址
                "qty":,//装卸数量
                "type":"",//类型;loading_type(装货类型);unloading_type(卸货类型)
                "remark":"",//说明
                "status":"",//状态;noting_loading(未装货);haved_loading(已装货)
                "loadDate":"",//装卸日期
                "endDate":""//截止日期
              }
        ]
        "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
        "mulUnloadList":[
            {
                      "longitude":,//经度
                      "latitude":,//纬度
                      "province":"",//省
                      "city":"",//市
                      "county":"",//县
                      "addressDetail":"",//详细地址
                      "qty":,//装卸数量
                      "type":"",//类型;loading_type(装货类型);unloading_type(卸货类型)
                      "remark":"",//说明
                      "status":"",//状态;noting_unloading(未卸货);haved_unloading(已卸货)
                      "loadDate":"",//装卸日期
                      "endDate":""//截止日期
                    }
        ]
        "packaged":true, //是否有包装;true:表示是;false:否
        "matterPrice":12, //发标价
        "realPrice":34, //中标价
        "marketPrice":23, //市场价
        "priceUnit":"", //价格单位;price_yuan(人民币);price_dollar(美元)
        "priceType":"", //价格类型;fixed_price(包车固定价);perton_price(每吨运费单价);
        "startProvince":"",//出发省
        "startCity":"",//出发市
        "startCountry":"",//出发县
        "endProvince":"",//到达省
        "endCity":"",//到达市
        "endCountry":"",//到达县
        "line":"",//路线
        "canShare":true,//是否拼单;true是;false否;
        "arrivalDate":"", //送达日期
        "endDate":"",//截止日期
        "remark":"",//备注
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


### 1.取消发布

*** 描述:对于正在进行中(审核通过)货源可以取消,货源取消后,将货源推送给车主的消息作废.


**URL**
>/goodsSource/cancelResource



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	  "id":23 //货源ID
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


### 1.货源修改

*** 描述:针对后台发布的货源,没有上架的货源可以修改(1:拒绝(product_refuse);下架(product_down);4:过期(product_expired));

已上架的货源只能修改货源价格.


**URL**
>/goodsSource/updateSource


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	         "id":123,  //货源Id
  	         "name": "",//货物介质
             "logo": 123,//logo头像
             "qty": ,//数量
             "weightUnit": "",//重量单位;ton(吨);rise(升)
             "tenderWay":"",//发标方式;招标(tender_mark);抢标(bid_mark)
             "payType":"",//付款方式;1:线下支付(offline_pay);2:微信支付(wechat);3:支付宝支付(alipay);4:银行卡支付(bankpay)
             "mulShipment":true,//多点装货;true:表示是多点装货;false:否
             "mulShipmentList":[
                   {
                     "id":1,//装货ID
                     "longitude":,//经度
                     "latitude":,//纬度
                     "province":"",//省
                     "city":"",//市
                     "county":"",//县
                     "addressDetail":"",//详细地址
                     "qty":,//装卸数量
                     "type":"",//类型;loading_type(装货类型);unloading_type(卸货类型)
                     "remark":"",//说明
                     "status":"",//状态;noting_loading(未装货);haved_loading(已装货)
                     "loadDate":"",//装卸日期
                     "endDate":""//截止日期
                   }
             ]
             "mulUnload":true, //多点卸货;true:表示是多点卸货;false:否
             "mulUnloadList":[
                 {
                           "id":2,//卸货ID
                           "longitude":,//经度
                           "latitude":,//纬度
                           "province":"",//省
                           "city":"",//市
                           "county":"",//县
                           "addressDetail":"",//详细地址
                           "qty":,//装卸数量
                           "type":"",//类型;;loading_type(装货类型);unloading_type(卸货类型)
                           "remark":"",//说明
                           "status":"",//状态;noting_unloading(未卸货);haved_unloading(已卸货)
                           "loadDate":"",//装卸日期
                           "endDate":""//截止日期
                         }
             ]
             "packaged":true, //是否有包装;true:表示是;false:否
             "matterPrice":12, //发标价
             "realPrice":34, //中标价
             "marketPrice":23, //市场价
             "priceUnit":"", //价格单位;price_yuan(人民币);price_dollar(美元)
             "priceType":"", //价格类型;fixed_price(包车固定价);perton_price(每吨运费单价);
             "startProvince":"",//出发省
             "startCity":"",//出发市
             "startCountry":"",//出发县
             "endProvince":"",//到达省
             "endCity":"",//到达市
             "endCountry":"",//到达县
             "line":"",//路线
             "canShare":true,//是否拼单;true是;false否;
             "arrivalDate":"", //送达日期
             "endDate":"",//截止日期
             "remark":""//备注
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


### 1.货源删除

*** 描述:针对后台发布的货源,没有上架的货源可以删除(1:拒绝(product_refuse);下架(product_down);4:过期(product_expired))


**URL**
>/goodsSource/deleteSource


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	         "id":123  //货源Id
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


### 1.邀请报价

*** 描述:针对货主发布的招标,按一定规则推送给车主,车主应标后,货主邀请车主报价(将数据写入到报价表中),返回车主报价单,货主根据报价单选择合适的车主
进行中标.


**URL**
>/goodsSource/invitationOffer


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	         "cargoIds":[123,68,98],  //货源Ids集合
  	       	 "carIds":[
              		  34,94,456
              		] //车主Ids;邀请多个车主报价
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


### 1.创建运单(货主)

*** 描述:针对招标方式,其订单是有货主创建,抢标方式订单是由车主创建;货主查看车主的报价信息,选择合适的车主进行下单,生成订单的状态为待
生成订单,需要车主分配司机与路线.


**URL**
>/font/waybill/createIndent


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                  "billNo": "S20181224123457",//订单编号
                  "advanceMoeny": 1121,//预付金额
                  "finalMoney": 1224,//尾款
                  "totalMoney": 2345,//总金额
                  "remark": "",//备注
                  "qty": 23,//数量
                  "payWay":"",//付款类型;货到付款(arrival_pay);尾款(tail_pay);首付(first_pay)
                  "weightUnit": ,//重量单位;ton(吨);rise(升)
                  "status":""//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
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



### 1.运单分配(车主)

*** 描述:货主创建运单(待生成运单),需要车主分配司机车辆以及路线.


**URL**
>/font/waybill/distribueIndent


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                  "id":1,//运单Id
                  "billNo": "S20181224123457",//订单编号
                  "advanceMoeny": 1121,//预付金额
                  "finalMoney": 1224,//尾款
                  "totalMoney": 2345,//总金额
                  "remark": "",//备注
                  "qty": 23,//数量
                  "weightUnit": ,//重量单位
                  "status":"",//运单状态;1:待生成运单(await_generate);2:待确定运单(await_determine);3:待装货(await_loading);4:待收货(await_accept);5:已签收待支付(sign_paied);6:已支付待评价(is_evaluation);7:已完成(completed)
                  "waybillDetails":[     //订单详情
                    {
                      "billNo": "S20181224123457",//订单编号
                      "carId":,//车辆Id
                      "driverId":,//司机Id
                      "supercargoId":,//压货人Id
                      "qty":,//运输数量
                      "weightUnit":,//重量单位
                      "flowName":"",//进度阶段名称
                      "billLocations":[  //运单装卸地
                          {
                            "waybillDetailId":,//运单详情ID
                            "waybillId":2,//运单Id
                            "longitude":104.123,//经度
                            "latitude":134.789,//纬度
                            "province":"",//省
                            "city":"",//市
                            "county":"",//县
                            "addressDetail":"",//地址详情
                            "qty":678,//装卸数量
                            "weightUnit":"",//重量单位
                            "type":"",//类型;noting_loading(未装货);haved_loading(已装货
                            "seqNo":2,//序号
                            "remark":"",//说明
                            "loadDate":"",//装卸日期
                            "endDate":"",//截止日期
                            "status":""//状态
                           }
                        ]
                      }
                  ]
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



### 1.待确定运单

*** 描述:货主进入订单详情页面，可查看运单中的中标车主报价信息、车辆信息、司机信息等，并且等待车主或司机确定运单;运单需要货主确认.


**URL**
>/font/waybill/confirmIndent


**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                  "id":1,//运单Id
                  "billNo": "S20181224123457",//订单编号
                  "advanceMoeny": 1121,//预付金额
                  "finalMoney": 1224,//尾款
                  "totalMoney": 2345,//总金额
                  "remark": "",//备注
                  "qty": 23,//数量
                  "weightUnit": ,//重量单位;ton(吨);rise(升)
                  "status":"",//2:待确定运单(await_determine)
                  "waybillDetails":[     //订单详情
                    {
                      "billNo": "S20181224123457",//订单编号
                      "carId":,//车辆Id
                      "driverId":,//司机Id
                      "supercargoId":,//压货人Id
                      "qty":,//运输数量
                      "weightUnit":,//重量单位;ton(吨);rise(升)
                      "flowName":"",//进度阶段名称
                      "billLocations":[  //运单装卸地
                          {
                            "waybillDetailId":,//运单详情ID
                            "waybillId":2,//运单Id
                            "longitude":104.123,//经度
                            "latitude":134.789,//纬度
                            "province":"",//省
                            "city":"",//市
                            "county":"",//县
                            "addressDetail":"",//地址详情
                            "qty":678,//装卸数量
                            "weightUnit":"",//重量单位;ton(吨);rise(升)
                            "type":"",//类型;noting_loading(未装货);haved_loading(已装货
                            "seqNo":2,//序号
                            "remark":"",//说明
                            "loadDate":"",//装卸日期
                            "endDate":"",//截止日期
                            "status":""//状态;
                           }
                        ]
                      }
                  ]
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



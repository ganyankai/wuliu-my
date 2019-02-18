
**本文档接口提交方式统一为：POST**


### 1.关注列表信息

*** 描述:车主查看关注的路线或货主.


**URL**
>/focusOn/page

**请求参数**

``` json
{
  "openid": "string",
  "page":{"pageNum":1, "pageSize":10},
  "params": 
{
   "focusType": "",//(选传)(string)关注类型;关注的是路线还是车主;
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
                            "id": 8,//账号Id
                            "focuserId": 5,//关注人Id
                            "beFocuserId": 15,//被关注的人Id
                            "focusType": "",//关注类型;关注的是路线还是货主;
                            "createDate":"" //创建时间
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



### 1.添加关注路线或车主

*** 描述:添加要关注的路线或车主


**URL**
>/focusOn/add



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
                               "focuserId": 5,//关注人Id
                               "beFocuserId": 15,//被关注的人Id
                               "focusType": "",//关注类型;关注的是路线还是车主;
                               "createDate":"" //创建时间
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



### 1.关注详情

*** 描述:查看关注的详情,如果关注的路线,则返回此路线相关货源信息列表,如果关注的是货主,则返回当前货主发布的货源信息列表.

**URL**
>/focusOn/detail

   

**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8//如果关注的是货主Id(查看货主发布的货源) 
  	 //如果关注的是路线(通过路线查找货源) 
  	 "startPlace":"",//出发地   
  	 "targetPlace":""//目的地            
  },
   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
}
```


============返回货源信息列表======================

**响应示例**

``` json
{
    "code": 1,
    "msg": "success",
    "data":
    [
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
         "startPlace":"",//出发地
         "endPlace":"",//目的地
         "line":"",//路线
         "canShare":true,//是否拼单;true是;false否;
         "status":"", //状态;1:拒绝(product_refuse);2:上架(product_up);3:下架(product_down);4:过期(product_expired)
         "arrivalDate":"", //送达日期
         "endDate":"",//截止日期
         "remark":"",//备注
         "createDate":"" //创建日期
    ]
}
```





### 1.关注删除

*** 描述:删除关注的路线或车主.

**URL**
>/focusOn/delete



**请求参数**

``` json
{
  "openid": "string",
  "params": 
{
  	 "id": 8 //关注Id
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
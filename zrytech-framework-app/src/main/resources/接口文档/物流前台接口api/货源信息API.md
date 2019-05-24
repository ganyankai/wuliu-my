



### 1.邀请报价

*** 描述:针对货主发布的招标,按一定规则推送给车主,车主应标后,货主邀请车主报价(将数据写入到报价表中),返回车主报价单,货主根据报价单选择合适的车主
进行中标.


**URL**
>/goodsSource/invitationOffer


**请求参数**

	{
	  	"params": 
		{
  	         "cargoIds":[123,68,98],  //货源Ids集合
  	       	 "carIds":[
              		  34,94,456
              		] //车主Ids;邀请多个车主报价
	  	},
	   "device":2 //设备类型1 manage，2PC，3 andriod，4 ios，5 h5
	}

**响应示例**

	{
		"code":1,
		"msg":"success"
	}





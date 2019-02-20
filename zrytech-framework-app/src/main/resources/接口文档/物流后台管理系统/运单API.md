# 运单后台管理系统接口 #

** 作者：CAT **

<br>

    运单  waybill

    "id": "",                  int 
    "billNo": "",              str 订单编号
    "cargoId": "",             int 货源Id 
    "cargoOwnnerId": "",       int 货主Id
    "carId": "",               int 车主Id
    "advanceMoeny": "",        str 预付款
    "finalMoney": "",          str 尾款
    "totalMoney": "",          str 总金额
    "payType": "",             str 支付类型
    "payWay": "",              str 付款方式
    "remark": "",              str 备注
    "qty": "",                 int 数量
    "weightUnit": "",          str 重量单位
    "billType": "",            str 运单类型
    "proofImgs": "",           str 收货凭证
    "status": "",              str 状态
    "createDate": "",          datetime 创建时间

<br>

    运单详情 waybillDetail

    "id": "",                  int 
    "billNo": "",              str 运单编号
    "carId": "",               int 车辆Id
    "driverId": "",            int 司机Id
    "supercargoId": "",        int 压货人Id
    "qty": "",                 int 运输数量
    "weightUnit": "",          str 重量单位
    "flowName": "",            str 进度阶段名称, 待确认，待发货，待收货
    "createDate": ""           str 创建时间

<br>

    运单装卸地   billLocation

    "id": "",                    int 
    "waybillDetailId": "",       int 运单明细ID
    "waybillId": "",             int 运单Id
    "longitude": "",             str 经度
    "latitude": "",              str 纬度
    "province": "",              str 省份
    "city": "",                  str 城市
    "county": "",                str 县
    "addressDetail": "",         str 地址详情
    "qty": "",                   int 装卸数量
    "weightUnit": "",            str 重量单位
    "type": "",                  str '1，装车 2，卸车',
    "seqNo": "",                 int 序号
    "remark": "",                str 说明
    "loadDate": "",              str 装卸日期
    "endDate": "",               str 截止日期
    "status": "",                str 状态 1，已拉 2 未拉',
    "createDate": "",            str 创建日期

<br>
<br>
<br>


## 1.运单列表分页 ##

**URL**
>admin/waybill/page

**请求参数**

    {
    	"params": {
            "id":"1",                     (选填)（int）运单Id
    		"carOwnerName":"1",           (选填)（str）车主企业名称
    		"carOwnerId":"1",             (选填)（int）车主Id
    		"cargoOwnerName":"1",         (选填)（str）货主企业名称
    		"cargoOwnerId":"2",           (选填)（int）货主Id
    		"cargoId": "1",               (选填)（int）货源Id
    		"billNo": "1",                (选填)（str）运单号
    		"payType": "1",               (选填)（str）支付类型
    		"payWay":"1",                 (选填)（str）支付方式
    		"billType":"1",               (选填)（str）运单类型
    		"status":"1"                  (选填)（str）运单状态
    	},
        "page":{
            "pageNum": "",
            "pageSize":""
        },
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "total": 1,
            "list": [
                {
                    "id": 1,
                    "billNo": "12580",
                    "cargoId": 1,
                    "cargo": null,
                    "cargoOwnnerId": 2,
                    "cargoOwnerName": "货主李四",           【货主企业名称】
                    "carOwnnerId": 1,
                    "carOwnerName": "车主张三",             【车主企业名称】
                    "advanceMoeny": 1,
                    "finalMoney": 1,
                    "totalMoney": 1,
                    "payType": "1",
                    "payTypeCN": null,
                    "payWay": "1",
                    "payWayCN": null,
                    "remark": "1",
                    "qty": 1,
                    "weightUnit": "1",
                    "billType": "1",
                    "billTypeCN": null,
                    "proofImgs": "1",
                    "status": "1",
                    "statusCN": null,
                    "createDate": "2019-02-20 15:42:18",
                    "waybillDetails": null
                }
            ],
            "pageNo": 1,
            "pageSize": 2
        }
    }

<br>
<br>
<br>


## 2.运单详情 ##

**URL**
>admin/waybill/details

**请求参数**

    {
    	"params": {
            "id": ""               (必填)（int）运单Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {       
            "id": 1,                                    【运单信息】
            "billNo": "12580",
            "cargoId": 1,
            "cargo": {                                  【货源信息】
                "id": 1,
                "name": "天然气",
                "logo": "1",
                "qty": 1,
                "weightUnit": "1",
                "weightUnitCN": null,
                "tenderWay": "1",
                "tenderWayCN": null,
                "payType": "1",
                "payTypeCN": null,
                "mulShipment": false,
                "mulShipmentList": null,
                "mulUnload": false,
                "mulUnloadList": null,
                "packaged": false,
                "matterPrice": 1,
                "realPrice": 1,
                "marketPrice": 1,
                "priceUnit": "1",
                "priceUnitCN": null,
                "priceType": "1",
                "priceTypeCN": null,
                "startProvince": "1",
                "startCity": "1",
                "startCountry": "1",
                "endProvince": "1",
                "endCity": "1",
                "endCountry": "1",
                "line": "1",
                "canShare": false,
                "carType": "1",
                "carTypeCN": null,
                "status": null,
                "statusCN": null,
                "arrivalDate": "2019-02-20 14:11:59",
                "pickupDate": "2019-02-20 14:11:57",
                "endDate": "2019-02-20 14:12:04",
                "remark": "1",
                "createBy": 1,
                "createDate": "2019-02-20 14:12:08"
            },
            "cargoOwnnerId": 2,
            "cargoOwnerName": "货主李四",                【货主企业名称】
            "carOwnnerId": 1,
            "carOwnerName": "车主张三",                  【车主企业名称】
            "advanceMoeny": 1,
            "finalMoney": 1,
            "totalMoney": 1,
            "payType": "1",
            "payTypeCN": null,
            "payWay": "1",
            "payWayCN": null,
            "remark": "1",
            "qty": 1,
            "weightUnit": "1",
            "billType": "1",
            "billTypeCN": null,
            "proofImgs": "1",
            "status": "1",
            "statusCN": null,
            "createDate": "2019-02-20 15:42:18",
            "waybillDetails": [                     【运单项信息】
                {
                    "id": 1,
                    "billNo": "12580",
                    "carId": 1,
                    "driverId": 1,
                    "supercargoId": 1,
                    "qty": 1,
                    "weightUnit": "1",
                    "flowName": "1",
                    "createDate": "2019-02-20 15:43:26",
                    "billLocations": [                      【运单项装卸地】
                        {
                            "id": 1,
                            "waybillDetailId": 1,
                            "waybillId": 1,
                            "longitude": 1,
                            "latitude": 1,
                            "province": "1",
                            "city": "1",
                            "county": "1",
                            "addressDetail": "1",
                            "qty": 1,
                            "weightUnit": "1",
                            "type": "1",
                            "seqNo": 1,
                            "remark": "1",
                            "loadDate": "2019-02-20 16:44:03",
                            "endDate": "2019-02-20 15:44:05",
                            "status": "1",
                            "createDate": "2019-02-20 15:44:08"
                        }
                    ]
                }
            ]
        }
    }

<br>
<br>
<br>
   
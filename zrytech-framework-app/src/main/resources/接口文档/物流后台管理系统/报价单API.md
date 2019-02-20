# 报价单后台管理系统接口 #

** 作者:CAT **

    报价单 cargoMatter 

<br>
<br>
<br>


## 1.报价单列表分页 ##

**URL**
>admin/cargoMatter/page

**请求参数**

    {
    	"params": {
            "id": "",               (选填)（int）报价单Id
            "cargoId": "",          (选填)（int）车源Id
            "carOwnerId": "",       (选填)（int）车主Id
            "carOwnerName": "",     (选填)（str）车主企业名称
            "status": ""            (选填)（str）状态
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
                    "cargoId": 1,
                    "cargo": null,                      【车源信息，暂无数据返回，请忽略】
                    "carOwnnerId": 1,
                    "carOwnerName": "车主张三",          【车主企业名称】
                    "matterPrice": 100,
                    "status": "0",
                    "statusCN": "待处理",
                    "loadDate": "2019-02-20 14:08:58",
                    "createDate": "2019-02-20 14:08:59"
                }
            ],
            "pageNo": 1,
            "pageSize": 2
        }
    }

<br>
<br>
<br>


## 2.报价单详情 ##

**URL**
>admin/cargoMatter/details

**请求参数**

    {
    	"params": {
            "id": ""              (必填)（int）报价单Id
    	},
    	"token": ""
	}

**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "id": 1,
            "cargoId": 1,
            "cargo": {                              【车源信息】
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
            "carOwnnerId": 1,
            "carOwnerName": "车主张三",                 【车主企业名称】
            "matterPrice": 100,
            "status": "0",
            "statusCN": "待处理",
            "loadDate": "2019-02-20 14:08:58",
            "createDate": "2019-02-20 14:08:59"
        }
    }

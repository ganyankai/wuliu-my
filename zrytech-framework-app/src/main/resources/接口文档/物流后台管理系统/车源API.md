# 车源后台管理系统接口 #

** 作者：CAT**


<br>
<br>
<br>


## 1.车源列表分页 ##

**URL**
>admin/carSource/page

**请求参数**

    {
    	"params": {
            "id": "",               (选填)（int）车源Id
            "createBy": "",         (选填)（int）车主Id
            "carOwnerName": "",     (选填)（str）车主企业名称
            "status": "",           (选填)（str）车源状态
            "carType": ""           (选填)（str）车辆类型
    	},
        "page":{
            "pageNum": "",
            "pageSize": ""
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
                    "id": 1,                                    【车源基本信息】
                    "createBy": 1,
                    "carOwnerName": "车主张三",                   // 车主企业名称
                    "carType": "trailer",
                    "carTypeCN": "车辆类型，待处理",
                    "qty": 1,
                    "status": "up",
                    "statusCN": "状态，待处理",
                    "createDate": "2019-02-15 18:13:18",
                    "carRecordPlaces": [                        【车源路线列表】
                        {
                            "id": 1,
                            "carSourceId": 1,
                            "startProvince": "出发省",
                            "startCity": "出发市",
                            "startCountry": "出发县",
                            "endProvince": "达到省",
                            "endCity": "达到市",
                            "endCountry": "达到县"
                        },
                        {
                            "id": 2,
                            "carSourceId": 1,
                            "startProvince": "出发省2",
                            "startCity": "出发市2",
                            "startCountry": "出发县2",
                            "endProvince": "达到省2",
                            "endCity": "达到市2",
                            "endCountry": "达到县2"
                        }
                    ],
                    "carSourceCars": null                       【车源车辆列表，暂无返回数据，请忽略】
                }
            ],
            "pageNo": 1,
            "pageSize": 2
        }
    }


<br>
<br>
<br>


## 2.车源详情 ##

**备注**

    返回数据包含
        1.基本信息
        2.路线信息
        3.车辆信息

**URL**
>admin/carSource/details

**请求参数**

    {
    	"params": {
            "id": ""                (必填)（int）车源Id
    	},
    	"token": ""
	}


**响应示例**

    {
        "code": 1,
        "msg": "success",
        "data": {
            "id": 1,                                【车源基本信息】
            "createBy": 1,
            "carOwnerName": "车主张三",
            "carType": "trailer",
            "carTypeCN": "车辆类型，待处理",
            "qty": 1,
            "status": "up",
            "statusCN": "状态，待处理",
            "createDate": "2019-02-15 18:13:18",
            "carRecordPlaces": [                    【车源路线】
                {
                    "id": 1,
                    "carSourceId": 1,
                    "startProvince": "出发省",
                    "startCity": "出发市",
                    "startCountry": "出发县",
                    "endProvince": "达到省",
                    "endCity": "达到市",
                    "endCountry": "达到县"
                },
                {
                    "id": 2,
                    "carSourceId": 1,
                    "startProvince": "出发省2",
                    "startCity": "出发市2",
                    "startCountry": "出发县2",
                    "endProvince": "达到省2",
                    "endCity": "达到市2",
                    "endCountry": "达到县2"
                }
            ],
            "carSourceCars": [                      【车源车辆，车辆，司机，压货人详细信息暂未绑定】
                {
                    "id": 1,
                    "carSourceId": 1,
                    "carId": 1,
                    "driverId": 1,
                    "supercargoId": 1
                }
            ]
        }
    }

<br>
<br>
<br>


## 3.车源审核 ##

**URL**
>/admin/carSource/check

**备注**


**请求参数**

    {
    	"params": {
            "businessId": "",     （必填）（int）车源Id
            "content": "",        （必填）（str）审核意见
            "result": ""          （必填）（str）审核结果[pass | no_pass]
    	},
    	"token": ""
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": "审核成功"
    }


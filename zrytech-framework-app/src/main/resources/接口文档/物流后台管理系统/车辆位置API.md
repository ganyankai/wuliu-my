# 车辆位置 #

** 作者: CAT **

## 字段说明 ##

    car_location
    
    id                  （int）主键
    carNo               （str）车牌号
    currentLongitude    （str）经度
    currentLatitude     （str）纬度
    currentAddress      （str）当前位置
    createDate          （str）创建日期

<br>
<br>
<br>

# 车辆位置后台管理系统接口 #


## 1.车辆位置分页 ##

**URL**
>admin/carLocation/page

**备注**

**请求参数**

    {
    	"params": {
            "carNo": ""              （选填）（str）车牌号（采用模糊搜索）
    	},
        "page": {
    		"pageSize": "10",
    		"pageNum": "1"
    	},
    	"token": ""
	}

**响应示例**

    {
	    "code": 1,
	    "msg": "success",
	    "data": {
	        "total": 2,
	        "list": [
	            {
	                "id": 2,
	                "carNo": "2",
	                "currentLongitude": 2,
	                "currentLatitude": 2,
	                "currentAddress": "2",
	                "createDate": "2019-02-15 09:44:23"
	            }
	        ],
	        "pageNo": 1,
	        "pageSize": 1
	    }
	}
	
	
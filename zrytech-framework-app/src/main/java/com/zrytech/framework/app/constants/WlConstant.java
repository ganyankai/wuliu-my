package com.zrytech.framework.app.constants;

public interface WlConstant {


    //redis key 相关常量
    String TOKEN_PREFIX = "token_";//代表token的前缀
    long TOKEN_EXPIRE = 60 * 60 * 24;//token 有效期默认一个小时

    String STATUS="pass";

    public static final String DEVICE_CP="device_cp";
    /**
     * USERSTATUS:状态;1:审核中
     */
    public static final String TRADE_SHENHEZHONG = "shen_he_zhong";

   //windows主机的地址
    public static final String FFMPEG_PATH = "F:/ffmpeg/ffm/bin/ffmpeg.exe";

    //unix主机ffmpeg:code
    public static final String UNIX_FFMPEG = "unix_ffmpeg_path";

    //unix主机ffmpeg:key
    public static final String UNIX_FFMPEG_KEY = "ffmpeg_path";

    /**
     * USERSTATUS:状态;2:拒絕
     */
    public static final String TRADE_JUJUE = "refuse";


    /**
     * LEVEL:等级;1~6级
     */
    public static final String TRADE_LEVEL = "level";

    /**
     * TRADE_TRADES:用户类型 贸易商
     */
    public static final String TRADE_USERTYPE = "user_type";

    /**
     * SHOPTYPE:商户类型;贸易商和炼油厂
     */
    public static final String TRADE_SHOPTYPE = "shop_type";

    //用户登录次数超限制重试时间
    String CREDENTIAL_PREFIX = "credential_";//代表用户登陆次数前缀
    long CREDENTIAL_EXPIRE = 60 * 30L;//半个小时的重试时间

    //微信accesstoken保存时间
    Long ACCESSTOKEN_EXPIRE = 60 * 90L;//一个半小时超时间

    //验证码超时时间
    String VERFI_CODE_PREFIX = "verfiCode_";
    Long VERFI_CODE_EXPIRE = 60 * 5L;//保存5分钟(60);

    Long ALIYUN_CODE_EXPIRE = 60 * 1L;//保存1分钟(60);

    //上传相关常量
    String FILE_SEPARATOR = "/";//文件路径分割符号

    //密码机密相关常量
    String ALGORITHM_NAME = "md5";
    int HASH_ITERATIONS = 2;
    String SALT = "gjakg;jdfdasjk%^$32";

    //文件类型常量
    String picture = ".bmp,.jpg,.png,.tiff,.gif,.pcx,.tga,.exif,.fpx,.svg,.psd,.cdr,.pcd,.dxf,.ufo,.eps,.ai,.raw,.jpeg";
    String video = ".avi,.wmv,.mpeg,.mp4,.mov,.mkv,.flv,.f4v,.m4v,.rmvb,.rm";
    String text = ".txt";

    String PIRCTURE_DIR = FILE_SEPARATOR + "img" + FILE_SEPARATOR;
    String VIDEO_DIR = FILE_SEPARATOR + "video" + FILE_SEPARATOR;
    String TEXT_DIR = FILE_SEPARATOR + "txt" + FILE_SEPARATOR;
    String OTHER_DIR = FILE_SEPARATOR + "other" + FILE_SEPARATOR;

    //图片压缩尺寸 单位是像素
    int WIDTH = 320;
    int HEIGHT = 240;

    /**
     * @Description: 权限默认顶级
     * @Author: LiuChao
     * @Date: 2018/5/4 16:44
     */
    Integer PERMISSION_PARENT_ID = 0;

    //ftp root 目录
    String FTP_ROOT_DIR = "/home/zry";


    String VIDEO_FILE_PATH="/home/zry/img";

    //放开权限controller的方法
    String OPEN_PERMISSION_METHOD = "upload,view,viewThumb,download,login,forget,uploadContract,/sign/notice,handSignStatus," +
            "/areaPriceRecordAPI/brentandwti/get,/areaPriceRecordAPI/priceTrend,/customer/forget,/crudeoil/page," +
            "/areaPriceRecordAPI/categoryNameList,findList,cityList,/tx/sendAliyunCode,/shopcarAPI/count," +
            "/areaPriceRecordAPI/exchangeRate/get,/areaPriceRecordAPI/latestPrice,/tx/checkCode,/betaBrtWtiExcRateAPI/get,/betaBrtWtiExcRateAPI/getAll,/betaRealTimeOilPriceAPI/get," +
            "/notice/noticePage,/notice/get,/information/inforPage,/information/get,/crudeoil/crudePage," +
            "/receptionroom/loungePage,/receptionroom/get,getExchangeRate,/receptionroom/articlebyid,/crudeoil/articlebyid," +
            "/product/childName,/customer/login,/article/articleById,/trade/get,/category/tree,/customer/personRegister," +
            "/product/param,/place/portList,/articleComment/articlePage,/product/list,/customer/checkToken," +
            "/message/countMessage,/product/categoryInit,/product/search,/product/get,/bannerPicture/page,findPage,/zipDowload," +
            "/customer/logout,/customer/register,/trade/register,getBRTAndBRT,/version/updateVersion,/customer/findByTelToOrgName,/trade/findByTelToOrgName," +
            "/customer/tree,logout,/dict/getListValue,/tx/sendVerifCode,/tx/code";


    //角色权限code方法
    String RULE_PERSSION_CODE = "add,edit,delete,audit,detail,addPort,publish,update,authorize";

    String FIND_BY_PAGE="page";


    String OPEN_PERMISSION_URL = "/customer/regist";

    /**
     * @Description: 用户初始密码
     * @Author: LiuChao
     * @Date: 2018/5/11 17:24
     */
    String INIT_PASS = "123456";


    /**
     *
     */
    String DATE_PATTEN = "yyyy-MM-dd HH:mm:ss";


    String DICT_CATEGORY_REIDS = "dict_category_reids_key";

    String DICT_VALUE_REIDS = "dict_value_reids_key";


    /**
     * @Description: 创建角色时，如果是平台管理员创建，则 createBy默认为0
     * @Author: LiuChao
     * @Date: 2018/5/4 16:44
     */
    Integer ROLE_CREATE_BY = 0;


    /**
     * @Description: 初始化监听器菜单存入redis缓存
     * @Author: LiuChao
     * @Date: 2018/5/4 16:44
     */
    String ADMIN_PERMISSION = "admin_permission";

    String TRADE_PERMISSION = "trade_permission";

}

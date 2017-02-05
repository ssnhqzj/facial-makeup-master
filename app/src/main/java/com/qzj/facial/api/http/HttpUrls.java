package com.qzj.facial.api.http;

/**
 * 用户模块接口地址
 */
public class HttpUrls {

    /**
     * 登录
     */
    public static final String LOGIN = "rest/auth/login";

    /**
     * 重置密码
     */
    public static final String RESET_PWD = "rest/auth/updatePassword";

    /**
     * APP_STORE动态参数获取地址
     */
    public static final String API_STORE_PARAM = "http://appapi.evmonitor.stepway.me/apistore_url.php";



    /**
     * 获取照明策略
     */
    public static final String GET_POLICY = "rest/street/getPoleLightingMode/{streetId}";

    /**
     * 获取手动模式规则列表
     */
    public static final String GET_MANUAL_RULE_LIST = "rest/street/strategyList";

    /**
     * 获取街道列表
     */
    public static final String GET_STREET_LIST = "rest/street/getStreetList";

    /**
     * 街道列表
     */
    public static final String GET_BRIEF_STREET_LIST = "rest/street/getBriefStreetList";

    /**
     * 设置照明模式--节能策略
     */
    public static final String SET_AUTO_POLICY = "rest/street/setStreetPolicy";

    /**
     * 设置照明模式--手动策略
     */
    public static final String SET_MANUAL_POLICY = "rest/street/setStreetStrategy";



    /**
     * 单灯设置
     */
    public static final String SET_SINGLEPOLE_STRATEGY = "rest/pole/singlePole/{iMei}/{strategyId}";



    /**
     * 获取街道列表--没有配置光感设备（路灯管理员）
     */
    static final String GET_STREET_LIST_NO_TERMINAL = "rest/assistTerminal/streets";

    /**
     * 绑定街道到光感设备
     */
    static final String SET_BIND_STREET_SENSOR = "rest/assistTerminal/sensorDevice";

    /**
     * 获取光感设备详细信息
     */
    static final String GET_SENSOR_DEVICE_INFO = "rest/assistTerminal/sensorDevice/{id}";

    /**
     * 查询光感设备阈值
     */
    static final String GET_SENSOR_DEVICE_THRESHOLD = "rest/assistTerminal/getSensorDeviceConfig";

    /**
     * 设置光感设备阈值
     */
    static final String SET_SENSOR_DEVICE_THRESHOLD = "rest/assistTerminal/setSensorDevice";


    /**
     * 查询设备类型列表（路灯管理员）
     */
    static final String GET_ALL_TERMINAL = "rest/assistTerminal/sensorDevice";

    /**
     * 编辑光感设备（路灯管理员）
     */
    static final String SET_EDIT_TERMINAL = "rest/assistTerminal/sensorDevice";

    /**
     * 街道详情
     */

    public static final String GET_STREET_DETAIL = "rest/street/streetDetail/{streetId}";

    /**
     * 灯杆排序
     */
    public static final String EDIT_LIGHT_SORT = "rest/pole/editPolesSort";

    /**
     * 新建街道
     */
    public static final String CREATE_STREET = "rest/street/createStreet";

    /**
     * 编辑街道
     */
    public static final String EDIT_STREET = "rest/street/editStreet/{id}";

    /**
     * 编辑灯杆
     */
    public static final String EDIT_POLES = "rest/pole/editPoles";

    /**
     * 删除灯杆
     */
    public static final String DELETE_POLE = "rest/pole/deletePoleByiMei/{iMei}";

    /**
     * 灯控详情
     */

    public static final String GET_LIGHTS_CONTROL_DETAIL = "rest/pole/poleDetail/{iMei}";


    /**
     * 增加路灯
     */

    public static final String ADD_POLE = "rest/pole/addPole";



    /**
     * 对调
     */

    public static final String EXANGE_POLE = "rest/pole/lampOrderExchange/{iMei}";



    /**
     * 快关
     */

    public static final String LAMPSTRATEGY = "rest/pole/oddPole/{iMei}/{isOpen}";



    /**
     * 关键灯
     */
    public static final String SETKEY = "rest/pole/setKeyPole/{iMei}/{isKey}";

    /**
     * 获取节能当月统计（指定街道）
     */
    static final String GET_ENERGY_DAYS_OF_MONTH = "rest/street/energy_month/{streetId}";

    /**
     * 节能往月统计（指定年份-指定街道）
     */
    static final String GET_ENERGY_MONTHS_OF_YEAR = "rest/street/energy_year/{year}/{streetId}";
}

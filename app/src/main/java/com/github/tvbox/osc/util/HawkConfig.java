package com.github.tvbox.osc.util;

/**
 * @author pj567
 * @date :2020/12/23
 * @description: 精简版 - 仅保留直播相关配置
 */
public class HawkConfig {
    // API配置
    public static final String API_URL = "api_url";
    public static final String API_HISTORY = "api_history";
    public static final String HOME_API = "home_api";

    // 直播相关配置
    public static final String LIVE_CHANNEL = "last_live_channel_name";
    public static final String LIVE_CHANNEL_REVERSE = "live_channel_reverse";
    public static final String LIVE_CROSS_GROUP = "live_cross_group";
    public static final String LIVE_CONNECT_TIMEOUT = "live_connect_timeout";
    public static final String LIVE_SHOW_NET_SPEED = "live_show_net_speed";
    public static final String LIVE_SHOW_TIME = "live_show_time";

    // 播放器配置
    public static final String PLAY_TYPE = "play_type";//0 系统 1 ijk 2 exo
    public static final String PLAY_RENDER = "play_render"; //0 texture 2
    public static final String PLAY_SCALE = "play_scale"; //0 texture 2

    // 网络配置
    public static final String DOH_URL = "doh_url";
    public static final String DEBUG_OPEN = "debug_open";
    public static final String PARSE_WEBVIEW = "parse_webview"; // true 系统 false xwalk
    public static final String IJK_CODEC = "ijk_codec";
}

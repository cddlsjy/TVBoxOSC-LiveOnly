package com.github.tvbox.osc.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author pj567
 * @date :2021/1/5
 * @description:
 */
public class SearchReceiver extends BroadcastReceiver {
    public static String action = "android.content.movie.search.Action";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 简化实现，不启动搜索界面
    }
}
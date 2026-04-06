package com.github.tvbox.osc.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tvbox.osc.R;
import com.github.tvbox.osc.api.ApiConfig;
import com.github.tvbox.osc.base.BaseActivity;
import com.github.tvbox.osc.server.ControlManager;
import com.github.tvbox.osc.util.FastClickCheckUtil;
import com.github.tvbox.osc.util.HawkConfig;
import com.orion.leanback.TvGridView;
import com.orion.leanback.V7GridLayoutManager;
import com.orhanobut.hawk.Hawk;

import androidx.annotation.Nullable;
import me.jessyan.autosize.utils.AutoSizeUtils;

/**
 * 直播TV入口页面
 */
public class HomeActivity extends BaseActivity {

    private TvGridView mGridView;
    private TextView tvDate;
    private LinearLayout topLayout;
    private long mExitTime = 0;
    private boolean apiLoaded = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        ControlManager.get().startServer();
        initView();
        loadApiConfig();
    }

    private void loadApiConfig() {
        String apiUrl = Hawk.get(HawkConfig.API_URL, "");
        if (!apiUrl.isEmpty() && !apiLoaded) {
            apiLoaded = true;
            ApiConfig.get().loadConfig(true, new ApiConfig.LoadConfigCallback() {
                @Override
                public void success() {
                    // API加载成功
                }

                @Override
                public void retry() {
                    // 重试
                }

                @Override
                public void error(String msg) {
                    // API加载失败，但不影响界面显示
                }
            }, this);
        }
    }

    private void initView() {
        topLayout = findViewById(R.id.topLayout);
        tvDate = findViewById(R.id.tvDate);
        mGridView = findViewById(R.id.mGridView);

        // 设置顶部标题
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (tvTitle != null) {
            tvTitle.setText("电视直播");
        }

        // 设置直播入口按钮
        LinearLayout liveButton = findViewById(R.id.liveButton);
        if (liveButton != null) {
            liveButton.setVisibility(View.VISIBLE);
            liveButton.requestFocus(); // 默认焦点在直播按钮上
            liveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FastClickCheckUtil.check(v);
                    jumpToLivePlay();
                }
            });
            liveButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        v.animate().scaleX(1.05f).scaleY(1.05f).setDuration(150).start();
                    } else {
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
                    }
                }
            });
        }

        // 设置按钮
        LinearLayout settingButton = findViewById(R.id.settingButton);
        if (settingButton != null) {
            settingButton.setVisibility(View.VISIBLE);
            settingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FastClickCheckUtil.check(v);
                    jumpActivity(SettingActivity.class);
                }
            });
            settingButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        v.animate().scaleX(1.05f).scaleY(1.05f).setDuration(150).start();
                    } else {
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
                    }
                }
            });
        }

        // 更新日期显示
        updateDateTime();
    }

    private void updateDateTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        tvDate.setText(sdf.format(new java.util.Date()));
        // 每秒更新时间
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateDateTime();
            }
        }, 1000);
    }

    private void jumpToLivePlay() {
        Intent intent = new Intent(mContext, LivePlayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // 不再自动跳转，等待用户点击按钮
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime < 2000) {
            super.onBackPressed();
        } else {
            mExitTime = System.currentTimeMillis();
            Toast.makeText(mContext, "再按一次返回键退出应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ControlManager.get().stopServer();
    }
}

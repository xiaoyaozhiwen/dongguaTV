package com.ednovas.donguatv;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 延时执行，作为插件失效时的强制兜底方案
        new android.os.Handler().postDelayed(() -> {
            try {
                // 1. 设置状态栏背景为纯黑
                getWindow().setStatusBarColor(android.graphics.Color.BLACK);
                
                // 2. 获取状态栏高度
                int statusBarHeight = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                }
                
                // 3. 强制给根视图增加 Top Padding
                android.view.View content = findViewById(android.R.id.content);
                if (content != null && statusBarHeight > 0) {
                    // 仅当没有 Padding 时才添加 (避免与插件冲突导致双倍高度)
                    if (content.getPaddingTop() < statusBarHeight) {
                        content.setPadding(0, statusBarHeight, 0, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 300);
    }
}

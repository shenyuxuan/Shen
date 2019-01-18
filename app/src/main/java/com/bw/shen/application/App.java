package com.bw.shen.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by lenovo on 2018-12-28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        CrashReport.initCrashReport(getApplicationContext(), "5840a44bbf", true);

//        HttpNet.init();
    }
}

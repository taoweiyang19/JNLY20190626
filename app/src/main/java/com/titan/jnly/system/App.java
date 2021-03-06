package com.titan.jnly.system;


import android.content.Context;
import android.location.Location;

import com.lib.bandaid.app.BaseApp;
import com.lib.bandaid.data.local.sqlite.proxy.transaction.DbManager;
import com.lib.bandaid.service.imp.ServiceLocation;
import com.tencent.bugly.beta.Beta;
import com.titan.jnly.dbase.DbVersion;
import com.titan.jnly.system.version.bugly.BuglySetting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class App extends BaseApp {

    final String appId = "dccc720c39";

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        BuglySetting.init(this, appId);
        DbManager.dbConfig = new DbVersion(baseApp);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Beta.installTinker();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(this);
    }

    //接收消息
    @Subscribe
    public void onEventMainThread(ServiceLocation serviceLocation) {
        if (serviceLocation == null) return;
        Constant.location = serviceLocation.getLocation();
    }
}

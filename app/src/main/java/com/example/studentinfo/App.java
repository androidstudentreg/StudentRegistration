package com.example.studentinfo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by vinay on 28/6/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

package com.example.shyam.dagger2sample.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.shyam.dagger2sample.DaggerMVVMApplication;

import dagger.android.AndroidInjection;

public class AppInjector {
    private AppInjector() {}

    public static void init(DaggerMVVMApplication daggerMVVMApplication) {
        DaggerAppComponent.builder().application(daggerMVVMApplication)
                .build().inject(daggerMVVMApplication);

        daggerMVVMApplication
                .registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        AndroidInjection.inject(activity);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                });
    }
}

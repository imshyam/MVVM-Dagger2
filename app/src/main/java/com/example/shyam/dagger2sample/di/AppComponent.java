package com.example.shyam.dagger2sample.di;

import android.app.Application;

import com.example.shyam.dagger2sample.DaggerMVVMApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(DaggerMVVMApplication daggerMVVMApplication);
}

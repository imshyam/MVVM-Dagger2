package com.example.shyam.dagger2sample.di;

import android.app.Application;

import com.example.shyam.dagger2sample.DaggerMVVMApplication;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {ViewModelModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(DaggerMVVMApplication daggerMVVMApplication);
}

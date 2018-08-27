package com.example.shyam.dagger2sample.di;

import android.arch.lifecycle.ViewModel;

import com.example.shyam.dagger2sample.viewmodel.RandomUserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RandomUserViewModel.class)
    abstract ViewModel bindRandomUserViewModel(RandomUserViewModel randomUserViewModel);
}

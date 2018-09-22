package com.mindvalley.pinterest.injection.module

import com.mindvalley.pinterest.view.MainActivity
import com.mindvalley.pinterest.view.PinterestListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributePinterestListFragment(): PinterestListFragment

}
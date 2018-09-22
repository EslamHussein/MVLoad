package com.mindvalley.pinterest.injection.module

import com.mindvalley.pinterest.contract.PinterestContract
import com.mindvalley.pinterest.presenter.PinterestPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindProjectsPresenter(presenter: PinterestPresenterImpl): PinterestContract.PinterestPresenter
}
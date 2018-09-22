package com.mindvalley.pinterest.injection.module

import com.mindvalley.mvload.MVLoadClient
import com.mindvalley.pinterest.model.PinterestModel
import com.mindvalley.pinterest.model.PinterestModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ModelModule {

    @Binds
    abstract fun bindPinterestModel(presenter: PinterestModelImpl): PinterestModel


    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMVLoadClint(): MVLoadClient {
            return MVLoadClient()
        }
    }
}
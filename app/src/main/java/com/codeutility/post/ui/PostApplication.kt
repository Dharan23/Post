package com.codeutility.post.ui

import android.app.Application
import com.codeutility.post.data.network.service.PostService
import com.codeutility.post.data.repository.PostRepository
import com.codeutility.post.data.repository.PostRepositoryImpl
import com.codeutility.post.ui.base.ViewModelProviderFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class PostApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@PostApplication))

        bind() from singleton { PostService() }
        bind<PostRepository>() with singleton { PostRepositoryImpl(instance()) }
        bind() from provider { ViewModelProviderFactory(instance()) }
    }
}
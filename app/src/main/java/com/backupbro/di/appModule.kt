package com.backupbro.di

import com.backupbro.model.UserViewModel
import com.backupbro.repository.UserRepository
import com.backupbro.repository.UserRepositoryImpl
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {

    viewModel { UserViewModel(get()) }

    bean { UserRepositoryImpl() as UserRepository }
}

val backupBroApp = listOf(appModule)
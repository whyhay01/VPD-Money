package me.greenworld.vpdmoney.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.greenworld.vpdmoney.domain.network.ConnectivityRepository
import me.greenworld.vpdmoney.domain.network.ConnectivityRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {

    @Binds
    abstract fun bindConnectivityRepository(impl: ConnectivityRepositoryImpl): ConnectivityRepository
}
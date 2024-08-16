package me.greenworld.vpdmoney.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.greenworld.vpdmoney.data.account.AccountDao
import me.greenworld.vpdmoney.data.account.AccountRepositoryImpl
import me.greenworld.vpdmoney.data.auth.AuthRepositoryImpl
import me.greenworld.vpdmoney.data.db.UserDatabase
import me.greenworld.vpdmoney.data.transaction.TransactionDao
import me.greenworld.vpdmoney.data.transaction.TransactionRepositoryImpl
import me.greenworld.vpdmoney.domain.account.AccountRepository
import me.greenworld.vpdmoney.domain.auth.AuthRepository
import me.greenworld.vpdmoney.domain.transaction.TransactionRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase =
        UserDatabase.build(context)

    @[Provides Singleton]
    fun provideAccountDao(userDatabase: UserDatabase): AccountDao = userDatabase.accountDao

    @[Provides Singleton]
    fun provideTransactionDao(userDatabase: UserDatabase): TransactionDao =
        userDatabase.transactionDao
}

@InstallIn(SingletonComponent::class)
@Module
interface CacheModule {
    @get:[Binds]
    val AccountRepositoryImpl.bindAccountRepository: AccountRepository

    @get:[Binds]
    val TransactionRepositoryImpl.bindTransactionRepository: TransactionRepository

    @get:[Binds]
    val AuthRepositoryImpl.bindAuthRepository: AuthRepository
}
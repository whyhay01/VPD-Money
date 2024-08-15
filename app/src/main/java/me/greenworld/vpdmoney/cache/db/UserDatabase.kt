package me.greenworld.vpdmoney.cache.db

import android.accounts.Account
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.greenworld.vpdmoney.cache.account.AccountDao
import me.greenworld.vpdmoney.cache.account.AccountEntity
import me.greenworld.vpdmoney.cache.db.UserDatabase.Companion.DATABASE_VERSION
import me.greenworld.vpdmoney.cache.transaction.TransactionDao
import me.greenworld.vpdmoney.cache.transaction.TransactionEntity

@Database(entities = [AccountEntity::class, TransactionEntity::class], version = DATABASE_VERSION)
abstract class UserDatabase: RoomDatabase() {

    abstract val accountDao: AccountDao
    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "userDB"

        @Volatile
        private var database: UserDatabase? = null

        fun build(
            context: Context
        ): UserDatabase {
            return database ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                )
                databaseBuilder.fallbackToDestructiveMigration()
                val instance = databaseBuilder.build()
                database = instance
                instance
            }
        }
    }
}
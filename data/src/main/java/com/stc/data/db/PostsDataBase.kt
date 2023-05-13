package com.stc.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stc.data.db.PostsDao
import com.stc.domain.entity.PostsResponse

@Database(entities = [PostsResponse::class], version = 1)
abstract class PostsDataBase : RoomDatabase() {
    abstract fun postDao(): PostsDao

    companion object {
        @Volatile
        private var INSTANCE: PostsDataBase? = null

        fun getDatabase(context: Context): PostsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
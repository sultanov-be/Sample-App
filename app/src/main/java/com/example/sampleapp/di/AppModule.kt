package com.example.sampleapp.di

import android.content.Context
import androidx.room.Room
import com.example.sampleapp.database.StudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): StudentDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            StudentDatabase::class.java,
            "student_database"
        )
            .fallbackToDestructiveMigration()
            .build()


    }

    @Singleton
    @Provides
    fun provideStudentDao(database: StudentDatabase) = database.databaseDao
}
package com.example.sampleapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract val databaseDao: StudentDatabaseDao

}
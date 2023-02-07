package com.example.sampleapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDatabaseDao {
    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Query("SELECT * from student_list_table WHERE studentId = :key")
    fun get(key: Int): Student

    @Query("DELETE FROM student_list_table")
    fun clear()

    @Query("SELECT * FROM student_list_table ORDER BY studentId DESC")
    fun getAllStudents(): LiveData<List<Student>>
}
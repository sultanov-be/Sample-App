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

    @Query("SELECT * from student_list_table WHERE student_name = :key")
    fun getByName(key: String): Student

    @Query("DELETE FROM student_list_table")
    fun clear()

    @Query("SELECT * FROM student_list_table ORDER BY student_name ASC")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student_list_table ORDER BY student_department ASC")
    fun getStudentsByDep(): LiveData<List<Student>>

    @Query("SELECT * FROM student_list_table ORDER BY student_second_name ASC")
    fun getStudentsBySurname(): LiveData<List<Student>>
}
package com.example.sampleapp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.database.Student
import com.example.sampleapp.database.StudentDatabase
import com.example.sampleapp.database.StudentDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val dao: StudentDatabaseDao,
    private val database: StudentDatabase
) : ViewModel() {
    private val dataSource = database.databaseDao
    private var students = dao.getAllStudents()
    private var currentStudent = MutableLiveData<Student?>()

    fun getAllStudents() = students

    fun addStudent(student: Student) {
        dataSource.insert(student)
    }
}
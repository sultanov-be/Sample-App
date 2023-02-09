package com.example.sampleapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.DispatcherProvider
import com.example.sampleapp.database.Student
import com.example.sampleapp.database.StudentDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    database: StudentDatabase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {
    private val dataSource = database.databaseDao
    var students = dataSource.getAllStudents()

    fun insertStudent(student: Student) {
        viewModelScope.launch(dispatcher.io) {
            dataSource.insert(student)
        }
    }

    fun getStudentsByDepartment(): LiveData<List<Student>> {
        viewModelScope.launch(dispatcher.main) {
            students = dataSource.getStudentsByDep()
        }
        return students
    }

    fun getStudentsBySurname(): LiveData<List<Student>> {
        viewModelScope.launch(dispatcher.main) {
            students = dataSource.getStudentsBySurname()
        }
        return students
    }

    fun getStudentsByName(): LiveData<List<Student>> {
        viewModelScope.launch(dispatcher.main) {
            students = dataSource.getStudentsByDep()
        }
        return students
    }
}
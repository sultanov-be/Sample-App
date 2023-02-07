package com.example.sampleapp.ui.create

import androidx.lifecycle.ViewModel
import com.example.sampleapp.database.Student
import com.example.sampleapp.database.StudentDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor
    (private val database: StudentDatabase) : ViewModel() {
    fun writeData(student: Student) {
        database.databaseDao.insert(student)
    }
}
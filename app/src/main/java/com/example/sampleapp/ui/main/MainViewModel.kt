package com.example.sampleapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.sampleapp.database.StudentDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    database: StudentDatabase
) : ViewModel() {
    private val dataSource = database.databaseDao
    var students = dataSource.getAllStudents()

}
package com.example.sampleapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.sampleapp.database.Student
import com.example.sampleapp.database.StudentDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val database: StudentDatabase
) : ViewModel() {
    private val dataSource = database.databaseDao
    var students = dataSource.getAllStudents()

    fun writeData(student: Student) {
        dataSource.insert(student)
        refresh()
    }

    private fun refresh() {
        students = dataSource.getAllStudents()
    }

    fun getStudentByName(name: String): String {
        return convertToString(dataSource.getByName(name))
    }

    fun showStudents() : String {
        var res = ""
        students.value?.forEach { stud ->
            res += convertToString(stud)
        }
        return res
    }

    private fun convertToString(student: Student): String {
        var res = ""

        res += "\n${student.studentId}\n"
        res += "${student.studentName}\n"
        res += "${student.studentSecondName}\n"
        res += "${student.studentDepartment}\n"

        return res
    }
}
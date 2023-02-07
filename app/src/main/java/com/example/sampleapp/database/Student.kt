package com.example.sampleapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_list_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var studentId: Int = 0,

    @ColumnInfo(name = "student_name")
    var studentName: String,

    @ColumnInfo(name = "student_second_name")
    var studentSecondName: String,

    @ColumnInfo(name = "student_department")
    var studentDepartment: String
)

package com.example.sampleapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sampleapp.database.StudentDatabaseDao
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val dao: StudentDatabaseDao
) : ViewModel() {

}
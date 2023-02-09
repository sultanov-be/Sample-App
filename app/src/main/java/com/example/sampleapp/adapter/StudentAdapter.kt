package com.example.sampleapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.database.Student
import com.example.sampleapp.databinding.StudentItemBinding

class StudentAdapter internal constructor() : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem:Student, newItem: Student): Boolean {
            return oldItem.studentSecondName == newItem.studentSecondName
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding) {
        val student = differ.currentList[position]

        nameAndSurname.text = "${student.studentName} ${student.studentSecondName}"
        department.text = student.studentDepartment
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
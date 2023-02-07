package com.example.sampleapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.R
import com.example.sampleapp.database.Student
import com.example.sampleapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.sortBtn.setOnClickListener {
            binding.textview.text = viewModel.getStudentByName("BEKA")
        }

        binding.randomBtn.setOnClickListener {
            viewModel.writeData(Student(null, "BEKA", "SULTANOV", "AUCA"))
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.students.observe(viewLifecycleOwner){
            binding.textview.text = viewModel.showStudents()
        }

        binding.createBtn.setOnClickListener {
            findNavController().navigate(R.id.goToCreate)
        }
    }

}
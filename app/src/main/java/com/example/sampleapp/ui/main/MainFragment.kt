package com.example.sampleapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.sampleapp.R
import com.example.sampleapp.adapter.StudentAdapter
import com.example.sampleapp.database.Student
import com.example.sampleapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        studentAdapter = StudentAdapter()

        initAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.visibility = GONE

        val listNames = resources.getStringArray(R.array.names)
        val listSurNames = resources.getStringArray(R.array.surnames)

        binding.generateBtn.setOnClickListener {
            viewModel.insertStudent(Student(null, listNames[Random.nextInt(0,7)], listSurNames[Random.nextInt(0,7)], "SFW"))
        }

        observeData()
    }

    private fun initAdapter() = with(binding) {
        recyclerView.apply {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        }
    }

    private fun observeData() = with(binding) {
        viewModel.students.observe(viewLifecycleOwner) {
            try {
                studentAdapter.differ.submitList(viewModel.students.value)
                recyclerView.visibility = VISIBLE
            } catch (e: java.lang.Exception) {
                recyclerView.visibility = GONE
            }
        }
    }
}
package com.example.sampleapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.sampleapp.R
import com.example.sampleapp.adapter.StudentAdapter
import com.example.sampleapp.database.Student
import com.example.sampleapp.databinding.BottomSheetBinding
import com.example.sampleapp.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var dialog: BottomSheetDialog

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
        val listDepo = resources.getStringArray(R.array.departments)

        binding.generateBtn.setOnClickListener {
            viewModel.insertStudent(
                Student(
                    null,
                    listNames[Random.nextInt(0, 7)],
                    listSurNames[Random.nextInt(0, 7)],
                    listDepo[Random.nextInt(0, 7)]
                )
            )
        }

        binding.sortBy.setOnClickListener {
            showDialog()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val list = viewModel.students.value
                    val newlist = arrayListOf<Student>()
                    list?.forEach {
                        if (it.studentName.contains(query))
                            newlist.add(it)
                    }
                    studentAdapter.differ.submitList(
                        newlist
                    )
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        observeData(false)
    }

    private fun initAdapter() = with(binding) {
        recyclerView.apply {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        }
    }

    private fun observeData(isReset: Boolean) = with(binding) {
        if (isReset) {
            studentAdapter.differ.submitList(null)
        }

        viewModel.students.observe(viewLifecycleOwner) {
            try {
                studentAdapter.differ.submitList(viewModel.students.value)
                recyclerView.visibility = VISIBLE
            } catch (e: java.lang.Exception) {
                recyclerView.visibility = GONE
            }
        }
    }

    private fun showDialog() {
        val binding = BottomSheetBinding.inflate(LayoutInflater.from(context))
        dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.show()

        with(binding) {
            byDepartmentBtn.setOnClickListener {
                viewModel.getStudentsByDepartment()
                dialog.dismiss()
                observeData(true)
            }
            byNameBtn.setOnClickListener {
                viewModel.getStudentsByName()
                dialog.dismiss()
                observeData(true)
            }
            bySurnameBtn.setOnClickListener {
                viewModel.getStudentsBySurname()
                dialog.dismiss()
                observeData(true)
            }
        }
    }

}
package com.example.sampleapp.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.R
import com.example.sampleapp.database.Student
import com.example.sampleapp.databinding.FragmentCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment() {
    private val viewModel by viewModels<CreateViewModel>()
    private lateinit var binding: FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateBinding.inflate(layoutInflater)

        if (binding.editName.text == null || binding.editSurname.text == null || binding.editDepartment.text == null) {
            Toast.makeText(requireContext(), "Some field is empty", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.writeData(Student(
                null,
                binding.editName.text.toString(),
                binding.editSurname.toString(),
                binding.editDepartment.toString()
            ))

            findNavController().navigate(R.id.mainFragment)
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }
}
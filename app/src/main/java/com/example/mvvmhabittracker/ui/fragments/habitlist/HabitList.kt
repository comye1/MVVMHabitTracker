package com.example.mvvmhabittracker.ui.fragments.habitlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmhabittracker.R
import com.example.mvvmhabittracker.databinding.FragmentHabitListBinding

class HabitList : Fragment(R.layout.fragment_habit_list) {

    private var _binding: FragmentHabitListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_habitList_to_createHabitItem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.mvvmhabittracker.ui.fragments.createhabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmhabittracker.R
import com.example.mvvmhabittracker.databinding.FragmentCreateHabitItemBinding
import com.example.mvvmhabittracker.ui.viewmodel.HabitViewModel
import com.example.mvvmhabittracker.util.Calculations
import java.util.*

class CreateHabitItem : Fragment(R.layout.fragment_create_habit_item),
TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private var _binding: FragmentCreateHabitItemBinding? = null
    private val binding get() = _binding!!

    private var title = ""
    private var descrition = ""
    private var drawableSelected = 0
    private var timeStamp = 0

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitViewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateHabitItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        binding.btnConfirm.setOnClickListener {
            addHabitToDB()
        }

        pickDateAndTime()

        drawableSelected()
    }

    private fun addHabitToDB() {
        TODO("Not yet implemented")
    }

    private fun pickDateAndTime() {
        binding.btnPickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        binding.btnPickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(requireContext(), this, hour, minute, true).show()
        }

    }

    private fun drawableSelected() {
        binding.apply {
            ivFastFoodSelected.setOnClickListener {
                ivFastFoodSelected.isSelected = !ivFastFoodSelected.isSelected
                drawableSelected = R.drawable.ic_fastfood

                ivSmokingSelected.isSelected = false
                ivTeaSelected.isSelected = false
            }

            ivSmokingSelected.setOnClickListener {
                ivSmokingSelected.isSelected = !ivSmokingSelected.isSelected
                drawableSelected = R.drawable.ic_smoking

                ivFastFoodSelected.isSelected = false
                ivTeaSelected.isSelected = false
            }

            ivTeaSelected.setOnClickListener {
                ivTeaSelected.isSelected = !ivTeaSelected.isSelected
                drawableSelected = R.drawable.ic_tea

                ivSmokingSelected.isSelected = false
                ivFastFoodSelected.isSelected = false
            }
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minuteX: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minuteX)
        binding.apply {
            tvTimeSelected.text = "Time : $cleanTime"
        }
    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth, monthX, yearX)
        binding.apply {
            tvDateSelected.text = "Date : $cleanDate"
        }
    }

    private fun getTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)

    }

    private fun getDateCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }
}
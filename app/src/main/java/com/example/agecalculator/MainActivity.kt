package com.example.agecalculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import com.example.agecalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityMainBinding
    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnDatePicker.setOnClickListener {
            showDatePickerDialog()
            if (binding.btnDatePicker.text.isNullOrEmpty()) {
                showDatePickerDialog()
            }
        }
        binding.btnCalculate.setOnClickListener {
            calculateAge()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedYear = year
        selectedMonth = month
        selectedDay = dayOfMonth
        val selectedDate = Calendar.getInstance()
        selectedDate.set(selectedYear, selectedMonth, selectedDay)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.datePicker.text = dateFormat.format(selectedDate.time)

    }

    private fun calculateAge() {
        val today = Calendar.getInstance()
        var age = today.get(Calendar.YEAR) - selectedYear
        if (today.get(Calendar.DAY_OF_YEAR) < selectedDay) {
            age--
        }
        binding.age.text = "Your age is: $age"
    }

}

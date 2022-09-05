package com.example.ageinhours

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var myDatePickerBtn : Button? = null
    private var tvSelectedDate : TextView? = null
    private var tvAgeInHours : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDatePickerBtn = findViewById(R.id.datePickerBtn)
        tvSelectedDate = findViewById(R.id.selectedDate)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)

        myDatePickerBtn?.setOnClickListener {
            myDatePickerFun()
        }
    }
    private fun myDatePickerFun() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateInDateFormat = sdf.parse(selectedDate)
            selectedDateInDateFormat?.let { 
                val selectedDateInHours = selectedDateInDateFormat.time/3600000
                val currDate  = sdf.parse(sdf.format(System.currentTimeMillis()))
                currDate?.let {
                    val currDateInHours = currDate.time/3600000
                    val differenceInHours = currDateInHours-selectedDateInHours
                    tvAgeInHours?.text = differenceInHours.toString()
                }
            }

            Toast.makeText(this, "DatePicker Works", Toast.LENGTH_LONG).show()
        }, year, month, dayOfMonth)
        dpd.datePicker.maxDate = System.currentTimeMillis() -86400000
        dpd.show()
    }
}
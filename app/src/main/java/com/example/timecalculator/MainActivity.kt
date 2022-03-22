package com.example.timecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvDateInMinutes : TextView? = null
    private var tvDateInHours : TextView? = null
    private var tvDateInDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvDateInMinutes = findViewById(R.id.tvDateInMinutes)
        tvDateInHours = findViewById(R.id.tvDateInHours)
        tvDateInDays = findViewById(R.id.tvDateInDays)


        btnDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){

        val myCalendar =Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        //val hour=myCalendar.get(Calendar.HOUR)
       // val minute=myCalendar.get(Calendar.MINUTE)
        val dpd=DatePickerDialog( this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(this,
                    "The date has been selected", Toast.LENGTH_LONG).show()

                val selectedDate ="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    // ^ this is type long because the Date is counted from 1.01.1970 in milliseconds
                    val selectedDateInHours = theDate.time / 3600000
                    val selectedDateInDays = theDate.time / 86400000


                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val currentDateInHours = currentDate.time/ 3600000
                        val currentDateInDays = currentDate.time/ 86400000


                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        val differenceInDays = currentDateInDays - selectedDateInDays
                        tvDateInMinutes?.text = differenceInMinutes.toString()
                        tvDateInHours?.text = differenceInHours.toString()
                        tvDateInDays?.text = differenceInDays.toString()
                    }
                }


            },
            year,
            month,
            day
            )
            dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
            dpd.show()



    }
}
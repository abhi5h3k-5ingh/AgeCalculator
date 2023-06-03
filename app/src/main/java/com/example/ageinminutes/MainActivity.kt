package com.example.ageinminutes

import java.text.SimpleDateFormat
import java.util.*

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var txtDate:TextView?=null
    private var txtSeconds:TextView?=null
    private var txtMinutes:TextView?=null
    private var txtHours:TextView?=null
    private var txtDays:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDate=findViewById(R.id.tvDate)
        txtSeconds=findViewById(R.id.tvSeconds)
        txtMinutes=findViewById(R.id.tvMinutes)
        txtHours=findViewById(R.id.tvHours)
        txtDays=findViewById(R.id.tvDays)

        val btnSelectDate=findViewById<Button>(R.id.btSelectDate)
        btnSelectDate.setOnClickListener(){
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog(){
        val calendar=Calendar.getInstance()  // to get current date, month , year
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, dayOfMonth ->

                val selectedDate="$dayOfMonth/${selectedMonth+1}/$selectedYear"
                txtDate?.text =selectedDate

                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateinSeconds=theDate.time/1000
                    val selectedDateInMinutes=theDate.time/60000
                    val selectedDateInHours=theDate.time/3600000
                    val selectedDateInDays=theDate.time/86400000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInSeconds=currentDate.time/1000
                        val currentDateinMinutes = currentDate.time / 60000
                        val currentDateInHours=currentDate.time/3600000
                        val currentDateInDays=currentDate.time/86400000
                        val differenceInSeconds = currentDateInSeconds - selectedDateinSeconds
                        val differenceInMinutes = currentDateinMinutes - selectedDateInMinutes
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        val differenceInDays = currentDateInDays - selectedDateInDays
                        txtSeconds?.text = differenceInSeconds.toString()
                        txtMinutes?.text = differenceInMinutes.toString()
                        txtHours?.text = differenceInHours.toString()
                        txtDays?.text = differenceInDays.toString()
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
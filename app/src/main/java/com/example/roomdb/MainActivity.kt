package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb.room.StudentDBViewModel
import com.example.roomdb.room.StudentTable

class MainActivity : AppCompatActivity() {

    private lateinit var studentDBViewModel: StudentDBViewModel
    private lateinit var editTextStudentID: EditText
    private lateinit var edittextStudentName: EditText
    private lateinit var editTextStudentResult: EditText
    private lateinit var buttonInsert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDBViewModel = ViewModelProvider(
            this).get(StudentDBViewModel::class.java)

        editTextStudentID = findViewById(R.id.et_StudentID)
        edittextStudentName = findViewById(R.id.et_StudentName)
        editTextStudentResult = findViewById(R.id.et_StudentResult)
        buttonInsert = findViewById(R.id.bv_insert)

        buttonInsert.setOnClickListener(onClickListener)

    }

    private val onClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(view: View?) {
            var studentID: Int = 0
            var studentResult: Double = 0.0
            var studentName: String = ""
            when (view!!.id) {
                R.id.bv_insert -> {
                    if (editTextStudentID.text.toString().isNotEmpty()) {
                        studentID = (editTextStudentID.text.toString()).toInt()
                    }
                    if (edittextStudentName.text.toString().isNotEmpty()) {
                        studentName = (edittextStudentName.text.toString())
                    }
                    if (editTextStudentResult.text.toString().isNotEmpty()) {
                        studentResult = (editTextStudentResult.text.toString()).toDouble()
                    }

                    val studentTable: StudentTable = StudentTable(studentID, studentName, studentResult)

                    //This logic is to jest debug
                    //Inset Data
                    if ((studentTable.id > 1) && (studentTable.name.isNotEmpty()) && (studentTable.result > -1.0)) {
                        insertStudentTable(studentTable)
                    } else {
                        Toast.makeText(this@MainActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }

                    //GetAll Data
                    val listOfStudentTable: List<StudentTable> = getAllStudentData()
                    Toast.makeText(this@MainActivity, "$listOfStudentTable", Toast.LENGTH_SHORT).show()
                    Log.e("StudentRoomDB", "$listOfStudentTable")
                }
                else -> {}
            }
        }

    }

    private fun insertStudentTable(studentTable: StudentTable) {

        val result: Long = studentDBViewModel.insertStudentTable(studentTable)

        if (result > 1L) {
            Log.e("StudentRoomDB", "$result")
            Toast.makeText(this@MainActivity, "$result: Data inserted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("StudentRoomDB", "$result")
            Toast.makeText(this@MainActivity, "$result: Data not inserted!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getAllStudentData(): List<StudentTable> {
        val listOfStudentTable: List<StudentTable> = studentDBViewModel.getAllStudentTable()

        return listOfStudentTable
    }

}
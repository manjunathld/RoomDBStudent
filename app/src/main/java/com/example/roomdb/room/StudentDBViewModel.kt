package com.example.roomdb.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel

class StudentDBViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var studentRoomDatabase: StudentRoomDatabase
    lateinit var studentTableDAO: StudentDBDao

    init {
        studentRoomDatabase = StudentRoomDatabase.getStudentDatabase(application)!!
        studentTableDAO = studentRoomDatabase.studentDBDAO()
    }

    public fun insertStudentTable(studentTable: StudentTable): Long {
        val result: Long = InsertAsyncTask(studentTableDAO).execute(studentTable).get()

        return result
    }

    public fun getAllStudentTable(): List<StudentTable> {
        val studentTable: List<StudentTable> = GetAsyncTask(studentTableDAO).execute().get()

        return studentTable
    }

    private class InsertAsyncTask(studentDBDao: StudentDBDao) : AsyncTask<StudentTable, Void, Long>() {
        lateinit var studentDBDao: StudentDBDao
        init {
            this.studentDBDao = studentDBDao
        }

        override fun doInBackground(vararg studentTable: StudentTable?): Long {
            val result: Long = studentDBDao.insertStudentTable(studentTable[0]!!)

            return result
        }
    }

    private class GetAsyncTask(studentDBDao: StudentDBDao) : AsyncTask<Void, Void, List<StudentTable>>() {
        lateinit var studentDBDao: StudentDBDao
        init {
            this.studentDBDao = studentDBDao
        }
        override fun doInBackground(vararg p0: Void?): List<StudentTable> {
            val studentTable: List<StudentTable> = studentDBDao.getAllStudentTable()
            return studentTable
        }

    }

}
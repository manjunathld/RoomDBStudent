package com.example.roomdb.room

import androidx.room.*

@Dao
interface StudentTableDAO {
    @Query("SELECT * FROM student_table")
    fun getAllStudentTable() : List<StudentTable>

    @Query("SELECT * FROM student_table WHERE name IN (:name)")
    fun loadAllStudentTableByName(name: String)

    @Insert
    fun insertStudentTable(vararg studentTable: StudentTable)

    @Update
    fun updateStudentTable(vararg studentTable: StudentTable)

    @Delete
    fun deleteStudentTable(vararg studentTable: StudentTable)
}
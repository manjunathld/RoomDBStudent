package com.example.roomdb.room

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Dao
interface StudentDBDao {
    @Query("SELECT * FROM student_table")
    fun getAllStudentTable() : List<StudentTable>

    //@Query("SELECT * FROM student_table WHERE name IN (:name)")
    //fun loadAllStudentTableByName(name: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudentTable(studentTable: StudentTable): Long

    @Update
    fun updateStudentTable(vararg studentTable: StudentTable)

    @Delete
    fun deleteStudentTable(vararg studentTable: StudentTable)
}
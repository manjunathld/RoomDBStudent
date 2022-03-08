package com.example.roomdb.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "student_table")
data class StudentTable
    (
        @NotNull
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @NotNull
        @ColumnInfo(name = "name")
        var name: String,

        @NotNull
        @ColumnInfo(name = "result")
        var result: Double
    ) {}
package com.example.roomdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentTable::class], version = 111)
public abstract class StudentRoomDatabase : RoomDatabase() {

    public abstract fun studentDBDAO(): StudentDBDao

    companion object {
        @Volatile
        private var studentRoomDatabase: StudentRoomDatabase? = null

        fun getStudentDatabase(context: Context): StudentRoomDatabase? {
            if (studentRoomDatabase == null) {
                synchronized(StudentRoomDatabase::class.java) {
                    if (studentRoomDatabase == null) {
                        studentRoomDatabase = Room.databaseBuilder(
                            context.applicationContext,
                            StudentRoomDatabase::class.java,
                            "student_room_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return studentRoomDatabase
        }
    }

}
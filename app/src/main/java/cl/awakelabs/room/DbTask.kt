package cl.awakelabs.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task:: class], version = 1)
abstract class DbTask: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

      companion object {
            @Volatile
            private var INSTANCE: DbTask? = null

            fun getDatabase(context: Context): DbTask {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DbTask::class.java,
                        "tarea_database"
                    ).build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
  }

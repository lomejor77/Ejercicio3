package cl.awakelabs.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)
}
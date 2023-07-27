package cl.awakelabs.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)
    //acceso a la informacion
    @Query("select * from tbl_task order by id ASC")
    fun getTasks(): List<Task>
}
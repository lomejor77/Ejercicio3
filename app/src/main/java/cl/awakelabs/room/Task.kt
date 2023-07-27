package cl.awakelabs.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_task")
data class Task(val names: String, val date: String) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

}


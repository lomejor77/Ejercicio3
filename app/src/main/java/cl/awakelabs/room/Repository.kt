package cl.awakelabs.room

import androidx.lifecycle.LiveData

class Repository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)//inserta tareas
    }
    fun taskListed(): LiveData<List<Task>> {
        return taskDao.getTasks()//obtiene tareas
    }
}
package cl.awakelabs.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(aplication: Application): AndroidViewModel(aplication) {
    private val  repository: Repository
    init {
       repository = Repository(DbTask.getDatabase(aplication).getTaskDao())
    }
    fun obtainTask(): LiveData<List<Task>> {
        return repository.taskListed()
    }
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }
}
package cl.awakelabs.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cl.awakelabs.room.databinding.FragmentAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentAddBinding
    lateinit var repository: Repository
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAddBinding.inflate(layoutInflater, container,false)

        initRepository()
        initListener()
        loadTasks()
        return binding.root
    }

    private fun initRepository(){
       repository = Repository(DbTask.getDatabase(requireContext()).getTaskDao())
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            val texto = binding.editAdd.text.toString()
            saveTask(texto)
            Toast.makeText(requireContext(), "se cargo correctamente", Toast.LENGTH_SHORT).show()
        }

    }

    private fun saveTask(texto: String) {

        val task = Task(texto,"fecha")
        GlobalScope.launch {  repository.insertTask(task) }

    }

    private fun loadTasks() {
        repository.taskListed().observe(requireActivity()){
            val taskAsText = it.joinToString("\n"){it.names}
            binding.textView.text = taskAsText
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.primerproyecto

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var etTask: EditText
    private lateinit var btnAddTask: Button
    private lateinit var fabGoToResult: FloatingActionButton
    private lateinit var btnSelectImage: Button
    private lateinit var ivSelectedImage: ImageView
    private val tasks = mutableListOf<Task>()
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de componentes
        initComponent()
        initVi()
        initListeners()
    }

    private fun initComponent(){

        etTask = findViewById(R.id.etTask)
        btnAddTask = findViewById(R.id.btnAddTask)
        fabGoToResult = findViewById(R.id.fabGoToResult)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        ivSelectedImage = findViewById(R.id.ivSelectedImage)
    }

    @SuppressLint("IntentReset", "NotifyDataSetChanged")
    private fun initListeners(){
        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            val selectedImageUri = ivSelectedImage.tag as Uri?

            if (currentTask.isNotBlank() && selectedImageUri != null) {
                tasks.add(Task(currentTask, selectedImageUri.toString()))
                etTask.text.clear()
                ivSelectedImage.setImageDrawable(null) // Clear the image
                ivSelectedImage.visibility = View.GONE // Hide the image view
                taskAdapter.notifyDataSetChanged()
            }
        }

        fabGoToResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putParcelableArrayListExtra("tasks", ArrayList(tasks))
            startActivity(intent)
        }

        btnSelectImage.setOnClickListener {
            // Inicia el selector de imágenes
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    private fun initVi(){
        taskAdapter = TaskAdapter(tasks)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            ivSelectedImage.setImageURI(selectedImageUri)
            ivSelectedImage.tag = selectedImageUri
            ivSelectedImage.visibility = View.VISIBLE
        }
    }
}

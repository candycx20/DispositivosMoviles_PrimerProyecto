package com.example.primerproyecto

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val ivTaskImage: ImageView = view.findViewById(R.id.ivTaskImage)

    fun render(task: Task){
        tvTask.text = task.name

        // Manejo de la imagen
        if (task.imageUri != null) {
            ivTaskImage.setImageURI(Uri.parse(task.imageUri))
            ivTaskImage.visibility = View.VISIBLE
        } else {
            ivTaskImage.visibility = View.GONE
        }
    }
}
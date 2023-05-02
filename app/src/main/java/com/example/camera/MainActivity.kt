package com.example.camera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var imageviewtop: ImageView
    lateinit var imageviewbottom: ImageView
    lateinit var button: Button
    var Image: Bitmap? = null
    val request_capture = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageviewtop = findViewById(R.id.image_top)
        imageviewbottom = findViewById(R.id.image_bottom)
        button = findViewById(R.id.button_camera)

        button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, request_capture)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == -1 && requestCode == request_capture) {
            val imageBitmap = data?.getParcelableExtra<Bitmap>("data")
            imageviewtop.setImageBitmap(imageBitmap)
            imageviewbottom.setImageBitmap(Image)
            Image = imageBitmap
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}
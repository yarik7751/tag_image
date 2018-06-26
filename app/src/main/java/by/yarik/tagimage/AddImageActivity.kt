package by.yarik.tagimage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AddImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)
        setTitle(R.string.add_image_title)
    }
}
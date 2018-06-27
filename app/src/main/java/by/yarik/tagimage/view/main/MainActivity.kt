package by.yarik.tagimage.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import by.yarik.tagimage.R
import by.yarik.tagimage.db.model.Image
import by.yarik.tagimage.presenter.MainPresenter
import by.yarik.tagimage.view.main.adapter.ImageAdapter
import by.yarik.tagimage.view.add_image.AddImageActivity
import by.yarik.tagimage.view.main.interfaces.Main

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), Main {

    private val RECORD_REQUEST_CODE = 101

    var mainPresenter: MainPresenter? = null
    var main: Main? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        main = this
        mainPresenter = MainPresenter(this, main as MainActivity)
        mainPresenter!!.getAllImages()

        fab.setOnClickListener { view ->
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RECORD_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            RECORD_REQUEST_CODE -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this, AddImageActivity::class.java))
                }
            }
        }
    }

    override fun setAllImages(images: List<Image>) {
        var imageAdapter = ImageAdapter(this, images);
        gvImages.adapter = imageAdapter;
    }

    override fun setImagesByQuery(q: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

package by.yarik.tagimage.view.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import by.yarik.tagimage.R
import by.yarik.tagimage.db.model.Image
import by.yarik.tagimage.presenter.MainPresenter
import by.yarik.tagimage.util.AndroidUtils
import by.yarik.tagimage.view.main.adapter.ImageAdapter
import by.yarik.tagimage.view.add_image.AddImageActivity
import by.yarik.tagimage.view.main.interfaces.Main
import com.jakewharton.rxbinding.widget.RxTextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1

class MainActivity : AppCompatActivity(), Main {

    private val RECORD_REQUEST_CODE = 101
    private val ADD_IMAGE_ACTIVITY = 41

    var mMainPresenter: MainPresenter? = null
    var mMain: Main? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initPresenter()

        etQuery.visibility = View.GONE

        RxTextView.textChanges(etQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1 {
                    Log.d("MainActivity_log", it.toString())
                    mMainPresenter!!.getImagesByQuery(it.toString())
                })
        fab.setOnClickListener { view ->
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RECORD_REQUEST_CODE)
        }

        imgSearch.setOnClickListener {
            searchButtonClick()
        }
    }

    private fun initPresenter() {
        mMain = this
        mMainPresenter = MainPresenter(this, mMain as MainActivity)
        mMainPresenter!!.getAllImages()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            RECORD_REQUEST_CODE -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(Intent(this, AddImageActivity::class.java), ADD_IMAGE_ACTIVITY )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            mMainPresenter!!.getAllImages()
        }
    }

    override fun onBackPressed() {
        if(etQuery.visibility == View.VISIBLE) {
            hideSearchField()
        } else {
            super.onBackPressed()
        }
    }

    private fun searchButtonClick() {
        if(etQuery.visibility == View.GONE) {
            showSearchField()
        } else if(etQuery.visibility == View.VISIBLE) {
            hideSearchField()
        }
    }

    private fun showSearchField() {
        imgSearch.setImageResource(R.drawable.baseline_close_white_36)
        etQuery.visibility = View.VISIBLE
        tvTitle.visibility = View.GONE
        etQuery.requestFocus()
        AndroidUtils.showKeyboard(this)
    }

    private fun hideSearchField() {
        imgSearch.setImageResource(R.drawable.baseline_search_white_36)
        etQuery.visibility = View.GONE
        tvTitle.visibility = View.VISIBLE
        AndroidUtils.hideKeyboard(this)
    }

    private fun loadImages(images: List<Image>) {
        if(images.size == 0) {
            tvNoImages.visibility = View.VISIBLE
        } else {
            tvNoImages.visibility = View.INVISIBLE
        }
        var imageAdapter = ImageAdapter(this, images)
        gvImages.adapter = imageAdapter
    }

    override fun setAllImages(images: List<Image>) {
        loadImages(images)
    }

    override fun setImagesByQuery(images: List<Image>) {
        loadImages(images)
    }
}

package by.yarik.tagimage.presenter

import android.content.Context
import by.yarik.tagimage.db.DBHelper
import by.yarik.tagimage.db.model.Image
import by.yarik.tagimage.view.main.interfaces.Main

class MainPresenter(var context: Context, var main: Main) {

    var dbHelper: DBHelper? = null
    init {
        dbHelper = DBHelper(context)
    }

    fun getAllImages() {
        var images = dbHelper!!.imageDao.queryForAll()
        main.setAllImages(images)
    }
}
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

    fun getImagesByQuery(q: String) {
        var images: List<Image>
        if(q.isEmpty()) {
            images = dbHelper!!.imageDao.queryForAll()
        } else {
            var queryBuilder = dbHelper!!.imageDao.queryBuilder();
            queryBuilder.where().like("tags", "%$q%")
            images = dbHelper!!.imageDao.query(queryBuilder.prepare())
        }
        main.setImagesByQuery(images)
    }
}
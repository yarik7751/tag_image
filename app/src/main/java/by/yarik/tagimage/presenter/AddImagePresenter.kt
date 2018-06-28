package by.yarik.tagimage.presenter

import android.content.Context
import by.yarik.tagimage.db.DBHelper
import by.yarik.tagimage.db.model.Image
import by.yarik.tagimage.view.add_image.interfaces.AddImage
import by.yarik.tagimage.view.main.interfaces.Main

class AddImagePresenter(var context: Context, var addImage: AddImage) {

    var dbHelper: DBHelper? = null
    init {
        dbHelper = DBHelper(context)
    }

    fun addImage(path: String?, tags: List<String>): Long {
        if(path!!.isEmpty()) {
            addImage.showImageError()
        } else if (tags.size == 1 && tags[0].isEmpty()) {
            addImage.showEmptyTagsError()
        } else {
            var image = Image(-1, path, Image.listToString(tags))
            var rows = dbHelper!!.imageDao.create(image)
            addImage.addImage()
            return rows.toLong()
        }
        return DBHelper::WRONG_RESULT.get();
    }
}
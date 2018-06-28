package by.yarik.tagimage.view.add_image

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import by.yarik.tagimage.R
import by.yarik.tagimage.presenter.AddImagePresenter
import by.yarik.tagimage.util.AndroidUtils
import by.yarik.tagimage.util.DialogUtils
import by.yarik.tagimage.view.add_image.interfaces.AddImage
import kotlinx.android.synthetic.main.activity_add_image.*
import java.io.File
import java.io.FileOutputStream

class AddImageActivity : AppCompatActivity(), AddImage {

    val GALLERY_PICK = 1;
    val TAKE_PHOTO = 2;

    var path: String? = ""

    var mAddImagePresenter: AddImagePresenter? = null
    var mAddImage: AddImage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)
        setTitle(R.string.add_image_title)

        mAddImage = this
        mAddImagePresenter = AddImagePresenter(this, mAddImage as AddImageActivity)

        btnChooseImage.setOnClickListener {
            showGetPhotoDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_image, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_iamge -> {
                mAddImagePresenter!!.addImage(path, getTags())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, R.string.no_chosen_image, Toast.LENGTH_SHORT).show()
        } else if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_PICK -> {
                    if(data != null) {
                        val contentUri = data!!.data;
                        path = getPathByUri(contentUri)
                        imgChosen.setImageURI(contentUri)
                        imgChosen.rotation = 90F;
                    }
                }
                TAKE_PHOTO -> {
                    val bitmap = data!!.extras!!.get("data") as Bitmap
                    imgChosen.setImageBitmap(bitmap)
                    path = saveBitmap(bitmap)
                }
            }
        }
    }

    private fun getPathByUri(uri: Uri): String {
        var filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = this.contentResolver.query(uri,
                filePathColumn,
                null, null, null)
        if(cursor == null) {
            return uri.path
        } else if(cursor.moveToFirst()) {
            var index = cursor.getColumnIndexOrThrow(filePathColumn[0])
            var result = cursor.getString(index)
            cursor.close()
            return if(result == null) "" else result
        } else {
            return uri.path
        }
    }

    private fun saveBitmap(bitmap: Bitmap): String {
        try {
            var file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "fname_" + System.currentTimeMillis().toString() + ".jpg"
            )
            file.createNewFile()
            var fout = FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout)
            fout.flush()
            fout.close()

            MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    file.getAbsolutePath(),
                    file.getName(),
                    file.getName());

            return file.getAbsolutePath()
        } catch (e: Exception) {
            return ""
        }
    }

    private fun showGetPhotoDialog() {
        var chooseDialog = DialogUtils.choseImageDialog(this,
                View.OnClickListener {
                    takePhoto();
                },
                View.OnClickListener {
                    openGallery()
                });
        chooseDialog.show();
    }

    private fun takePhoto() {
        var pickerIntent = Intent().apply {
            action = MediaStore.ACTION_IMAGE_CAPTURE
        }
        startActivityForResult(Intent.createChooser(pickerIntent, getString(R.string.take_photo)), TAKE_PHOTO)
    }

    private fun openGallery() {
        var pickerIntent = Intent().apply {
            data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            action = Intent.ACTION_PICK
        }
        startActivityForResult(Intent.createChooser(pickerIntent, getString(R.string.select_images)), GALLERY_PICK)
    }

    private fun getTags(): List<String> {
        var tagsStr = etTags.text.toString()
        var tags = tagsStr.split(",")
        return tags
    }

    override fun addImage() {
        Toast.makeText(this, R.string.image_added_successfully, Toast.LENGTH_SHORT).show()
        AndroidUtils.hideKeyboard(this)
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showImageError() {
        Toast.makeText(this, R.string.you_didnt_chose_image, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyTagsError() {
        Toast.makeText(this, R.string.you_should_enter_tags, Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
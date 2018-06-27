package by.yarik.tagimage.util

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import by.yarik.tagimage.R

object DialogUtils {

    fun choseImageDialog(
            context: Context,
            onClickTakePhoto: View.OnClickListener,
            onClickGallery: View.OnClickListener) : AlertDialog {
        var alertDialogBuilder = AlertDialog.Builder(context)
        var view = LayoutInflater.from(context).inflate(R.layout.dialog_select_image, null)
        alertDialogBuilder.setView(view)
        var alertDialog = alertDialogBuilder.create();

        view.findViewById<View>(R.id.imgPhoto).setOnClickListener(View.OnClickListener {
            onClickTakePhoto.onClick(null)
            alertDialog.cancel();
        })
        view.findViewById<View>(R.id.imgGalery).setOnClickListener(View.OnClickListener {
            onClickGallery.onClick(null)
            alertDialog.cancel();
        })

        return alertDialog;
    }
}
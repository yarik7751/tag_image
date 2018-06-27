package by.yarik.tagimage.view.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import by.yarik.tagimage.R
import by.yarik.tagimage.db.model.Image
import com.squareup.picasso.Picasso
import java.io.File

class ImageAdapter(var context: Context, var images: List<Image>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        var imgImage = view.findViewById<ImageView>(R.id.imgImage)
        var tvTags = view.findViewById<TextView>(R.id.tvTags)
        var file = File(images[position].path)
        if(file.exists()) {
            Picasso.with(context).load(file).resize(100, 100).centerInside().into(imgImage)
        } else {
            Picasso.with(context).load(R.drawable.baseline_broken_image_24).resize(100, 100).centerInside().into(imgImage)
        }
        tvTags.setText(images[position].tags)
        return view
    }

    override fun getItem(position: Int): Image {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return images[position].id
    }

    override fun getCount(): Int {
        return images.size
    }
}
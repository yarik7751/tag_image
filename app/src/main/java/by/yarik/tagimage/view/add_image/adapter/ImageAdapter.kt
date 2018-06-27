package by.yarik.tagimage.view.add_image.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import by.yarik.tagimage.R

class ImageAdapter(var context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        var imgImage = view.findViewById<ImageView>(R.id.imgImage)
        var tvTags = view.findViewById<TextView>(R.id.tvTags)
        return view
    }

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return 22
    }
}
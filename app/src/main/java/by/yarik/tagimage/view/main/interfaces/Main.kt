package by.yarik.tagimage.view.main.interfaces

import by.yarik.tagimage.db.model.Image

interface Main {
    fun setAllImages(images: List<Image>)
    fun setImagesByQuery(images: List<Image>)
}
package by.yarik.tagimage.view.add_image.innterface

interface AddImage : Loading {

    fun addImage();

    fun showImageError();

    fun showEmptyTagsError();
}
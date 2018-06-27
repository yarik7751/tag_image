package by.yarik.tagimage.view.add_image.interfaces

interface AddImage : Loading {

    fun addImage();

    fun showImageError();

    fun showEmptyTagsError();
}
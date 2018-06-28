package by.yarik.tagimage

import android.app.Application
import android.content.Context
import android.test.ActivityInstrumentationTestCase2
import android.test.AndroidTestCase
import android.test.InstrumentationTestCase
import android.test.mock.MockContext
import by.yarik.tagimage.db.DBHelper
import by.yarik.tagimage.presenter.AddImagePresenter
import by.yarik.tagimage.view.add_image.interfaces.AddImage
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.coroutines.experimental.coroutineContext

@Config(sdk = intArrayOf(21), manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class AddImageTest {

    @Test
    fun testImageError() {

        var addImage = Mockito.mock(AddImage::class.java)
        var addImagePresenter = AddImagePresenter(RuntimeEnvironment.application, addImage)

        var list = ArrayList<String>()
        list.add("")

        addImagePresenter.addImage("", list)
        Mockito.verify(addImage).showImageError()
    }

    @Test
    fun testTagsError() {

        var addImage = Mockito.mock(AddImage::class.java)
        var addImagePresenter = AddImagePresenter(RuntimeEnvironment.application, addImage)

        var list = ArrayList<String>()
        list.add("")

        addImagePresenter.addImage("test", list)
        Mockito.verify(addImage).showEmptyTagsError()
    }

    @Test
    fun testAddImage() {

        var addImage = Mockito.mock(AddImage::class.java)
        var addImagePresenter = AddImagePresenter(RuntimeEnvironment.application, addImage)

        var list = ArrayList<String>()
        list.add("tag")

        addImagePresenter.addImage("test", list)
        Mockito.verify(addImage).addImage()
    }
}
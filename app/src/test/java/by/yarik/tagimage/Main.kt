package by.yarik.tagimage

import android.media.Image
import by.yarik.tagimage.presenter.MainPresenter
import by.yarik.tagimage.view.main.interfaces.Main
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(21), manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class MainTest {

    @Test
    fun testEmptyQuery() {

        var main = Mockito.mock(Main::class.java)
        var mainPresenter = MainPresenter(RuntimeEnvironment.application, main)

        var q = ""

        mainPresenter.getImagesByQuery(q)
        Mockito.verify(main).setImagesByQuery(Mockito.anyList())
    }

    @Test
    fun testImagesByQuery() {

        var main = Mockito.mock(Main::class.java)
        var mainPresenter = MainPresenter(RuntimeEnvironment.application, main)

        var q = ""

        mainPresenter.getImagesByQuery(q)
        Mockito.verify(main).setImagesByQuery(Mockito.anyList())
    }
}
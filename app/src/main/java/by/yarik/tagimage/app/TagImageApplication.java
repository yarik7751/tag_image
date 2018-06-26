package by.yarik.tagimage.app;

import android.app.Application;

import by.yarik.tagimage.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TagImageApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
            );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

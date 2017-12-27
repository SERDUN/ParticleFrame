package dmitriiserdun.gmail.com;

import android.app.Application;

/**
 * Created by dmitro on 27.12.17.
 */

public class App extends Application {
    public static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}

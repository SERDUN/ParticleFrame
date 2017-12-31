package dmitriiserdun.gmail.com.particleframe.engine;

import android.content.Context;
import android.graphics.Canvas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dmitro on 31.12.17.
 */

public abstract class Screen {
    protected AtomicBoolean initialized;
    protected Context context;

    public Screen(Context context) {
        this.initialized = new AtomicBoolean(false);
        this.context = context;
    }

    public abstract void init();

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime, Canvas canvas);

    public abstract void changedSize(float width, float height);
}

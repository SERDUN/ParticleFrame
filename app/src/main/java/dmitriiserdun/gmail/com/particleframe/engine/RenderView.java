package dmitriiserdun.gmail.com.particleframe.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dmitro on 31.12.17.
 */

public abstract  class RenderView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    public RenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}

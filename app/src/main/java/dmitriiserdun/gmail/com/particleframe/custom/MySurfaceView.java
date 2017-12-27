package dmitriiserdun.gmail.com.particleframe.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.steelkiwi.alias.ui.welcome.custom.ManagerTriangle;

/**
 * Created by dmitro on 27.12.17.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    ManagerTriangle managerTriangle = null;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        float width = MeasureSpec.getSize(widthMeasureSpec);
//        Drawable d = getDrawable();
//
//        float height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
//
//    }


    public void stop() {
        drawThread.setRunning(false);
    }

    public MySurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        managerTriangle = new ManagerTriangle(width, height);
        drawThread = new DrawThread(getHolder(), getResources(), managerTriangle);
        drawThread.setRunning(true);
        drawThread.start();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // завершаем работу потока
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }
}
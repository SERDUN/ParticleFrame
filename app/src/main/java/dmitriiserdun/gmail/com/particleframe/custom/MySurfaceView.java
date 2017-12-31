package dmitriiserdun.gmail.com.particleframe.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.steelkiwi.alias.ui.welcome.custom.ManagerTriangle;

import dmitriiserdun.gmail.com.particleframe.DrawThread.DrawThread;

/**
 * Created by dmitro on 27.12.17.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    DrawThread drawThread;
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
//        drawThread.setRunning(false);
    }

    public MySurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        ManagerTriangle.INSTANCE.setSizeContainer(width, height);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Log.d("thread_log","CREATE");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("thread_log","DESTROY");

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

    public void pause() {
        Log.d("thread_log","Pause start");


        drawThread.setRunning(false);
        try {
            // Stop the thread (rejoin the main thread)
            drawThread.join();
        } catch (InterruptedException e) {
        }

        Log.d("thread_log","Pause stop");

    }

    public void resume() {
        Log.d("thread_log","resume start");

        drawThread = new DrawThread(getHolder(), getResources());


        drawThread.setRunning(true);
        drawThread.start();

        Log.d("thread_log","resume stop");

    }


}
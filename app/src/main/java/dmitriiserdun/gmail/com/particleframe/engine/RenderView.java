package dmitriiserdun.gmail.com.particleframe.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dmitro on 31.12.17.
 */

public abstract class RenderView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    Thread renderThread = null;
    SurfaceHolder holder;
    protected Screen screen;
    AtomicBoolean running = new AtomicBoolean();
    AtomicBoolean render = new AtomicBoolean();

    public RenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
        running.set(false);
        render.set(false);
    }

    public void resume() {
        if (renderThread == null) {
            running.set(true);
            renderThread = new Thread(this);
            renderThread.start();
        }
    }


    public void run() {
        while (!render.get())
            continue;

        screen.init();
        long startTime = System.nanoTime();
        while (running.get()) {
            if (!render.get())
                continue;
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            screen.update(deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.drawRGB(255, 255, 255);
            screen.present(deltaTime, canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        running.set(false);
        if (renderThread != null) {
            boolean retry = true;
            while (retry) {
                try {
                    renderThread.join();
                    renderThread = null;
                    retry = false;
                } catch (InterruptedException e) {
                    //retry
                }
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        render.set(false);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        render.compareAndSet(false, true);
        screen.changedSize(width,height);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        render.set(false);

    }


}

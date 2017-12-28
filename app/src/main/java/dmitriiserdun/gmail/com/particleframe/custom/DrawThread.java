package dmitriiserdun.gmail.com.particleframe.custom;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

import com.steelkiwi.alias.ui.welcome.custom.ManagerTriangle;

/**
 * Created by dmitro on 27.12.17.
 */

public class DrawThread extends Thread {
    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private Bitmap picture;
    private long prevTime;
    private Paint mPaint;
    ManagerTriangle managerTriangle = null;

    float rotate = 0;
    float scale = 0;
    Matrix matrix;

    public DrawThread(SurfaceHolder surfaceHolder, Resources resources, ManagerTriangle managerTriangle) {
        this.surfaceHolder = surfaceHolder;
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        this.managerTriangle = managerTriangle;
        //  myShape = new MyShape();
        matrix = new Matrix();
//

    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (runFlag) {

            canvas = null;
            try {
                // получаем объект Canvas и выполняем отрисовку
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        canvas.drawColor(Color.WHITE);
                        for (int i = 0; i < managerTriangle.getTriangles().size(); i++) {

                            ManagerTriangle.Triangle triangleModel = managerTriangle.getTriangles().get(i);
                            triangleModel.setScale((float) (triangleModel.getScale() + triangleModel.getDeltaScale()));
                            if (triangleModel.getScale() > 1.1) {
                                triangleModel.setDeltaScale(triangleModel.getDeltaScale() * -1);
                            }

                            if (triangleModel.getScale() < 0.5) {
                                triangleModel.setDeltaScale(triangleModel.getDeltaScale() * -1);
                                triangleModel.setScale(0.5f);

                            }


                            mPaint.setColor(triangleModel.getColor());
                            drawTriangle(canvas, mPaint, triangleModel.getX(), triangleModel.getY(), triangleModel.getSize(), rotate + triangleModel.getRotate(), triangleModel.getScale());
                        }


                        rotate++;

                    }
                }
            } finally {
                if (canvas != null) {
                    // отрисовка выполнена. выводим результат на экран
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void drawTriangle(Canvas canvas, Paint paint, float x, float y, float width, float rotate, float scale) {
        float halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();


        matrix.reset();
        matrix.setScale(scale, scale, x, y);
        matrix.postRotate(rotate, x, y);

        path.transform(matrix);

        canvas.drawPath(path, paint);
    }


}
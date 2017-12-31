package dmitriiserdun.gmail.com.particleframe.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

import com.steelkiwi.alias.ui.welcome.custom.ManagerTriangle

/**
 * Created by dmitro on 31.12.17.
 */

class CustomViewTest(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    internal var mPaint: Paint? = null
    internal var managerTraingle: ManagerTriangle? = null


    internal var rotate = 0f
    internal var matrix: Matrix


    init {
        this.managerTraingle = ManagerTriangle
        this.mPaint = HolderAnimation.getPaintInstance()
        this.matrix = Matrix()


        ManagerTriangle.setSizeContainer(480f,840f)

    }


    override fun onDraw(canvas: Canvas) {
        render(canvas)
        invalidate()

    }

    private fun render(canvas: Canvas?) {
        for (i in 0 until managerTraingle!!.triangles.size) {

        //    if (!runFlag) break
            val triangleModel = managerTraingle!!.triangles[i]
            triangleModel.scale = (triangleModel.scale + triangleModel.deltaScale).toFloat()
            if (triangleModel.scale > 1.1) {
                triangleModel.deltaScale = triangleModel.deltaScale * -1
            }
                    //  if (!runFlag) break

            if (triangleModel.scale < 0.5) {
                triangleModel.deltaScale = triangleModel.deltaScale * -1
                triangleModel.scale = 0.5f

            }
           // if (!runFlag) break

            mPaint!!.color = triangleModel.color
            drawTriangle(canvas!!, mPaint!!, triangleModel.x, triangleModel.y, triangleModel.size, rotate + triangleModel.rotate, triangleModel.scale)
        }
    }

    fun drawTriangle(canvas: Canvas, paint: Paint, x: Float, y: Float, width: Float, rotate: Float, scale: Float) {
        val halfWidth = width / 2

        val path = Path()
        path.moveTo(x, y - halfWidth) // Top
        path.lineTo(x - halfWidth, y + halfWidth) // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth) // Bottom right
        path.lineTo(x, y - halfWidth) // Back to Top
        path.close()
        //if (!runFlag) return


        matrix.reset()
        matrix.setScale(scale, scale, x, y)
        matrix.postRotate(rotate, x, y)
       // if (!runFlag) return

        path.transform(matrix)
  //      if (!runFlag) return

        canvas.drawPath(path, paint)
    }

}

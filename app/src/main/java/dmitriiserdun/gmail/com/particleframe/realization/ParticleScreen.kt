package dmitriiserdun.gmail.com.particleframe.realization

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import com.steelkiwi.alias.ui.welcome.custom.ManagerTriangle
import dmitriiserdun.gmail.com.particleframe.custom.HolderAnimation

import dmitriiserdun.gmail.com.particleframe.engine.Screen

/**
 * Created by dmitro on 31.12.17.
 */

class ParticleScreen(context: Context) : Screen(context) {


    private var scale: Float = 0.toFloat()
    private var mPaint: Paint? = null
    internal var managerTraingle: ManagerTriangle? = null

    internal var rotate = 0f
    internal lateinit var matrix: Matrix

    override fun init() {
        this.managerTraingle = ManagerTriangle
        this.mPaint = HolderAnimation.getPaintInstance()
        this.matrix = Matrix()
    }

    override fun update(deltaTime: Float) {
        for (i in 0 until managerTraingle!!.triangles.size) {

            val triangleModel = managerTraingle!!.triangles[i]
            triangleModel.scale = (triangleModel.scale + triangleModel.deltaScale).toFloat()
            if (triangleModel.scale > 1.1) {
                triangleModel.deltaScale = triangleModel.deltaScale * -1
            }

            if (triangleModel.scale < 0.5) {
                triangleModel.deltaScale = triangleModel.deltaScale * -1
                triangleModel.scale = 0.5f

            }


            triangleModel.rotate++
        }

    }

    override fun changedSize(width: Float, height: Float) {
        ManagerTriangle.setSizeContainer(width, height)

    }

    override fun present(deltaTime: Float, canvas: Canvas) {
        for (i in 0 until managerTraingle!!.triangles.size) {
            val triangleModel = managerTraingle!!.triangles[i]
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


        matrix.reset()
        matrix.setScale(scale, scale, x, y)
        matrix.postRotate(rotate, x, y)

        path.transform(matrix)

        canvas.drawPath(path, paint)
    }
}

package dmitriiserdun.gmail.com.particleframe.custom

import android.graphics.Color
import android.graphics.Paint

/**
 * Created by dmitro on 30.12.17.
 */
object HolderAnimation {
    var paint: Paint? = null
    fun getPaintInstance(): Paint {
        if (paint == null) {
            paint = createPaint()
        }
        return paint as Paint
    }

    private fun createPaint(): Paint {
        paint = Paint()
        paint!!.color = Color.BLUE
        paint!!.strokeWidth = 10f
        paint!!.isAntiAlias = true

        return paint as Paint

    }

}
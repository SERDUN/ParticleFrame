package dmitriiserdun.gmail.com.particleframe.custom

import android.content.Context
import android.util.DisplayMetrics


/**
 * Created by bohdan on 12/8/17.
 */
object Tools {

    fun convertPixelsToDp(px: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }





}
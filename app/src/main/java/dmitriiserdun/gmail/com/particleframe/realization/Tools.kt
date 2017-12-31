package dmitriiserdun.gmail.com.particleframe.custom

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue


/**
 * Created by bohdan on 12/8/17.
 */
object Tools {

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px.toFloat(),context.resources.displayMetrics)

    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),context.resources.displayMetrics)

    }





}
package com.steelkiwi.alias.ui.welcome.custom

import android.graphics.Color
import android.util.Log
import dmitriiserdun.gmail.com.App
import dmitriiserdun.gmail.com.particleframe.custom.Tools
import java.util.*

/**
 * Created by serdun on 12/27/17.
 */

class ManagerTriangle(widthContainer: Float, heightContainer: Float) {
    //        internal set
    val colors: Array<String> = arrayOf("#4ddcdf", "#c185f4", "#f6c235", "#f35f9c")
    val random = Random(System.currentTimeMillis())
    var triangles = ArrayList<Triangle>()


    init {
        generateTriangle(widthContainer, heightContainer)
//        createVerticalArraysTriangle(deltaX, sizeTriangle, margin, countVerticalTriangles, rnd, positionXOne, positionXTwo, 0f)
//        createVerticalArraysTriangle(deltaX, sizeTriangle, margin, countVerticalTriangles, rnd, positionXOne, positionXTwo, width - rightScale)
//
//        createHorizontalArraysTriangle(deltaY, sizeTriangle, margin, countHorizontalTriangles, rnd, positionYOne, positionYTwo, 0f)
//        createHorizontalArraysTriangle(deltaY, sizeTriangle, margin, countHorizontalTriangles, rnd, positionYOne, positionYTwo, height - bottomScale)
//

    }

    private fun generateTriangle(width: Float, height: Float) {

        ///////////////////
        val widtDp = convertPixelToDp(width)
        val heightDp = convertDpToPixel(height)

        val baseMarginLeft = 2
        val baseMarginTop = 2

        val horizontalMarginDp = 5
        val verticalMarginDp = 5
        val sizeTriangleDp = 15
        var widthVerticalItem = horizontalMarginDp * 2 + sizeTriangleDp
        var heightVerticalItem = verticalMarginDp * 2 + sizeTriangleDp

        var countHorizontalItem = width / widthVerticalItem
        var countVerticalItem = height / heightVerticalItem

        var horizontalDisplacement = 2
        var verticalDisplacement = 2
        //////////////////


        ///////////////////////
        var centerX = width/2
        var centerY = height/2


        var innerRectangleWidth = convertDpToPixel(width*0.8f)
        var innerRectangleHeight = convertDpToPixel(height*0.9f)


        /////////////////////


        for (i in 0..(countHorizontalItem.toInt()).toInt()) {
            for (j in 0..(countVerticalItem.toInt()).toInt()) {

                var x = convertDpToPixel(((i * (widthVerticalItem )) + baseMarginLeft).toFloat())
                var y = convertDpToPixel(((j * (heightVerticalItem )) + baseMarginTop).toFloat())
                /////////
               if (!checkEntryPoint(x, y, centerX, centerY, innerRectangleWidth, innerRectangleHeight)) {
                    ////////

                   if(i%2==0){
                       y+=convertDpToPixel(8f)
                   }

                   if(j%2==0){
                       y+=convertDpToPixel(8f)
                   }

                   if(j%2==1){
                       x+=convertDpToPixel(8f)
                   }


                    var size = convertDpToPixel(sizeTriangleDp.toFloat())
                    val randomColor = 0 + random.nextInt(3 - 0 + 1)
                   val color = Color.parseColor(colors[randomColor])

                   var triangle = Triangle(x, y, size, 30f, 1f, color)
                    triangles.add(triangle)
                }

            }

        }


        Log.d("", "sd")


    }

    inner class Triangle(var x: Float, var y: Float, var size: Float, var rotate: Float, var scale: Float, var color: Int) {
        var deltaScale = 0.009
    }

    private fun convertPixelToDp(pixels: Float): Float {
        return Tools.convertPixelsToDp(pixels, App.instance)
    }

    private fun convertDpToPixel(dp: Float): Float {
        return Tools.convertDpToPixel(dp, App.instance)
    }


    private fun checkEntryPoint(x: Float, y: Float, xCenter: Float, yCenter: Float, width: Float, height: Float): Boolean {

        var minX = xCenter - width / 2
        var maxX = xCenter + width / 2

        var minY = yCenter - height / 2
        var maxY = yCenter + height / 2


        return x > minX && x < maxX && y > minY && y < maxY


    }
}

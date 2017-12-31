package com.steelkiwi.alias.ui.welcome.custom

import android.graphics.Color
import android.util.Log
import dmitriiserdun.gmail.com.App
import dmitriiserdun.gmail.com.particleframe.custom.Tools


import java.util.*

/**
 * Created by serdun on 12/27/17.
 */

object ManagerTriangle {
    //        internal set

    var pause: Boolean = false


    var isRunning = false;
    public var widthContainer: Float = 0f
    public var heightContainer: Float = 0f

    fun setSizeContainer(width: Float, height: Float) {
        this.widthContainer = width
        this.heightContainer = height
        createTriangles()
    }

    val colors: Array<String> = arrayOf("#4ddcdf", "#c185f4", "#f6c235", "#f35f9c")
    val random = Random(System.currentTimeMillis())
    var triangles = ArrayList<Triangle>()


    fun createTriangles() {
        if (triangles.isEmpty())
            generateTriangle(widthContainer, heightContainer)

    }

    var widthVerticalItem = 0
    var heightVerticalItem = 0
    private fun generateTriangle(width: Float, height: Float) {
        ///////////////////
        val widthDp = convertPixelToDp(width)
        val heightDp = convertPixelToDp(height)

        val baseMarginLeft = 0
        val baseMarginTop = 0

        val horizontalMarginDp = 8
        val verticalMarginDp = 8
        val sizeTriangleDp = 15

        widthVerticalItem = horizontalMarginDp * 2 + sizeTriangleDp
        heightVerticalItem = verticalMarginDp * 2 + sizeTriangleDp

        var countHorizontalItem = widthDp / widthVerticalItem
        var countVerticalItem = heightDp / heightVerticalItem

        var horizontalDisplacement = 2
        var verticalDisplacement = 2
        //////////////////


        ///////////////////////
        var centerX = width / 2
        var centerY = height / 2


        var innerRectangleWidth = width / 1.4f
        var innerRectangleHeight = height / 1.4f


        /////////////////////


        for (i in 0..(countHorizontalItem.toInt())) {
            for (j in 0..(countVerticalItem.toInt())) {




                var x = convertDpToPixel(((i * (widthVerticalItem)) + baseMarginLeft).toFloat())
                var y = convertDpToPixel(((j * (heightVerticalItem)) + baseMarginTop).toFloat())
                /////////
                if (!checkEntryPoint(x, y, centerX, centerY, innerRectangleWidth, innerRectangleHeight)) {
                    ////////
                    /////// ЗМІЩЕНННЯ
                    if (i % 2 == 0) {
                        y += convertDpToPixel(8f)
                    }

                    if (j % 2 == 0) {
                        y += convertDpToPixel(8f)
                    }

                    if (j % 2 == 1) {
                        x += convertDpToPixel(8f)
                    }
                    //Вугол повороту

                    val randomRotate = 0 + random.nextInt(360 - 0 + 1)

                    //SCALE
                    val scaleTriangle = (700 + random.nextInt(1100 - 700 + 1)) / 1000


                    var size = convertDpToPixel(sizeTriangleDp.toFloat())
                    val randomColor = 0 + random.nextInt(3 - 0 + 1)
                    val color = Color.parseColor(colors[randomColor])

                    var triangle = Triangle(x, y, size, randomRotate.toFloat(), scaleTriangle.toFloat(), color)
                    triangles.add(triangle)
                }

            }

        }


        Log.d("", "sd")


    }

    class Triangle(var x: Float, var y: Float, var size: Float, var rotate: Float, var scale: Float, var color: Int) {
        var deltaScale = 0.009
    }

    private fun convertPixelToDp(pixels: Float): Float {
        return Tools.convertPixelsToDp(pixels, App.instance).toFloat()
    }

    private fun convertDpToPixel(dp: Float): Float {
        return Tools.convertDpToPixel(dp, App.instance).toFloat()
    }


    private fun checkEntryPoint(x: Float, y: Float, xCenter: Float, yCenter: Float, width: Float, height: Float): Boolean {

        var minX = xCenter - width / 2
        var maxX = xCenter + width / 2

        var minY = yCenter - height / 2
        var maxY = yCenter + height / 2


        return x > minX && x < maxX && y - heightVerticalItem > minY && y - heightVerticalItem < maxY


    }
}

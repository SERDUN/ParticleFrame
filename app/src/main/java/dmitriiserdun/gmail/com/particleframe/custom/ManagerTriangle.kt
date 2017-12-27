package com.steelkiwi.alias.ui.welcome.custom

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
        val widtDp = convertPixelToDp(width)
        val heightDp = convertDpToPixel(height)
        val horizontalMarginDp = 3
        val verticalMarginDp = 3
        val sizeTriangleDp = 5
        var widthVerticalItem = horizontalMarginDp * 2 * sizeTriangleDp
        var heightVerticalItem = verticalMarginDp * 2 * sizeTriangleDp

        var countHorizontalItem = heightDp / heightVerticalItem
        var countVerticalItem = widtDp / widthVerticalItem

        var horizontalDisplacement = 2
        var verticalDisplacement = 2

        for (i in 0..countHorizontalItem.toInt()) {
            for (j in 0..countVerticalItem.toInt()) {

                var x = convertDpToPixel((i * (widthVerticalItem / horizontalDisplacement)).toFloat())
                var y = convertDpToPixel((j * (heightVerticalItem / verticalDisplacement)).toFloat())
                var size = convertDpToPixel(sizeTriangleDp.toFloat())
                val randomColor = 0 + random.nextInt(3 - 0 + 1)

                var triangle = Triangle(x, y, size, 30f, 1f, colors[randomColor])
                triangles.add(triangle)

            }

        }


        Log.d("", "sd")


    }

//    private fun createVerticalArraysTriangle(deltaX: Float, sizeTriangle: Float, margin: Float, countHorizontalTriangles: Int, rnd: Random, positionYOne: Float, positionYTwo: Float, xCentral: Float) {
//
//        for (i in 0 until countHorizontalTriangles) {
//            val randomRotate = 0 + rnd.nextInt(360 - 0 + 1)
//            val randomTriangleSize = 10 + rnd.nextInt(15 - 10 + 1)
//            val randomTriangleScale = 700 + rnd.nextInt(1100 - 700 + 1)
//            val randomColor = 0 + rnd.nextInt(3 - 0 + 1)
//
//            val randDeltaX = 0 + rnd.nextInt((deltaX / 5 - 0 + 1).toInt())
//
//            var x: Float
//            if (i % 2 == 0) {
//                x = positionYOne - randDeltaX
//            } else {
//                x = positionYTwo - randDeltaX
//            }
//            x = x + xCentral
//            val y = Tools.convertDpToPixel(((sizeTriangle + margin * 2) * i).toInt(), AliasApp.instance).toFloat()
//            val size = Tools.convertDpToPixel((sizeTriangle + randomTriangleSize).toInt(), AliasApp.instance).toFloat()
//            arrayList.add(Triangle(x, y, size, randomRotate.toFloat(), (randomTriangleScale / 1000).toFloat(), colors[randomColor]))
//        }
//    }


    //    private fun createHorizontalArraysTriangle(deltaY: Float, sizeTriangle: Float, margin: Float, countTriangles: Int, rnd: Random, positionYOne: Float, positionYTwo: Float, yCentral: Float) {
//
//        for (i in 0 until countTriangles) {
//            val randomRotate = 0 + rnd.nextInt(360 - 0 + 1)
//            val randomTriangleSize = 10 + rnd.nextInt(15 - 10 + 1)
//            val randomTriangleScale = 700 + rnd.nextInt(1100 - 700 + 1)
//            val randomColor = 0 + rnd.nextInt(3 - 0 + 1)
//
//            val randDeltaY = 0 + rnd.nextInt((deltaY / 5 - 0 + 1).toInt())
//
//            var y: Float
//            if (i % 2 == 0) {
//                y = positionYOne + randDeltaY
//            } else {
//                y = positionYTwo + randDeltaY
//            }
//            y += yCentral
//            val x = Tools.convertDpToPixel(((sizeTriangle + margin * 2) * i).toInt(), AliasApp.instance).toFloat()
//            val size = Tools.convertDpToPixel((sizeTriangle + randomTriangleSize).toInt(), AliasApp.instance).toFloat()
//            arrayList.add(Triangle(x, y, size, randomRotate.toFloat(), (randomTriangleScale / 1000).toFloat(), colors[randomColor]))
//        }
//    }
//
//
    inner class Triangle(var x: Float, var y: Float, var size: Float, var rotate: Float, var scale: Float, var color: String) {
        var deltaScale = 0.009
    }

    private fun convertPixelToDp(pixels: Float): Float {
        return Tools.convertPixelsToDp(pixels, App.instance)
    }

    private fun convertDpToPixel(dp: Float): Float {
        return Tools.convertDpToPixel(dp, App.instance)
    }

}

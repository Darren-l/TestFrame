package cn.gd.snm.glide

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import cn.gd.snm.tools.BlurTransformation
import cn.gd.snm.tools.RotateTransformation
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


/**
 * glide的高级用法。
 *
 * 支持的网络协议栈：okhttp和Volley
 *
 *
 */
class TestHighLevel(private var context: Context, private var img:ImageView) {


    /**
     * 使用glide旋转图片。
     *
     */
    fun testRotate(url:String, img:ImageView){
        //旋转图片也算是变换中的一种特效。
        Glide.with(context).load(url).transform(RotateTransformation()).into(img)
    }


    /**
     * 占位图与原图切换定制动画
     *
     *  官网：控制从占位符到图片和/或缩略图到全图的交叉淡入和其他类型变换的选项，被移动到了 TransitionOptions 中,
     * 通过transition接口进行传递。
     *
     * 简单说说，切换的效果都交由TransitionOptions。
     *
     * TransitionOptions有三个实现类：
     *
     *  GenericTransitionOptions    ：提供animate自定义xml的加载接口
     *  BitmapTransitionOptions
     *  DrawableTransitionOptions   ：提供默认的crossFades效果
     *
     *  若需要定义一个自定义的过度动画，则需要：
     *      1. 实现 TransitionFactory
     *      2. 使用 DrawableTransitionOptions#with 来将你自定义的 TransitionFactory 应用到加载中，具体参照官网demo。
     *
     *
     */
    fun testTransition(url:String, img:ImageView){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img)
            .transition(withCrossFade())   //交叉淡入。
//            .transition(GenericTransitionOptions.with(R.anim.animate1))   //可以使用自定义的动画，但是测试发现placeholder失效
            .into(img)
    }

    /**
     * glide 自定义变化图片。就是实现图片的特效，如高斯模糊，黑白颜色，放大镜等功能。
     *
     *  note：
     *      1、 glide-transformations中自带的高斯模糊在运行时会有异常，可以复制代码，放到项目中。
     *      2、高斯模糊在低性能设备上，再图片尺寸较大的情况下，可能会引起卡顿，建议由glide指定较小的尺寸，再进行放大。
     *
     */
    fun testTransformation(url:String, img:ImageView){
        Log.e("test123","testTransformation...")
        Glide.with(context)
            .load(R.drawable.img)
//            .apply(RequestOptions.bitmapTransform(BlurTransformation(context,25)))
            .transform(BlurTransformation(context,25))  //允许传多个进行组合
            .override(200,200)
            .into(img)
    }


}
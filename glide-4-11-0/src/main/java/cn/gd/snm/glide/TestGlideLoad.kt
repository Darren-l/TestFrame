package cn.gd.snm.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioRecord.MetricsConstants.SOURCE
import android.net.Uri
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception


/**
 * glide的常用加载类型。
 *
 * 1. glide能做什么。
 * 2. glide的高级用法
 *
 */
class TestGlideLoad(private var context: Context) {

    companion object {
        val picUrl = "https://tse1-mm.cn.bing.net/th/id/OIP-C.9Lyy1G1N0Fy7_YuKtKT3nAHaEo?w=268&h=180&c=7&r=0&o=5&dpr=2.5&pid=1.7"
        val gifUrl = "https://img.zcool.cn/community/01c5695a52dac5a8012180c527476b.gif"
    }

    //todo glide甚至可以用于加载视频，但只能加载本地的视频。
    fun testLoadVideo(mImageView: ImageView) {
        val filePath = "/storage/emulated/video.mp4"
        Glide.with(context)
            .load(Uri.fromFile(File(filePath)))
            .into(mImageView)
    }

    //todo 控制gif图是否要加载，通常用于recyclerview滑动时的优化。
    fun testControlRequest(mImageView: ImageView) {
        Glide.with(context).load(picUrl).into(mImageView)
        Glide.with(context).pauseRequests() //立刻停止加载，已经加载的不受影响。
        Glide.with(context).pauseAllRequests()  //停止加载，已经加载也会被清除。
        Glide.with(context).resumeRequests()    //恢复加载
    }

    //todo 加载gif图和普通图片没有区别。
    fun testLoadGif(mImageView: ImageView) {
        Glide.with(context)
            .asGif()        //判断是否为gif图
            .error(context.resources.getDrawable(R.drawable.img, null))   //如果不是gif图，则直接加载img
            .load(gifUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)  //压缩gif，优化加载。
            .into(mImageView)
    }

    //todo 图片显示前为图片显示指定大小（通常就是img的大小）。如果给图片设置的很小，但实际的img尺寸很大，
    // 那么就会引起图片的模糊。
    fun testFitImg(url: String, mImageView: ImageView) {
        Glide.with(context)
            .load(url)
            .override(50, 50)
            .centerCrop()   //等比放大，充满整个img，会裁掉多余的部分。
//            .fitCenter()     //显示图片的全部，但可能会出现宽或高填充不满img
//            .centerInside()
            .into(mImageView)
    }

    //todo 用于占位图与主图之间的动画切换
    fun testCrossFade(mImageView: ImageView, errorId: Int, url: String, placeholderId: Int) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholderId)
            .transition(withCrossFade(2000))   //支持设置动画时长、动画效果
//            .dontAnimate()    //默认是有动画的，使用该设置可以关闭动画。
            .error(errorId)
            .into(mImageView)
    }

    //todo error 用于加载出错时的占位图
    fun testErrorHolder(mImageView: ImageView, errorId: Int, url: String) {
        Glide.with(context)
            .load(url)
            .error(errorId)
            .into(mImageView)
    }

    //todo placeHolder用于异步加载图片暂未请求回来时的占位图
    fun testPlaceHolder(mImageView: ImageView, placeholderId: Int, url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholderId)
            .into(mImageView)
    }


    //todo File加载
    fun loadFile(mImageView: ImageView) {
        val storePath = "mnt/sdcard/test.jpg"
        val file = File(storePath)
        Glide.with(context)
            .load(file)
            .into(mImageView)
    }

    //todo: resourceId加载
    fun loadResourceId(mImageView: ImageView, resourceId: Int) {
        Glide.with(context)
            .load(resourceId)
            .into(mImageView)
    }

    //todo: url加载
    fun loadUrl(mImageView: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .into(mImageView)
    }

    //todo 加载byte[]
    fun loadByteArray(mImageView: ImageView, resourceId: Int) {
        val sourceBitmap: Bitmap =
            BitmapFactory.decodeResource(context.resources, resourceId)
        val bArrayOS = ByteArrayOutputStream()
        sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, bArrayOS)
        sourceBitmap.recycle()
        val byteArray: ByteArray = bArrayOS.toByteArray()
        try {
            bArrayOS.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Glide.with(context)
            .load(byteArray)
            .into(mImageView)
    }
}
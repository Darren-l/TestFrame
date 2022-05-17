package cn.gd.snm.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition


/**
 * 测试glide 进阶用法。
 *
 *  Note：with的context决定的是request的请求生命周期，如果context是act，那么act退出时，所有glide请求都会停止。
 *  必要的请求可以使用application的，与当前应用绑在一起。
 *
 */
class TestAdvance(private var context: Context) {


    /**
     * 测试glide的log日志。
     *
     *  打开debug调试：
     *      adb shell setprop log.tag.GenericRequest DEBUG
     *
     * listener:添加单独的日志监听器
     *
     */
    fun TestLog(url:String, img:ImageView, errImg:Int){

        var lis = object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                //加载失败
                Log.e("test", "Load failed", e);

                return false       //返回false，才会加载errot图
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false  //返回false，允许加载资源。
            }

        }

        Glide.with(context)
            .load(url)
            .listener(lis)
            .error(errImg)
            .into(img)
    }


    /**
     * 使用glide获取bitmap。用于只获取图片资源，不进行img的加载。
     *
     */
    fun TestTarget(url:String){
        //todo：需要bitmap。最好将target定义为类成员，若定义成匿名对象，可能存在异步加载的过程中，匿名对象被回收。
        val target = object: CustomTarget<Bitmap>(200,200){ //可以在构造中指定图片宽高，也可以不指定。
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                //todo 这里可以获取到图片下载完成后的bitmap
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        }
        Glide.with(context)
            .asBitmap()     //指定需要一个bitmap，没拿到bitmap就算失败。
            .load(url)
            .into(target)

        //todo： 需要drawable。
        var view = View(context)
        val drawableTar = object : CustomViewTarget<View,Drawable>(view){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                //todo 这里返回drawable对象。
                resource.current

                //todo 这里就是构造中传进去的view，方便直接获取并操作。如果不需要可以直接new匿名对象。
                this.view
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
            }

            override fun onResourceCleared(placeholder: Drawable?) {
            }
        }
    }

    /**
     * 测试缩略图。所谓的缩略图就是一种动态的占位图，效果就是从模糊到清晰的过程。
     *
     *
     */
    fun TestThumbnail(url:String, img:ImageView){

        //todo 方式一，指定缩略与原图的比例尺寸
        Glide.with(context)
            .load(url)
            .thumbnail(0.1f)    //首次加载的占位图占原图的尺寸。
            .into(img)

        //todo 方式二， 自己准备缩略图，把缩略图发放前面，最后一张为原图，glide会依次加载。
        var imgThum = arrayOf("url","url2")
        var requestBuilder = Glide.with(context).load(imgThum)

        Glide.with(context).load(url).thumbnail(requestBuilder).into(img)

    }


    /**
     * 设置缓存相关：缓存分两级 --- 磁盘缓存和内存缓存。
     *
     * glide除了会缓存全尺寸图片以外，还会缓存img对应的缩小图片。
     *
     * 磁盘缓存：
     *  DiskCacheStrategy.NONE：不缓存。
     *  DiskCacheStrategy.SOURCE：缓存全尺寸原图。
     *  DiskCacheStrategy.RESULTL：缓存img用到的处理后的图片。
     *  DiskCacheStrategy.ALL：缓存所有类型图片。
     *
     */
    fun TestCache(url:String, img:ImageView){
        Glide.with(context)
            .load(url)
            .skipMemoryCache(true)  //跳过内存缓存，默认为false
            .diskCacheStrategy(DiskCacheStrategy.NONE)  //设置禁止磁盘缓存。
            .into(img)
    }

    /**
     * 设置图片请求优先级。app同时加载多个图片时，优先级高的会优先请求并显示。
     *
     * 优先级：
     *  Priority.LOW、Priority.NORMAL、Priority.HIGH、Priority.IMMEDIATE
     *
     */
    fun TestPriority(url:String, img:ImageView){
        Glide.with(context)
            .load(url)
            .priority(Priority.LOW) //设置当前img的加载优先级为低
            .into(img)
    }
}
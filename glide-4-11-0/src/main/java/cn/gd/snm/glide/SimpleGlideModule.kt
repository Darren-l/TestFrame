package cn.gd.snm.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


/**
 *
 * GlideModule的两个重要回调：
 *      applyOptions：设置glide核心配置。
 *      registerComponents：处理请求相关。
 *
 * GlideModule用于控制glide全局的行为。例如统一将glide默认的低质量RGB565格式，全部变成ARGB888。
 * 有点类似于okhttp的拦截器。还能自定义干预处理glide的请求。
 *
 * 在glide v3版本之前，是使用androidManifest中写meta的方式制定配置的model，在当前的v4版本，修改为使用注解，但
 * 仍然兼容支持manifest。
 *
 *
 * ###关于添加图片大小到请求中的实现：
 * 实现位置：GlideModule中重写registerComponents方法
 *
 *  ModelLoaderFactory：添加图片大小到请求中，支持glide动态向服务器请求指定大小的图片，这样就不需要glide再对图片
 * 进行压缩处理，需要服务端配合处理。
 *      如假设glide加载图片的img宽为400，高为300，那么可以通过该工厂，动态去处理请求，如下：
 *          https://futurestud.io/images/logo.png?w=400&h=300。
 *      此时服务端通过获取不同的w和h，返回不同的图片。这样可以优化图片加载性能，可以规避glide去做图片缩放的计算。
 *
 *  在GlideModule中处理，将会导致这个处理是全局的，所有的加载都会执行。glide也提供给指定加载做处理的方式，方式如下：
 *      Glide.with(context).with(CustomImageSizeLoader).load(customImageRequest).into(img)
 *  需要自定义处理CustomImageSizeLoader、customImageRequest。
 *
 *
 *  ### 总结
 *      若是希望效果是全局的，则需要在AppGlideModule中做处理，单次加载的则需要在Glide.with时做处理。
 *
 *
 */
@GlideModule
class SimpleGlideModule: AppGlideModule(){

    /**
     * GlideBuilder：对象可以访问到Glide的核心部分，可以改变磁盘缓存、内存缓存等等。
     *
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        //todo 设置内存缓存。
//        builder.setMemoryCache()
        //todo 设置bitmap池。
//        builder.setBitmapPool()
        //todo 设置磁盘缓存。
//        builder.setDiskCache()
        //todo 设置图片解码格式，强制覆盖原有，作用于每一个加载。
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
        //todo 设置日志级别
        builder.setLogLevel(Log.DEBUG)

        testOptionCache(context,builder)
    }

    /**
     * 测试glide自定义缓存。
     *
     * 注意，不能直接设置大小，需要通过相应的类。
     *
     *
     */
    private fun testOptionCache(context: Context, builder: GlideBuilder) {
        //获取内存缓存
        var memoryCache = MemorySizeCalculator.Builder(context).build()
        //默认的内存缓存大小
        memoryCache.memoryCacheSize
        //默认的bitmapPool缓存大小
        memoryCache.bitmapPoolSize

        //重置内存cachesize、bitmapsize
        builder.setMemoryCache(LruResourceCache((memoryCache.memoryCacheSize * 1.2).toLong()))
        builder.setBitmapPool(LruBitmapPool((memoryCache.bitmapPoolSize*1.5).toLong()))

        //自定义磁盘缓存大小
        builder.setDiskCache(InternalCacheDiskCacheFactory(context,1048*10000))
        //修改缓存path路径及大小
//        builder.setDiskCache(DiskLruCacheFactory("path",1048*10000))

    }

    /**
     *
     * 1. 可用于修改网格站，解决https引起的加载问题。
     * 2. ModelLoaderFactory动态处理请求地址。
     *
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        //todo 请求地址动态添加宽高
//        registry.register()
    }

}
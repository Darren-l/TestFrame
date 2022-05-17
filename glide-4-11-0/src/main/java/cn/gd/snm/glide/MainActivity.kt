package cn.gd.snm.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 用于测试glide常用接口使用及源码分析
 *
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo 测试基础使用
//        testGlide()

        //todo 测试常用加载
//        testCommonLoad()

        //todo 测试高级用法
        testHighLevel()


    }


    /**
     * 测试glide的高级用法。
     *
     */
    private fun testHighLevel() {
        var testHigh = TestHighLevel(this,test_img)

        //todo 测试变换 -- 高斯模糊、放大等
//        testHigh.testTransformation(TestGlideLoad.picUrl, test_img)

        //todo 测试过度
//        testHigh.testTransition(TestGlideLoad.picUrl, test_img)

        //todo 测试旋转
        testHigh.testRotate(TestGlideLoad.picUrl,test_img)

    }

    private fun testCommonLoad() {

        var testGlideMethod = TestGlideLoad(this)

        //普通加载
//        testGlideMethod.loadUrl(test_img,imgUrl)

        //指定图片大小，通常是已经知道了img的宽高后，才能指定。
//        testGlideMethod.testFitImg(imgUrl,test_img)

        //加载gif图
        testGlideMethod.testLoadGif(test_img)
    }

    //网络图片测试地址
    var imgUrl = "http://g.hiphotos.baidu.com/image/pic/item/" +
            "6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg"
    /**
     * 加载图片的最简方式
     *
     * with(): 选择Glide的依附体，通过重载方法可以看出，类型可以是fragment，activity，view,也能直接传一个context。
     *  返回类型RequestManager: 通过代码注释可以看出，该类用于请求的控制，手段是关联lifeCycle的生命周期。
     *
     * load(): 选择需要加载的资源，这里可以是file、drawable、resource、model(byte[])等类型。
     *  RequestBuilder<Drawable>:代码注释可以看出，该类主要是个数据的包装类，用于包装图片的资源来源。同时也可以用于包装占位
     *  图，出错图等。
     *
     * into(): 选择加载资源的容器，一般是imgView。
     *  ViewTarget： 执行加载bitmap到view容器中。
     *
     *
     */
    private fun testGlide() {
        Log.d("test","testGlide...")
        //todo 加载drawable中的图片
//        Glide.with(this).load(R.drawable.img).into(test_img)

        //todo 加载网络图片
        Glide.with(this).load(imgUrl).into(test_img)

        //todo 取消加载 - 在activity和fragment中不需要取消，会监听生命周期，自行取消。
//        Glide.with(this).clear(test_img)

    }
}
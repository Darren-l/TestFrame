package cn.gd.snm.glide_4_8_0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()
    }

    private val gifUrl = "https://img.zcool.cn/community/01c5695a52dac5a8012180c527476b.gif"
    private fun test() {
        Glide.with(this).load(gifUrl).into(img)
    }
}
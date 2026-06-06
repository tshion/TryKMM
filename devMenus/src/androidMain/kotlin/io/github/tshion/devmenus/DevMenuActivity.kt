package io.github.tshion.devmenus

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity

/**
 * 開発者メニュー画面
 */
public class DevMenuActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val provider = application as? DevMenuProvider
        setContent {
            ViewHost(provider?.getDevMenuList())
        }
    }
}

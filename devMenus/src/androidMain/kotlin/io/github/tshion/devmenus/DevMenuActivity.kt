package io.github.tshion.devmenus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * 開発メニュー画面
 */
public class DevMenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val provider = application as? DevMenuProvider
        setContent {
            val viewModel = DevMenuSpecViewModel.create(
                specs = provider?.devMenuList,
            )
            ViewHost(viewModel)
        }
    }
}

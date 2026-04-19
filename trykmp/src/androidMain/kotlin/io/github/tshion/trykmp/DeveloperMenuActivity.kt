package io.github.tshion.trykmp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity

public class DeveloperMenuActivity : ComponentActivity() {

    internal companion object {

        fun createIntent(
            context: Context,
        ): Intent = Intent(
            Intent.ACTION_VIEW,
            Uri.EMPTY,
            context,
            DeveloperMenuActivity::class.java,
        )
    }
}

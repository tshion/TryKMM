package io.github.tshion.trykmp.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import io.github.tshion.trykmp.TryKmp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(
                        viewModel = MainViewModel.create(
                            tryKmp = TryKmp(),
                            viewModelStore = this.viewModelStore,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(
    viewModel: MainViewModel,
) {
    var isTapped by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val repos by viewModel.repos.collectAsStateWithLifecycle()
        val timeText by viewModel.timeText.collectAsStateWithLifecycle()

        Text(timeText)
        HorizontalDivider()
        if (!isTapped) {
            Button(onClick = {
                isTapped = !isTapped
                viewModel.searchRepo()
            }) {
                Text("Search GitHub Repo")
            }
        } else {
            Text(repos)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainView(
            viewModel = MainViewModel.create(
                tryKmp = TryKmp(),
                viewModelStore = LocalViewModelStoreOwner.current!!.viewModelStore,
            )
        )
    }
}

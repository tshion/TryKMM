package io.github.tshion.devmenus

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * [DevMenuSpec] を管理するViewModel
 */
internal class DevMenuSpecViewModel(
    private val specs: List<DevMenuSpec>,
) : ViewModel() {

    /**
     * 表示データの取得
     */
    fun load(history: String): List<DevMenuSpec> {
        if (history.isBlank()) {
            return specs
        }

        val indexes = history.split(",").map { it.trim().toInt() }
        var candidate = specs
        indexes.forEach {
            val group = candidate[it] as DevMenuSpec.Group
            candidate = group.children
        }
        return candidate
    }

    /**
     * 履歴情報の更新
     */
    fun updateHistory(
        currentHistory: String,
        index: Int,
        spec: DevMenuSpec,
    ) = if (spec is DevMenuSpec.Group) {
        listOf(currentHistory, "$index")
            .filter { it.isNotBlank() }
            .joinToString()
    } else {
        currentHistory
    }.trim()


    internal companion object {

        private val KEY_SPECS = CreationExtras.Key<List<DevMenuSpec>>()

        private val Factory = viewModelFactory {
            initializer {
                val specs = this[KEY_SPECS] as List<DevMenuSpec>
                DevMenuSpecViewModel(specs)
            }
        }


        @Composable
        fun create(
            specs: List<DevMenuSpec>?,
        ): DevMenuSpecViewModel = viewModel(
            factory = Factory,
            extras = MutableCreationExtras().apply {
                set(KEY_SPECS, specs ?: emptyList())
            },
        )
    }
}

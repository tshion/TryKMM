package io.github.tshion.trykmp.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.tshion.trykmp.TryKmp
import io.github.tshion.trykmp.timer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val model: TryKmp,
) : ViewModel() {

    private val _repos = MutableStateFlow("")
    val repos = _repos.asStateFlow()

    private val _timeText = MutableStateFlow("")
    val timeText = _timeText.asStateFlow()


    init {
        viewModelScope.launch {
            model.timer().collect {
                _timeText.value = it
            }
        }
    }


    fun searchRepo() {
        _repos.value = "LOADING..."

        val query = "android"
        viewModelScope.launch {
            val result = model.searchGitHubRepo(query)
            _repos.value = result
        }
    }


    companion object {
        private val KEY_TRYKMP = object : CreationExtras.Key<TryKmp> {}

        private val Factory = viewModelFactory {
            initializer {
                val tryKmp = this[KEY_TRYKMP] as TryKmp
                MainViewModel(tryKmp)
            }
        }


        fun create(
            tryKmp: TryKmp,
            viewModelStoreOwner: ViewModelStoreOwner,
        ) = ViewModelProvider.create(
            viewModelStoreOwner,
            factory = Factory,
            extras = MutableCreationExtras().apply {
                set(KEY_TRYKMP, tryKmp)
            }
        )[MainViewModel::class]
    }
}

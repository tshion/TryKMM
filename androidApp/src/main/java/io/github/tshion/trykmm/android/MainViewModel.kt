package io.github.tshion.trykmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.tshion.trykmmlib.Greeting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _greetingList = MutableStateFlow(emptyList<String>())
    val greetingList = _greetingList.asStateFlow()

    init {
        val model = Greeting()
        viewModelScope.launch {
            model.greetStream().collect { text ->
                _greetingList.update { it + text }
            }
        }
    }
}

package com.projects.statisticshelper.ui.screens.inputvalors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.statisticshelper.model.CalculatorType
import com.projects.statisticshelper.model.getKeyboardType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputValorsViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(InputValorsViewState())
    val viewState: StateFlow<InputValorsViewState> = _viewState

    fun updateCalculatorType(calculatorType: CalculatorType){
        viewModelScope.launch {
            if(calculatorType != CalculatorType.UNKNOWN){
                _viewState.value = viewState.value.copy(
                    keyboardOptions = calculatorType.getKeyboardType(),
                    calculatorType = calculatorType
                )
            }else {
                _viewState.value = viewState.value.copy(
                    isError = true
                )
            }
        }
    }

    fun updateActualText(text: String?) {
        viewModelScope.launch {
            text?.let {
                _viewState.value = viewState.value.copy(
                    isClearTextVisible = it.isNotBlank() && it.isNotEmpty()
                )
                return@launch
            }
            _viewState.value = viewState.value.copy(
                isClearTextVisible = false
            )
        }
    }

}
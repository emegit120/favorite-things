package br.com.threeX.favoritethings.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.usecases.GetMinAppVersionUseCase
import kotlinx.coroutines.launch

class BaseViewModel(
    private val getMinAppVersionUseCase: GetMinAppVersionUseCase
) : ViewModel() {


    var minVersionAppState = MutableLiveData<RequestState<Int>>()

    fun getMinVersion() {
        viewModelScope.launch {
            minVersionAppState.value = getMinAppVersionUseCase.getMinVersionApp()
        }
    }
}

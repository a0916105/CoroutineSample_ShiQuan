package tw.idv.jew.coroutinesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            //Call suspend function here
        }
    }
}
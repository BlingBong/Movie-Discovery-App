package com.qifnav.moviediscovery.presentation.util

import com.qifnav.moviediscovery.util.UIText

sealed class UIEvent {
    data class ShowSnackbar(val uiText: UIText) : UIEvent()
    object HideKeyboard : UIEvent()
    object Finish: UIEvent()
}
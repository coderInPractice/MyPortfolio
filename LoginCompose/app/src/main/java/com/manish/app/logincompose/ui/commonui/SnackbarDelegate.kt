package com.manish.app.logincompose.ui.commonui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class SnackbarState {
    DEFAULT, ERROR
}

class SnackbarDelegate(
    var snackbarHostState: SnackbarHostState? = null,
    var coroutineScope: CoroutineScope? = null
) {

    private var snackbarState: SnackbarState = SnackbarState.DEFAULT

    val snackbarBackgroundColor: Color
        @Composable
        get() = when (snackbarState) {
            SnackbarState.DEFAULT -> SnackbarDefaults.color
            SnackbarState.ERROR -> MaterialTheme.colorScheme.error
        }

    var snackbarTestTag: String = ""
        private set

    fun showSnackbar(
        state: SnackbarState,
        message: String,
        testTag: String? = null,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        testTag?.let { snackbarTestTag = it }
        this.snackbarState = state
        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(message, actionLabel, withDismissAction, duration)
        }
    }
}
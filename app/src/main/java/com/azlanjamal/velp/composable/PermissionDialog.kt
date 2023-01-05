package com.azlanjamal.velp.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.azlanjamal.common.R

@Composable
fun PermissionDialog(
    openDialog: MutableState<Boolean> = mutableStateOf(false),
    message: String
) {
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = stringResource(id = R.string.location_permission_title))
        },
        text = {
            Text(text = message)
        },
        confirmButton = {},
        dismissButton = {}
    )
}
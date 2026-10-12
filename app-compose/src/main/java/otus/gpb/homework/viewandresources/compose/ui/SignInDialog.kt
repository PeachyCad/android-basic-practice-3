package otus.gpb.homework.viewandresources.compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import otus.gpb.homework.viewandresources.compose.R
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

/**
 * Диалог входа. BasicAlertDialog вместо AlertDialog со слотами,
 * потому что по макету кнопок три и они стоят не в стандартных местах.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInDialog(onDismiss: () -> Unit) {
    BasicAlertDialog(onDismissRequest = onDismiss) {
        // Фон surfaceContainerHigh и скругление 28dp, как у MaterialAlertDialog
        Surface(
            shape = AlertDialogDefaults.shape,
            color = AlertDialogDefaults.containerColor,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            SignInContent(onDismiss)
        }
    }
}

@Composable
private fun SignInContent(onDismiss: () -> Unit) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Скролл нужен для ландшафта: с клавиатурой диалог по высоте не помещается
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 22.dp)
    ) {
        Text(
            text = stringResource(R.string.login_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        TextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.login_hint)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.login_password)) },
            trailingIcon = { Icon(painterResource(R.drawable.ic_light_mode), contentDescription = null) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        TextButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
        ) {
            Text(stringResource(R.string.login_reset_password))
        }

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, end = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.login_submit))
            }
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.login_cancel))
            }
        }
    }
}

@Preview
@Composable
private fun SignInDialogPreview() {
    ViewResourcesTheme {
        Surface(color = AlertDialogDefaults.containerColor) {
            SignInContent(onDismiss = {})
        }
    }
}

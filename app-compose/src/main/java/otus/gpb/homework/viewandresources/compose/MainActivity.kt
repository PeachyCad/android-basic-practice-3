package otus.gpb.homework.viewandresources.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import otus.gpb.homework.viewandresources.compose.ui.SignInDialog
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewResourcesTheme {
                MainScreen(
                    onOpenContacts = { startActivity(Intent(this, ContactsActivity::class.java)) },
                    onOpenCart = { startActivity(Intent(this, CartActivity::class.java)) }
                )
            }
        }
    }
}

@Composable
private fun MainScreen(onOpenContacts: () -> Unit, onOpenCart: () -> Unit) {
    // rememberSaveable: диалог остаётся открытым после поворота экрана,
    // как DialogFragment в View-версии
    var showSignIn by rememberSaveable { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onOpenContacts) {
                Text(stringResource(R.string.main_open_contacts))
            }
            Button(onClick = onOpenCart) {
                Text(stringResource(R.string.main_open_cart))
            }
            Button(onClick = { showSignIn = true }) {
                Text(stringResource(R.string.main_show_sign_in))
            }
        }
    }

    if (showSignIn) {
        SignInDialog(onDismiss = { showSignIn = false })
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ViewResourcesTheme {
        MainScreen(onOpenContacts = {}, onOpenCart = {})
    }
}

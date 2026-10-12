package otus.gpb.homework.viewandresources.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import otus.gpb.homework.viewandresources.compose.ui.ContactsScreen
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

class ContactsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewResourcesTheme {
                ContactsScreen(onBack = ::finish)
            }
        }
    }
}

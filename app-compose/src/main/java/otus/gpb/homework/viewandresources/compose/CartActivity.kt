package otus.gpb.homework.viewandresources.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import otus.gpb.homework.viewandresources.compose.ui.CartScreen
import otus.gpb.homework.viewandresources.compose.ui.CartTotals
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val items = (1..4).map {
            CartItem(
                title = "List item $it",
                category = getString(R.string.cart_item_category),
                description = getString(R.string.cart_item_description),
                price = "$35"
            )
        }
        val totals = CartTotals(
            total = "$175.50",
            subtotal = "$140",
            shipping = "$25",
            tax = "$10.50"
        )

        setContent {
            ViewResourcesTheme {
                CartScreen(items = items, totals = totals, onClose = ::finish)
            }
        }
    }
}

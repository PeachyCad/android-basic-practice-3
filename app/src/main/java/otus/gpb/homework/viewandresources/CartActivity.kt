package otus.gpb.homework.viewandresources

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
            setOnMenuItemClickListener {
                finish()
                true
            }
        }

        val items = (1..4).map {
            CartItem(
                title = "List item $it",
                category = getString(R.string.cart_item_category),
                description = getString(R.string.cart_item_description),
                price = "$35"
            )
        }
        findViewById<RecyclerView>(R.id.cart_list).adapter = CartAdapter(items)
        findViewById<TextView>(R.id.items_count).text =
            resources.getQuantityString(R.plurals.cart_items_count, items.size, items.size)

        findViewById<TextView>(R.id.total).text = "$175.50"
        findViewById<TextView>(R.id.subtotal).text = "$140"
        findViewById<TextView>(R.id.shipping).text = "$25"
        findViewById<TextView>(R.id.tax).text = "$10.50"
    }
}

package otus.gpb.homework.viewandresources.compose.ui

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import otus.gpb.homework.viewandresources.compose.CartItem
import otus.gpb.homework.viewandresources.compose.R
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

data class CartTotals(
    val total: String,
    val subtotal: String,
    val shipping: String,
    val tax: String
)

@Composable
fun CartScreen(items: List<CartItem>, totals: CartTotals, onClose: () -> Unit) {
    // Вместо отдельного layout-land/activity_cart.xml — ветка по ориентации.
    // Configuration меняется при повороте, и Compose сам перерисует экран
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(topBar = { CartTopBar(onClose) }) { innerPadding ->
        if (isLandscape) {
            // Ландшафт: высоты мало, поэтому список слева, итоги справа
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CartList(items, Modifier.weight(3f).fillMaxHeight())
                CartSummary(
                    totals = totals,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                )
            }
        } else {
            // Портрет: список занимает всё свободное место, итоги прижаты к низу
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                CartList(items, Modifier.weight(1f))
                CartSummary(totals = totals)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartTopBar(onClose: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.cart_title)) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(painterResource(R.drawable.ic_arrow_back), stringResource(R.string.action_back))
            }
        },
        actions = {
            IconButton(onClick = onClose) {
                Icon(painterResource(R.drawable.ic_highlight_off), stringResource(R.string.cart_close))
            }
        }
    )
}

@Composable
private fun CartList(items: List<CartItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = pluralStringResource(R.plurals.cart_items_count, items.size, items.size),
            modifier = Modifier.padding(dimensionResource(R.dimen.screen_padding)),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        // LazyColumn создаёт только видимые строки — аналог RecyclerView без адаптера
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { item -> CartItemRow(item) }
        }
    }
}

/** Один товар: картинка, текст, цена и разделитель снизу. */
@Composable
private fun CartItemRow(item: CartItem) {
    Column {
        Row(modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 24.dp, bottom = 12.dp)) {
            Image(
                painter = painterResource(R.drawable.product_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.cart_image_size))
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = item.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = item.category,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = item.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = item.price,
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.list_padding)))
    }
}

/** Итоги заказа и кнопка оформления — аналог view_cart_summary.xml. */
@Composable
private fun CartSummary(totals: CartTotals, modifier: Modifier = Modifier) {
    val listPadding = dimensionResource(R.dimen.list_padding)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(listPadding)
        ) {
            Text(
                text = stringResource(R.string.cart_order_total),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = totals.total,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        SummaryRow(R.string.cart_subtotal, totals.subtotal)
        SummaryRow(R.string.cart_shipping, totals.shipping)
        SummaryRow(R.string.cart_tax, totals.tax)

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(R.dimen.screen_padding))
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_sentiment_satisfied),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.cart_place_order))
        }
    }
}

/** Строка итогов: подпись слева, сумма справа — вместо стилей CartSummaryRow/Label/Value. */
@Composable
private fun SummaryRow(@StringRes label: Int, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.list_padding), vertical = 4.dp)
    ) {
        Text(
            text = stringResource(label),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

private val previewItems = (1..4).map {
    CartItem("List item $it", "Category", "Supporting line text lorem ipsum dolor sit amet, consectetur.", "$35")
}
private val previewTotals = CartTotals("$175.50", "$140", "$25", "$10.50")

@Preview
@Composable
private fun CartScreenPreview() {
    ViewResourcesTheme {
        CartScreen(previewItems, previewTotals, onClose = {})
    }
}

@Preview(device = "spec:width=800dp,height=360dp,orientation=landscape")
@Composable
private fun CartScreenLandscapePreview() {
    ViewResourcesTheme {
        CartScreen(previewItems, previewTotals, onClose = {})
    }
}

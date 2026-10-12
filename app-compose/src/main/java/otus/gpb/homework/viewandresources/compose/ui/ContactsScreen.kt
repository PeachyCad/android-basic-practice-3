package otus.gpb.homework.viewandresources.compose.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import otus.gpb.homework.viewandresources.compose.R
import otus.gpb.homework.viewandresources.compose.ui.theme.ViewResourcesTheme

/**
 * Тулбар сверху, форма в скролле, кнопка Save всегда видна внизу.
 * В ландшафте форма не помещается по высоте — её прокручивает verticalScroll.
 */
@Composable
fun ContactsScreen(onBack: () -> Unit) {
    val screenPadding = dimensionResource(R.dimen.screen_padding)
    val fieldSpacing = dimensionResource(R.dimen.field_spacing)

    Scaffold(
        topBar = { ContactsTopBar(onBack) },
        bottomBar = {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(screenPadding)
            ) {
                Text(stringResource(R.string.contact_save))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = screenPadding, vertical = fieldSpacing),
            verticalArrangement = Arrangement.spacedBy(fieldSpacing)
        ) {
            ContactField(
                label = R.string.contact_name,
                initialValue = R.string.contact_name_value,
                icon = R.drawable.ic_person_outline,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(fieldSpacing)) {
                ContactField(
                    label = R.string.contact_phone,
                    initialValue = R.string.contact_phone_value,
                    icon = R.drawable.ic_mobile_friendly,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.weight(4f)
                )
                PhoneTypeField(modifier = Modifier.weight(3f))
            }

            ContactField(
                label = R.string.contact_address,
                initialValue = R.string.contact_address_value,
                icon = R.drawable.ic_bookmark_border,
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(fieldSpacing)) {
                ContactField(
                    label = R.string.contact_city,
                    initialValue = R.string.contact_city_value,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    modifier = Modifier.weight(1f)
                )
                ContactField(
                    label = R.string.contact_country,
                    initialValue = R.string.contact_country_value,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    modifier = Modifier.weight(1f)
                )
            }

            ContactField(
                label = R.string.contact_birthday,
                initialValue = R.string.contact_birthday_value,
                icon = R.drawable.ic_event,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            ContactField(
                label = R.string.contact_notes,
                initialValue = R.string.contact_notes_value,
                icon = R.drawable.ic_mic,
                singleLine = false,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactsTopBar(onBack: () -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(R.string.contact_name_value)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(painterResource(R.drawable.ic_arrow_back), stringResource(R.string.action_back))
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.ic_attach_file), stringResource(R.string.contact_attach))
            }
            // Аналог пункта меню с showAsAction="never": «три точки» и выпадающее меню
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, stringResource(R.string.action_more))
                }
                DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.contact_delete)) },
                        onClick = { menuExpanded = false }
                    )
                }
            }
        }
    )
}

/** Filled-поле формы — аналог TextInputLayout с endIconMode="custom". */
@Composable
private fun ContactField(
    @StringRes label: Int,
    @StringRes initialValue: Int,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val initial = stringResource(initialValue)
    var value by rememberSaveable { mutableStateOf(initial) }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text(stringResource(label)) },
        trailingIcon = icon?.let { { Icon(painterResource(it), contentDescription = null) } },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

/** Выпадающий список типа телефона — аналог ExposedDropdownMenu из Material Components. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneTypeField(modifier: Modifier = Modifier) {
    val phoneTypes = stringArrayResource(R.array.phone_types)
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            value = phoneTypes[selectedIndex],
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(stringResource(R.string.contact_phone_type)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            phoneTypes.forEachIndexed { index, type ->
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContactsScreenPreview() {
    ViewResourcesTheme {
        ContactsScreen(onBack = {})
    }
}

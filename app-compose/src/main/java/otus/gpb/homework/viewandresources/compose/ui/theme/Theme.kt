package otus.gpb.homework.viewandresources.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Аналог Base.Theme.ViewResources из values/themes.xml
private val LightColors = lightColorScheme(
    primary = OtusLightPrimary,
    onPrimary = OtusLightOnPrimary,
    primaryContainer = OtusLightPrimaryContainer,
    onPrimaryContainer = OtusLightOnPrimaryContainer,
    secondary = OtusLightSecondary,
    onSecondary = OtusLightOnSecondary,
    secondaryContainer = OtusLightSecondaryContainer,
    onSecondaryContainer = OtusLightOnSecondaryContainer,
    tertiary = OtusLightTertiary,
    onTertiary = OtusLightOnTertiary,
    tertiaryContainer = OtusLightTertiaryContainer,
    onTertiaryContainer = OtusLightOnTertiaryContainer,
    background = OtusLightBackground,
    onBackground = OtusLightOnBackground,
    surface = OtusLightSurface,
    onSurface = OtusLightOnSurface,
    surfaceVariant = OtusLightSurfaceVariant,
    onSurfaceVariant = OtusLightOnSurfaceVariant,
    outline = OtusLightOutline,
    outlineVariant = OtusLightOutlineVariant,
    surfaceContainerLowest = OtusLightSurfaceContainerLowest,
    surfaceContainerLow = OtusLightSurfaceContainerLow,
    surfaceContainer = OtusLightSurfaceContainer,
    surfaceContainerHigh = OtusLightSurfaceContainerHigh,
    surfaceContainerHighest = OtusLightSurfaceContainerHighest,
)

// Аналог values-night/themes.xml
private val DarkColors = darkColorScheme(
    primary = OtusDarkPrimary,
    onPrimary = OtusDarkOnPrimary,
    primaryContainer = OtusDarkPrimaryContainer,
    onPrimaryContainer = OtusDarkOnPrimaryContainer,
    secondary = OtusDarkSecondary,
    onSecondary = OtusDarkOnSecondary,
    secondaryContainer = OtusDarkSecondaryContainer,
    onSecondaryContainer = OtusDarkOnSecondaryContainer,
    tertiary = OtusDarkTertiary,
    onTertiary = OtusDarkOnTertiary,
    tertiaryContainer = OtusDarkTertiaryContainer,
    onTertiaryContainer = OtusDarkOnTertiaryContainer,
    background = OtusDarkBackground,
    onBackground = OtusDarkOnBackground,
    surface = OtusDarkSurface,
    onSurface = OtusDarkOnSurface,
    surfaceVariant = OtusDarkSurfaceVariant,
    onSurfaceVariant = OtusDarkOnSurfaceVariant,
    outline = OtusDarkOutline,
    outlineVariant = OtusDarkOutlineVariant,
    surfaceContainerLowest = OtusDarkSurfaceContainerLowest,
    surfaceContainerLow = OtusDarkSurfaceContainerLow,
    surfaceContainer = OtusDarkSurfaceContainer,
    surfaceContainerHigh = OtusDarkSurfaceContainerHigh,
    surfaceContainerHighest = OtusDarkSurfaceContainerHighest,
)

/**
 * Вместо выбора папки values / values-night системой тема сама решает,
 * какую палитру отдать, а экраны читают цвета через MaterialTheme.colorScheme.
 */
@Composable
fun ViewResourcesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}

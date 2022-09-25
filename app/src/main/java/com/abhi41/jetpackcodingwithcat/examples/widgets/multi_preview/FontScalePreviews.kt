package com.abhi41.jetpackcodingwithcat.examples.widgets.multi_preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Target(AnnotationTarget.EXPRESSION, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Preview(
    name = "Small Font",
    group = "Small Font Group",
    fontScale = 0.5f,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Small Font",
    group = "Small Font Group",
    fontScale = 0.5f,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Normal Font",
    group = "Normal Font Group",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Normal Font",
    group = "Normal Font Group",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Large Font",
    group = "Large Font Group",
    fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Large Font",
    group = "Large Font Group",
    fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
annotation class FontScapePreviews



@Preview(name = "Pixel XL", group = "Devices", device = Devices.PIXEL_XL, showSystemUi = true)
@Preview(name = "Pixel 2", group = "Devices", device = Devices.PIXEL_2, showSystemUi = true)
@Preview(name = "Pixel 3", group = "Devices", device = Devices.PIXEL_3, showSystemUi = true)
@Preview(name = "Pixel 4", group = "Devices", device = Devices.PIXEL_4, showSystemUi = true)
@Preview(name = "Pixel C", group = "Devices", device = Devices.PIXEL_C, showSystemUi = true)
annotation class DevicePreviews
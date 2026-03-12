package com.shouvick.productbrowserapprevest.commonWidget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shouvick.productbrowserapprevest.feature.product.data.models.Category

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomInputFieldWithDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<Category>, // Dropdown options
    isDropDownEnable: Boolean = true,
    placeHolder: @Composable () -> Unit = {},
    singleLine: Boolean = false,
    maxChar: Int = 10000,
    charFilter: (Char) -> Boolean = { true },
    regexFilter: Regex? = null,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
        ),
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester = FocusRequester(),
    isReadOnly: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    val localFocusRequester = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (isDropDownEnable) expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier =
                Modifier
                    .menuAnchor()
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
            value = value,
            onValueChange = { newValue ->
                val filtered = newValue.filter(charFilter)
                if (filtered.length <= maxChar) {
                    if (regexFilter == null || regexFilter.matches(filtered)) {
                        onValueChange(filtered)
                    }
                }
            },
            placeholder = placeHolder,
            singleLine = singleLine,
            enabled = true,
            readOnly = isReadOnly,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions =
                keyboardActions ?: KeyboardActions(onDone = {
                    localFocusRequester.clearFocus()
                }),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
        )

        // Dropdown menu
        ExposedDropdownMenu(
            expanded = expanded,
            containerColor = Color.White,
            onDismissRequest = { expanded = false },
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option.name ?:"") },
                    onClick = {
                        onValueChange(option.slug ?: "")
                        expanded = false
                    },
                )
                if (index < options.size - 1) {
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun CustomInputFieldWithDropdownPreview() {
//    var text by remember { mutableStateOf("") }
//
//    CustomInputFieldWithDropdown(
//        value = text,
//        onValueChange = { text = it },
//        options =
//            listOf(
//                "Option One",
//                "Option Two",
//                "Option Three",
//            ),
//        placeHolder = {
//            Text(text = "Select option")
//        },
//    )
//}
//
//@Preview
//@Composable
//fun CustomInputFieldWithDropdownSelectedPreview() {
//    var text by remember { mutableStateOf("Option Two") }
//
//    CustomInputFieldWithDropdown(
//        value = text,
//        onValueChange = { text = it },
//        options =
//            listOf(
//                "Option One",
//                "Option Two",
//                "Option Three",
//            ),
//        placeHolder = {
//            Text(text = "Select option")
//        },
//    )
//}

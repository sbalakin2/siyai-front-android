package com.example.siyai_front_android.ui.components.text_fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any?> DropDownTextField(
    value: T,
    onValueChange: (item: T) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    itemLabel: (item: T) -> String = { it.toString() },
    items: List<T>,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    fullScreen: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var isFieldExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isFieldExpanded,
        onExpandedChange = { isExpanded -> isFieldExpanded = isExpanded },
        modifier = modifier
    ) {

        BaseTextField(
            value = itemLabel(value),
            onValueChange = {},
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .height(64.dp),
            enabled = enabled,
            readOnly = true,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            isError = isError,
        )

        DropdownMenu(
            expanded = isFieldExpanded,
            onDismissRequest = { isFieldExpanded = false },
            modifier = Modifier
                .run { if (fullScreen) fillMaxHeight() else this }
                .exposedDropdownSize(matchTextFieldWidth = true)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = itemLabel(item),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    onClick = {
                        onValueChange(item)
                        isFieldExpanded = false
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DropDownFieldPreview() {
    var text by remember { mutableStateOf("") }

    SiyaifrontandroidTheme {
        DropDownTextField(
            modifier = Modifier.padding(16.dp),
            value = text,
            onValueChange = { text = it },
            label = "text",
            items = List(10) { "Item $it" }
        )
    }
}



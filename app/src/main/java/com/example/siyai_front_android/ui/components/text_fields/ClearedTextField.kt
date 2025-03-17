package com.example.siyai_front_android.ui.components.text_fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun ClearedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange.invoke("") }) {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        imageVector = SiyAiIcons.Close,
                        contentDescription = stringResource(R.string.clear)
                    )
                }
            }
        },
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource
    )
}

@Preview(showBackground = true)
@Composable
private fun ClearedTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    SiyaifrontandroidTheme {
        ClearedTextField(
            modifier = Modifier.padding(16.dp),
            value = text,
            onValueChange = { text = it },
            label = "Text"
        )
    }
}
package com.example.siyai_front_android.ui.components.text_fields

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    label: String
) {
    var passwordVisible by remember { mutableStateOf(false) }

    BaseTextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
        label = label,
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val icon = if (passwordVisible) {
                SiyAiIcons.EyeShow
            } else {
                SiyAiIcons.EyeHide
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    modifier = Modifier.padding(12.dp),
                    imageVector = icon,
                    contentDescription = if (passwordVisible) stringResource(R.string.hide_password)
                    else stringResource(R.string.show_password)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("") }

    SiyaifrontandroidTheme {
        PasswordTextField(
            modifier = Modifier.padding(16.dp),
            password = password,
            onPasswordChange = { password = it },
            label = "Пароль"
        )
    }
}
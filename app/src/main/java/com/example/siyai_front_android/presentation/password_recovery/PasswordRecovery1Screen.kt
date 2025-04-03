package com.example.siyai_front_android.presentation.password_recovery

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.SecondaryLoadingButton
import com.example.siyai_front_android.ui.components.text_fields.ClearedTextField
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.utils.validateUserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecovery1Screen(
    onBackClick: () -> Unit,
    onRecoveryClick: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: RecoveryPasswordViewModel = viewModel(factory = viewModelFactory)
    val recoveryPasswordState by viewModel.recoveryPasswordState.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = SiyAiIcons.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.recovery_password1_subtitle),
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 3,
                minLines = 3
            )
            Spacer(modifier = Modifier.height(20.dp))
            ClearedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                value = email,
                onValueChange = { value ->
                    email = value
                },
                label = stringResource(R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                SecondaryLoadingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp),
                    text = stringResource(R.string.recovery),
                    onClick = {
                        if (email.isNotEmpty()) {
                            val errorMessage = validateUserData(email = email, context = context)
                            if (errorMessage == null) {
                                viewModel.recoveryPassword(email)
                            } else {
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    isLoading = isLoading
                )
            }
            LaunchedEffect(recoveryPasswordState) {
                when (recoveryPasswordState) {
                    is RecoveryPasswordState.Success -> {
                        isLoading = false
                        onRecoveryClick.invoke()
                        viewModel.resetState()
                    }

                    is RecoveryPasswordState.Error -> {
                        isLoading = false
                        val errorMessage =
                            if ((recoveryPasswordState as RecoveryPasswordState.Error).code in 500..599) {
                                context.getString(R.string.server_error)
                            } else {
                                (recoveryPasswordState as RecoveryPasswordState.Error).message
                            }
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        viewModel.resetState()
                    }

                    is RecoveryPasswordState.Exception -> {
                        isLoading = false
                        Toast.makeText(
                            context,
                            (recoveryPasswordState as RecoveryPasswordState.Exception).message,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.resetState()
                    }
                    is RecoveryPasswordState.Loading -> isLoading = true
                    is RecoveryPasswordState.Empty -> isLoading = false
                }
            }
        }
    }
}

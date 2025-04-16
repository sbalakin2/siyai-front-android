package com.example.siyai_front_android.presentation.email_confirmation

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.utils.EMAIL_CONFIRMATION_CODE_SIZE
import com.example.siyai_front_android.utils.INPUT_CODE_MAX_COUNT
import com.example.siyai_front_android.utils.createToast

@SuppressLint("MutableCollectionMutableState")
@Composable
fun EmailConfirmationScreen(
    email: String,
    password: String,
    expDate: Int,
    otp: Int,
    onEmailConfirmationClick: () -> Unit,
    onResendingCodeClick: (Int) -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: EmailConfirmationViewModel = viewModel(factory = viewModelFactory)
    val emailConfirmationState by viewModel.emailConfirmationState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val focusRequesters = List(EMAIL_CONFIRMATION_CODE_SIZE) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var receivedCode by rememberSaveable(otp) { mutableIntStateOf(0) }
    val enteredCode = remember { mutableStateListOf("", "", "", "", "", "") }
    var inputCodeCount by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(expDate) {
        if (expDate == 0) {
            createToast(context, context.getString(R.string.code_lifetime_has_expired))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = SiyAiIcons.CheckMark,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.2f),
                tint = Color.White
            )

            Text(
                text = stringResource(R.string.confirmation_code_has_been_sent),
                modifier = Modifier.padding(top = 24.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )

            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(EMAIL_CONFIRMATION_CODE_SIZE) { index ->
                    var isFocused by remember { mutableStateOf(false) }
                    val height by animateDpAsState(targetValue = if (isFocused) 68.dp else 64.dp)

                    TextField(
                        value = enteredCode[index],
                        onValueChange = { newValue ->
                            if (newValue.isDigitsOnly()) {
                                enteredCode[index] = newValue.lastOrNull()?.toString().orEmpty()
                                if (enteredCode[index].isBlank() && index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                } else if (index < EMAIL_CONFIRMATION_CODE_SIZE - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }

                            if (
                                index == EMAIL_CONFIRMATION_CODE_SIZE - 1 &&
                                isEnteredCodeComplete(enteredCode)
                            ) {
                                keyboardController?.hide()
                            }
                        },
                        modifier = Modifier
                            .size(width = 48.dp, height = height)
                            .onKeyEvent { event ->
                                if (event.type == KeyEventType.KeyUp
                                    && event.key == Key.Backspace
                                    && enteredCode[index].isEmpty()
                                    && index > 0
                                ) {
                                    focusRequesters[index - 1].requestFocus()
                                    enteredCode[index - 1] = ""
                                    true
                                } else {
                                    false
                                }
                            }
                            .focusRequester(focusRequesters[index])
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.surface
                        ),
                        placeholder = {
                            if (!isFocused && enteredCode[index].isBlank()) {
                                Text(
                                    text = "__",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                            autoCorrectEnabled = false
                        ),
                        singleLine = true,
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.primary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }

            Text(
                text = stringResource(R.string.please_check_your_email),
                modifier = Modifier.padding(top = 24.dp, bottom = 134.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = stringResource(R.string.confirm_registration),
                onClick = {
                    if (inputCodeCount < INPUT_CODE_MAX_COUNT) {
                        if (compareCode(receivedCode, enteredCode)) {
                            viewModel.registerUser(email, password)
                        } else {
                            inputCodeCount++
                            resetEnteredCode(enteredCode)
                            focusRequesters[0].requestFocus()

                            if (inputCodeCount < INPUT_CODE_MAX_COUNT) {
                                createToast(
                                    context,
                                    context.getString(R.string.Incorrect_confirmation_code)
                                )
                            } else {
                                createToast(
                                    context,
                                    context.getString(R.string.number_of_input_attempts_is_exhausted)
                                )
                            }
                        }
                    } else {
                        createToast(
                            context,
                            context.getString(R.string.number_of_input_attempts_is_exhausted)
                        )
                    }
                },
                enabled = isEnteredCodeComplete(enteredCode)
            )

            Text(
                text = stringResource(R.string.did_not_get_code),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(
                        onClick = {
                            viewModel.verify(email)
                            inputCodeCount = 0
                            resetEnteredCode(enteredCode)
                            focusRequesters[0].requestFocus()
                        }
                    ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    LaunchedEffect(emailConfirmationState) {
        getEmailConfirmationState(
            emailConfirmationState = emailConfirmationState,
            context = context,
            onEmailConfirmationClick = onEmailConfirmationClick,
            onResendingCodeClick = onResendingCodeClick,
            clearEmailConfirmationState = viewModel::clearEmailConfirmationState,
            setReceivedCode = { receivedCode = it }
        )
    }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
}

fun isEnteredCodeComplete(enteredCode: MutableList<String>): Boolean {
    return enteredCode.all { it.isNotEmpty() }
}

fun compareCode(receivedCode: Int, enteredCode: SnapshotStateList<String>): Boolean {
    val enteredOtp = enteredCode.joinToString("").toIntOrNull()
    return enteredOtp != null && enteredOtp == receivedCode
}

fun resetEnteredCode(enteredCode: SnapshotStateList<String>) {
    for (i in enteredCode.indices) {
        enteredCode[i] = ""
    }
}

fun pasteFromClipboard(context: Context, code: MutableList<String>) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipboardText = clipboardManager.primaryClip?.getItemAt(0)?.text.toString()
    if (clipboardText.isDigitsOnly()) {
        clipboardText.forEachIndexed { index, char ->
            if (index < EMAIL_CONFIRMATION_CODE_SIZE) {
                code[index] = char.toString()
            }
        }
    } else {
        createToast(context, context.getString(R.string.incorrect_code_format))
    }
}

private fun getEmailConfirmationState(
    emailConfirmationState: EmailConfirmationState,
    context: Context,
    onEmailConfirmationClick: () -> Unit,
    onResendingCodeClick: (Int) -> Unit,
    clearEmailConfirmationState: () ->  Unit,
    setReceivedCode: (Int) -> Unit
) {
    when (emailConfirmationState) {
        is EmailConfirmationState.RegSuccess -> {
            onEmailConfirmationClick()
            clearEmailConfirmationState()
        }
        is EmailConfirmationState.VerificationSuccess -> {
            setReceivedCode(emailConfirmationState.otp)
            onResendingCodeClick(emailConfirmationState.otp)
            clearEmailConfirmationState()
        }
        is EmailConfirmationState.Error -> {
            val errorMessage = when (emailConfirmationState.code) {
                in 500..599 -> context.getString(R.string.server_error)
                else -> emailConfirmationState.message
            }
            createToast(context, errorMessage)
            clearEmailConfirmationState()
        }
        is EmailConfirmationState.Exception -> {
            createToast(context, emailConfirmationState.message)
            clearEmailConfirmationState()
        }
        EmailConfirmationState.Idle -> {
        }
    }
}

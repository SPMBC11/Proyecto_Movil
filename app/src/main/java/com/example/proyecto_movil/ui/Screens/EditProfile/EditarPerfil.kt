package com.example.proyecto_movil.ui.Screens.EditProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.ui.utils.BotonEditarImagen
import com.example.proyecto_movil.ui.utils.BotonGuardar
import com.example.proyecto_movil.ui.utils.ScreenBackground

@Composable
fun EditarPerfilScreen(
    viewModel: EditProfileViewModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { picked -> viewModel.uploadAvatarFromPicker(picked) }
    }

    LaunchedEffect(state.navigateBack, state.navigateSaved) {
        if (state.navigateBack) { onBack(); viewModel.consumeBack() }
        if (state.navigateSaved) { onSaved(); viewModel.consumeSaved() }
    }

    val isDark = isSystemInDarkTheme()
    val backgroundRes = if (isDark) R.drawable.fondocriti else R.drawable.fondocriti_light

    ScreenBackground(backgroundRes = backgroundRes, modifier = modifier) {
        Scaffold(
            bottomBar = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(16.dp)
                ) {
                    BotonGuardar(
                        text = if (state.isUploading) "Subiendo..." else "Guardar",
                        onClick = { if (!state.isUploading) viewModel.onSaveClicked() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                    )
                }
            }
        ) { pv ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pv)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(
                    start = 16.dp, end = 16.dp, top = 8.dp, bottom = 120.dp
                )
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { viewModel.onBackClicked() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Editar perfil",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(48.dp))
                    }
                    Spacer(Modifier.height(16.dp))
                }

                item {
                    if (state.avatarUrl.isNotBlank()) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(state.avatarUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp).clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = state.avatarRes),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp).clip(CircleShape)
                        )
                    }

                    if (state.isUploading) {
                        Spacer(Modifier.height(8.dp))
                        CircularProgressIndicator()
                    }

                    Spacer(Modifier.height(8.dp))
                    BotonEditarImagen( // sin parámetro enabled
                        text = if (state.isUploading) "Subiendo..." else "Editar imagen",
                        onClick = {
                            if (!state.isUploading) picker.launch("image/*")
                        },
                        modifier = Modifier.height(36.dp).width(170.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                }

                item {
                    CompactTextField(
                        value = state.avatarUrl,
                        onValueChange = viewModel::updateAvatarUrlManually,
                        label = "Link Imagen Firebase",
                        icon = R.drawable.usuario
                    )
                }
                item {
                    CompactTextField(
                        value = state.nombrePersona,
                        onValueChange = viewModel::updateNombrePersona,
                        label = stringResource(R.string.nombre),
                        icon = R.drawable.usuario
                    )
                }
                item {
                    CompactTextField(
                        value = state.nombreUsuario,
                        onValueChange = viewModel::updateNombreUsuario,
                        label = stringResource(R.string.nombre_usuario),
                        icon = R.drawable.usuario
                    )
                }
                item {
                    CompactTextField(
                        value = state.email,
                        onValueChange = viewModel::updateEmail,
                        label = stringResource(R.string.email),
                        icon = R.drawable.correo
                    )
                }
                item {
                    CompactTextField(
                        value = state.password,
                        onValueChange = viewModel::updatePassword,
                        label = stringResource(R.string.contra),
                        icon = R.drawable.candado,
                        isPassword = true,
                        mostrarPassword = state.mostrarPassword,
                        onTogglePassword = viewModel::toggleMostrarPassword
                    )
                }

                if (state.errorMessage.isNotBlank()) {
                    item {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = state.errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp
                        )
                    }
                }

                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
private fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: Int,
    isPassword: Boolean = false,
    mostrarPassword: Boolean = false,
    onTogglePassword: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 13.sp) },
        trailingIcon = if (isPassword && onTogglePassword != null) {
            {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (mostrarPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        } else null,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
        visualTransformation = if (isPassword && !mostrarPassword) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
}

@Preview(name = "Light Mode", showSystemUi = true)
@Composable
fun EditarPerfilPreviewLight() {
    Proyecto_movilTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { EditProfileViewModel() }
            EditarPerfilScreen(viewModel = vm)
        }
    }
}

@Preview(name = "Dark Mode", showSystemUi = true)
@Composable
fun EditarPerfilPreviewDark() {
    Proyecto_movilTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            val vm = remember { EditProfileViewModel() }
            EditarPerfilScreen(viewModel = vm)
        }
    }
}

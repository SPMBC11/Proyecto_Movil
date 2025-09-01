package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.utils.BotonEditarImagen
import com.example.proyecto_movil.ui.utils.BotonGuardar
import com.example.proyecto_movil.ui.utils.TituloArtista
import com.example.proyecto_movil.utils.ScreenBackground

@Composable
fun EditarPerfil(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    nombrePersona: String = "",
    nombreUsuario: String = "",
    email: String = "",
    password: String = "",
    onNombrePersonaChange: (String) -> Unit = {},
    onNombreUsuarioChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
) {
    ScreenBackground(backgroundRes = R.drawable.fondocriti) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TopBar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
                Spacer(Modifier.weight(1f))
                TituloArtista("Editar perfil")
                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.height(12.dp))

            // Avatar
            Image(
                painter = painterResource(id = R.drawable.xocas),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(8.dp))

            BotonEditarImagen(
                "Editar imagen",
                onClick = { /* acción editar imagen */ },
                modifier = Modifier
                    .height(32.dp)
                    .width(150.dp)
            )

            Spacer(Modifier.height(24.dp))

            // Campos compactos
            CompactTextField(
                value = nombrePersona,
                onValueChange = onNombrePersonaChange,
                label = stringResource(R.string.nombre),
                icon = R.drawable.usuario
            )

            CompactTextField(
                value = nombreUsuario,
                onValueChange = onNombreUsuarioChange,
                label = stringResource(R.string.nombre_usuario),
                icon = R.drawable.usuario
            )

            CompactTextField(
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.email),
                icon = R.drawable.correo
            )

            var mostrarPassword by remember { mutableStateOf(false) }
            CompactTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.contra),
                icon = R.drawable.candado,
                isPassword = true,
                mostrarPassword = mostrarPassword,
                onTogglePassword = { mostrarPassword = !mostrarPassword }
            )

            Spacer(Modifier.height(24.dp))

            BotonGuardar(
                "Guardar",
                onClick = { /* acción guardar */ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CompactTextField(
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
        label = { Text(label, fontSize = 14.sp) },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        trailingIcon = if (isPassword && onTogglePassword != null) {
            {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        painter = painterResource(
                            if (mostrarPassword) R.drawable.view else R.drawable.hide
                        ),
                        contentDescription = "Toggle password",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        } else null,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
        visualTransformation = if (isPassword && !mostrarPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp) // 👈 compactos
            .padding(vertical = 6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilPreview() {
    EditarPerfil()
}

package com.example.proyecto_movil.screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.utils.BotonEditarImagen
import com.example.proyecto_movil.ui.utils.BotonGuardar
import com.example.proyecto_movil.ui.utils.TituloArtista
import com.example.proyecto_movil.ui_components.ScreenBackground

@Composable
fun EditarPerfil(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onBack: () -> Unit = {},
    nombrePersona: String = "",
    nombreUsuario: String = "",
    email: String = "",
    password: String = "",
    onRegister: (String, String, String) -> Unit = { _, _, _ -> },
    onNombrePersonaChange: (String) -> Unit = {},
    onNombreUsuarioChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},

) {
    ScreenBackground(backgroundRes = R.drawable.grisoscuro) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {

                TituloArtista("Editar perfil")


                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = colorResource(id = R.color.white)
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.xocas),
                    contentDescription = "Avatar de el.xokas",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(12.dp))
            BotonEditarImagen(
                "Editar imagen",
                onClick = { /* Acción de editar imagen */ },
                modifier = Modifier.width(150.dp)
                    .height(35.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            )
            Spacer(Modifier.height(24.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                OutlinedTextField(
                    value = nombrePersona,
                    onValueChange = onNombrePersonaChange,
                    label = { Text(stringResource(R.string.nombre)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.usuario),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "Icono de usuario"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorResource(R.color.white),
                        unfocusedTextColor = colorResource(R.color.white),
                        focusedContainerColor = colorResource(R.color.gris),
                        unfocusedContainerColor = colorResource(R.color.gris),
                        focusedBorderColor = colorResource(R.color.azulClaro),
                        unfocusedBorderColor = colorResource(R.color.azulClaro),
                        focusedLabelColor = colorResource(R.color.white),
                        unfocusedLabelColor = colorResource(R.color.white),
                        disabledLabelColor = colorResource(R.color.white),
                        errorLabelColor = colorResource(R.color.white)
                    )
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = nombreUsuario,
                    onValueChange = onNombreUsuarioChange,
                    label = { Text(stringResource(R.string.nombre_usuario)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.usuario),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "Icono de usuario"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorResource(R.color.white),
                        unfocusedTextColor = colorResource(R.color.white),
                        focusedContainerColor = colorResource(R.color.gris),
                        unfocusedContainerColor = colorResource(R.color.gris),
                        focusedBorderColor = colorResource(R.color.azulClaro),
                        unfocusedBorderColor = colorResource(R.color.azulClaro),
                        focusedLabelColor = colorResource(R.color.white),
                        unfocusedLabelColor = colorResource(R.color.white),
                        disabledLabelColor = colorResource(R.color.white),
                        errorLabelColor = colorResource(R.color.white)
                    )
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text(stringResource(R.string.email)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.correo),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "Icono de correo"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorResource(R.color.white),
                        unfocusedTextColor = colorResource(R.color.white),
                        focusedContainerColor = colorResource(R.color.gris),
                        unfocusedContainerColor = colorResource(R.color.gris),
                        focusedBorderColor = colorResource(R.color.azulClaro),
                        unfocusedBorderColor = colorResource(R.color.azulClaro),
                        focusedLabelColor = colorResource(R.color.white),
                        unfocusedLabelColor = colorResource(R.color.white),
                        disabledLabelColor = colorResource(R.color.white),
                        errorLabelColor = colorResource(R.color.white)
                    )
                )

                var mostrarPassword by remember { mutableStateOf(false) }
                val icono = if (mostrarPassword) R.drawable.view else R.drawable.hide
                Spacer(Modifier.height(24.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text(stringResource(R.string.contra)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.candado),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "Icono de candado"
                        )
                    },
                    visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                            Icon(
                                painter = painterResource(icono),
                                contentDescription = stringResource(R.string.mostrar_password),
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorResource(R.color.white),
                        unfocusedTextColor = colorResource(R.color.white),
                        focusedContainerColor = colorResource(R.color.gris),
                        unfocusedContainerColor = colorResource(R.color.gris),
                        focusedBorderColor = colorResource(R.color.azulClaro),
                        unfocusedBorderColor = colorResource(R.color.azulClaro),
                        focusedLabelColor = colorResource(R.color.white),
                        unfocusedLabelColor = colorResource(R.color.white),
                        disabledLabelColor = colorResource(R.color.white),
                        errorLabelColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(Modifier.height(24.dp))
                BotonGuardar(
                    "Guardar",
                    onClick = { /* Acción de editar imagen */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            )
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun EditarPerfilPreview() {
    EditarPerfil()
}
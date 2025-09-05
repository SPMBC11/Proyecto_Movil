package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R

@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onLogin: (email: String, password: String, remember: Boolean) -> Unit = { _, _, _ -> },
    onForgotPassword: () -> Unit = {},
    onRegister: () -> Unit = {},
    onGoogle: () -> Unit = {},
    onFacebook: () -> Unit = {},
    onApple: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var remember by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    val bg = colorResource(R.color.black)
    val card = colorResource(R.color.black)
    val fieldText = colorResource(R.color.white)
    val hint = colorResource(R.color.gris)
    val accent = colorResource(R.color.teal_200)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(horizontal = 20.dp)
    ) {
        // Back button
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 8.dp)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Color.White)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
            , horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo placeholder (circle with 3 diagonal lines)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Accede a tu cuenta",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(24.dp))

            // Email
            InputField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                isPassword = false,
                showPassword = false,
                onTogglePassword = { }
            )
            Spacer(Modifier.height(12.dp))
            // Password
            InputField(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                isPassword = true,
                showPassword = showPassword,
                onTogglePassword = { showPassword = !showPassword }
            )

            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = remember,
                    onCheckedChange = { remember = it },
                    colors = CheckboxDefaults.colors(checkedColor = accent, checkmarkColor = Color.Black)
                )
                Text(stringResource(R.string.recordarme), color = Color.White, fontSize = 14.sp)
            }

            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onLogin(email, password, remember) },
                colors = ButtonDefaults.buttonColors(containerColor = accent, contentColor = Color.Black),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Ingresar", fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.olvidaste_tu_contrase_a),
                color = hint,
                fontSize = 13.sp,
                modifier = Modifier.clickable { onForgotPassword() }
            )

            Spacer(Modifier.height(18.dp))
            Divider(color = Color(0xFF2B2B2B))
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.continuar_con),
                color = hint,
                fontSize = 13.sp
            )
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                IconButton(onClick = { /* Google */ }) {
                    Icon(painter = painterResource(id = R.drawable.google), contentDescription = "Google", tint = Color.Unspecified)
                }
                IconButton(onClick = { /* Facebook */ }) {
                    Icon(painter = painterResource(id = R.drawable.facebook), contentDescription = "Facebook", tint = Color.Unspecified)
                }
                IconButton(onClick = { /* Apple */ }) {
                    Icon(painter = painterResource(id = R.drawable.manzana), contentDescription = "Apple", tint = Color.Unspecified)
                }
            }

            Spacer(Modifier.height(22.dp))
            Row {
                Text(stringResource(R.string.no_tienes_una_cuenta), color = hint, fontSize = 13.sp)
                Text(
                    stringResource(R.string.reg_strate),
                    color = accent,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onRegister() }
                )
            }
        }
    }
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean,
    showPassword: Boolean,
    onTogglePassword: () -> Unit
) {
    val card = Color(0xFF151515)
    val fieldText = Color(0xFFEDEDED)
    val hint = Color(0xFF9A9A9A)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = hint) },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = fieldText),
        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Ocultar" else "Mostrar",
                        tint = hint
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = card,
            unfocusedContainerColor = card,
            focusedBorderColor = Color(0xFF2B2B2B),
            unfocusedBorderColor = Color(0xFF2B2B2B),
            cursorColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}



@Composable
private fun LogoMark() {
    // Simple circular logo with 3 diagonal lines to mimic the mockup
    val ring = Color.White
    val line = Color.White
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .border(width = 2.dp, color = ring, shape = CircleShape)
    ) {
        // Diagonal lines
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            DiagonalLine()
            DiagonalLine(offset = 10.dp)
            DiagonalLine(offset = 20.dp)
        }
    }
}

@Composable
private fun DiagonalLine(offset: Dp = 0.dp) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = offset)
            .clip(RoundedCornerShape(50))
            .background(Color.Transparent)
    ) {
        // Using a thin rectangle rotated visually by placing inside the padded box
        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E)
@Composable
private fun LoginScreenPreview() {
    com.example.proyecto_movil.ui.theme.Proyecto_movilTheme {
        LoginScreen()
    }
}
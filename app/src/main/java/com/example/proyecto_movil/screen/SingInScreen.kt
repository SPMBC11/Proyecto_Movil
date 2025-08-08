package com.example.proyecto_movil.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_movil.R
import com.example.proyecto_movil.ui.theme.Proyecto_movilTheme
import com.example.proyecto_movil.utils.SocialLoginButton

@Composable
fun SignInScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo), // tu logo
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Empecemos", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de redes sociales (usa logos propios)
        Column {
            SocialLoginButton(
                text = "Continúa con Google",
                icon = painterResource(id = R.drawable.google),
                onClick = { /* Acción Google */ }
            )

            SocialLoginButton(
                text = "Continúa con Facebook",
                icon = painterResource(id = R.drawable.facebook),
                backgroundColor = Color(0xFF1877F2), // Azul Facebook
                onClick = { /* Acción Facebook */ }
            )

            SocialLoginButton(
                text = "Continúa con Apple",
                icon = painterResource(id = R.drawable.manzana),
                onClick = { /* Acción Apple */ }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Loguéate con tus datos de acceso", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("¿No tienes una cuenta? ", color = Color.White)
        Text(
            "Registrarse", color = Color(0xFF00E5FF),
            modifier = Modifier.clickable { /* ir a registro */ }
        )
    }
}

@Preview (showBackground = true)
@Composable
fun SignInScreenPreview() {
    Proyecto_movilTheme {
        SignInScreen(onLoginClick = {})
    }
}

package com.example.critichord.ui
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critichord.R
import com.example.critichord.ui.utils.Background
import com.example.critichord.ui.utils.LogoApp
import com.example.critichord.ui.utils.AppButton
import com.example.critichord.ui.utils.CheckboxDatos
import com.example.critichord.ui.utils.Registrate
import com.example.critichord.ui.utils.Terminos
import com.example.critichord.ui.utils.YatienesCuenta

@Composable
fun RegisterScreen2(
    modifier: Modifier = Modifier
){
    var click = 0;
    var nombrePersona by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier,
    ){
        Background()
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
         modifier = Modifier.fillMaxSize()
        ){
            Spacer(Modifier.height(90.dp))
            LogoApp()
            Spacer(Modifier.height(60.dp))
            Registrate("Registrate")
            Spacer(Modifier.height(10.dp))
            FormularioRegistro(
                nombrePersona = nombrePersona,
                nombreUsuario = nombreUsuario,
                email = email,
                password = password,
                onNombrePersonaChange = { nombrePersona = it },
                onNombreUsuarioChange = { nombreUsuario = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },

            )
            Spacer(Modifier.height(30.dp))
            Row( modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,){

            CheckboxDatos()
                Spacer(Modifier.width(8.dp))
            Terminos("He leido y acepto los terminos y condiciones",
                modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(30.dp))
            AppButton(
                stringResource(R.string.registrar),
               onClick = {
                   Log.d("RegisterScreen2", "nombrePersona: $nombrePersona")
                Log.d("RegisterScreen2", "nombreUsuario: $nombreUsuario")
                Log.d("RegisterScreen2", "email: $email")
                Log.d("RegisterScreen2", "password: $password")
                }
            )
            Spacer(Modifier.height(30.dp))
            YatienesCuenta("Ya tienes una cuenta? inicia sesion")
        }
    }
    }

fun Text(text: String, fontSize: TextUnit, fontWeight: FontWeight.Companion, color: Color, modifier: Modifier) {

}

@Preview
@Composable
fun RegisterScreen2Preview() {
    RegisterScreen2()
}


@Composable
fun FormularioRegistro(
    nombrePersona: String = "",
    nombreUsuario: String = "",
    email: String = "",
    password: String = "",
    onNombrePersonaChange: (String) -> Unit ,
    onNombreUsuarioChange: (String) -> Unit,
    onEmailChange: (String) -> Unit ,
    onPasswordChange: (String) -> Unit ,
    modifier: Modifier = Modifier

){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier){
        OutlinedTextField(
            value = nombrePersona,
            onValueChange = onNombrePersonaChange,
            label = { Text(stringResource(R.string.nombre)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.usuario),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",

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
        OutlinedTextField(
            value = nombreUsuario,
            onValueChange =
              onNombreUsuarioChange,
            label = { Text(stringResource(R.string.nombre_usuario)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.usuario),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",  

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

        OutlinedTextField(
            value = email,
            onValueChange =
             onEmailChange,
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.correo),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",

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
        ))
        var mostrarPassword by remember { mutableStateOf(false) }
        var icono = if (mostrarPassword)  R.drawable.view else R.drawable.hide
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.contra)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.candado),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Icono de usuario",

                    )
            },

            visualTransformation = if(mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    mostrarPassword = !mostrarPassword
                }) {
                   Icon(
                       painter = painterResource(icono),
                       contentDescription = (stringResource(R.string.mostrar_password)),
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
            modifier = Modifier.padding(vertical =8.dp)
        )
    }
    }


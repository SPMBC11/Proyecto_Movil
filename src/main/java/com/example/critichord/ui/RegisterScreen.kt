package com.example.critichord.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critichord.R
import com.example.critichord.ui.utils.AppButton
import com.example.critichord.ui.utils.Background
import com.example.critichord.ui.utils.LogoApp

@Composable
fun MensajeBienvenida(
    nombre : String,
    modifier: Modifier = Modifier
){
    Text(
        text = "Bienvenido a critichord",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(R.color.cian),
        modifier = modifier
        )
}
@Composable
@Preview(showBackground = true)
fun MensajeBienvenidaPreview(){
    MensajeBienvenida(stringResource(R.string.app_name))
}


@Composable
@Preview(showBackground = true)
fun LogoAppPreview(){
    LogoApp()
}

@Composable
@Preview(showBackground = true)
fun AppButtonPreview(){
    AppButton(stringResource(R.string.registrarse))
}
@Composable
fun ExternalLogo(
    idImage: Int,
    description: String,
    modifier: Modifier = Modifier

){
    Image(
        painter = painterResource(idImage),
        contentDescription = description,
        modifier = Modifier
            .height(48.dp)
            .width(48.dp)
            .padding(8.dp)
    )
}
@Composable
@Preview(showBackground = true)
fun ExternalLogoPreview(){
    ExternalLogo(R.drawable.apple, "Logo critichord")
}

//row, column, box
@Composable
fun BodyRegister(
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ){
        LogoApp()
        MensajeBienvenida(
            stringResource(R.string.app_name),
            modifier = Modifier.padding(8.dp))
        AppButton(stringResource(R.string.registrarse),
            modifier = Modifier.padding(8.dp))
        AppButton("iniciar sesion")
        Row{
        ExternalLogo(R.drawable.facebook, "Logo facebook")
        ExternalLogo(R.drawable.apple, "Logo apple")
        }
    }
}
@Composable
@Preview(showBackground = true)
fun BodyRegisterPreview(){
    BodyRegister()
}

@Composable
fun Register(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    )
    Background()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1F))
        BodyRegister()
        Spacer(modifier = Modifier.weight(1F))
    Text(
        stringResource(R.string.app_name),
    )
}
    }

@Composable
@Preview(showBackground = true)
fun RegisterPreview(){
    Register()
}
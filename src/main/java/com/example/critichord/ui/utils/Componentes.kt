package com.example.critichord.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critichord.R

@Composable
fun LogoApp(
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = ("Logo critichord"),
        modifier = Modifier.size(120.dp)
    )
}

@Composable
fun AppButton(
    Textoboton: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.cian),
            contentColor = colorResource(R.color.white)
        ),
        modifier = modifier

    )
    {
        Text(Textoboton)
    }
}
@Composable
fun Registrate(
    texto : String,
    modifier: Modifier = Modifier
){
    Text(
        text = texto,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}
@Composable
fun Terminos(
    texto : String,
    modifier: Modifier = Modifier
){
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}

@Composable
fun YatienesCuenta(texto : String,
                   modifier: Modifier = Modifier
){
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Light,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}
@Composable
fun CheckboxDatos(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }

}

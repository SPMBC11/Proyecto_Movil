package com.example.proyecto_movil.utils


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.res.colorResource
import com.example.proyecto_movil.R

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.teal_200),
            contentColor = colorResource(R.color.white)
        ),
        modifier = modifier
    ) {
        Text(text)
    }
}

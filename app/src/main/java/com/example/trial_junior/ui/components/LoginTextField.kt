package com.example.firstjetpack.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginTextField(
    modifier:Modifier=Modifier,
    value:String,
    labelText:String,
    leadingIcon:ImageVector?=null,
    keyboardType: KeyboardType=KeyboardType.Text,
    visualTransformation: VisualTransformation=VisualTransformation.None,
    onValueChange:(String)->Unit
){
    OutlinedTextField(
        modifier=modifier,
        value=value,
        onValueChange=onValueChange,
        label={ Text(labelText) },leadingIcon = {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType),
        visualTransformation=visualTransformation,
        shape = RoundedCornerShape(percent = 30)
    )
}
@Preview(showBackground = true)
@Composable
fun prevTextField(){
    LoginTextField(
        value = "",
        onValueChange={},
        labelText = "password"
    )

}
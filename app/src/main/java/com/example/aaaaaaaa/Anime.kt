package com.example.aaaaaaaa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aaaaaaaa.ui.theme.AAAAAAAATheme

class Anime: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaAnime()
        }
    }
}

@Composable
fun ListaAnimes() {
    val context= LocalContext.current
    val expanded = remember { mutableStateOf(true) }
    val extraPadding = if(expanded.value) 60.dp else 0.dp
    val animes=remember {
        mutableStateOf(
            mutableListOf(
                Animei("Hunter x Hunter","2011","Sigue a Gon en esta entretenida aventura para ver " +
                        "a su papito corazon que lo dejo tirado en una isla",R.drawable.hxh){
                    expanded.value = !expanded.value
                },
                Animei("Anime2","2013","nose " +
                        "nose",R.drawable.anime){
                    expanded.value = !expanded.value
                },
                Animei("Anime3","2008","anime generico" +
                        "=resena generica",R.drawable.anime){
                    expanded.value = !expanded.value
                }
            )
        )
    }

    var nombre by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(bottom = extraPadding)) {


        animes.value.forEach { anime ->
            ElevatedButton(onClick = anime.onClick) {
                Image(
                    painter = painterResource(id = anime.icon),
                    contentDescription = anime.nombre,
                    modifier = Modifier.padding(8.dp).size(48.dp)
                )
                Text(if (expanded.value) anime.nombre else
                    anime.nombre+ "\nFecha Emision:"+anime.anio+
                            "\n"+anime.descripcion)
            }
        }

        var errorNumeros by remember { mutableStateOf(false) }
        Text("Agregar Aime")
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del anime") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = anio,
            onValueChange = {
                anio = it
                errorNumeros=it.toIntOrNull()==null
            },
            label = { Text("Anio, porfavro use numeros") },
            isError = errorNumeros,
            modifier = Modifier.fillMaxWidth()
        )
        if(errorNumeros){
            anio=""
            Text("Solo numeros enteros")}
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )


        ElevatedButton(onClick =   {
            if(nombre.isNotBlank()){
                animes.value.add(
                    Animei(nombre,anio, descripcion,R.drawable.anime){
                        expanded.value=!expanded.value
                    }
                )
                nombre=""
                anio=""
                descripcion=""
            }
        } ) { Text("Agregar Anime") }
        ElevatedButton(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) { Text(stringResource(id=R.string.volver_menu))}

    }
}
data class Animei(
    val nombre: String,
    val anio: String,
    val descripcion: String,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun PantallaAnime() {
    ListaAnimes()
}

@Preview(showBackground = true)
@Composable
fun ListaAnimesPreview() {
    AAAAAAAATheme {
        ListaSeries()
    }
}
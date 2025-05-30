package com.example.aaaaaaaa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

class Peliculas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPeliculas()
        }
    }
}

@Composable
fun ListaPeliculas() {
    val context= LocalContext.current
    val expanded = remember { mutableStateOf(true) }
    val extraPadding = if(expanded.value) 60.dp else 0.dp
    val peliculas=remember {
        mutableStateListOf(
                Pelicula("Pacific Rim","2013","Una pelicula de accion y ciencia ficcion " +
                        "que claramente no tuvo una segunda pelicula","9.2",R.drawable.pacificrim){
                    expanded.value = !expanded.value
                },
                Pelicula("Rapidos y furiosos","20101","no sabia que caeriamos aqui " +
                        "tuve fe (XDDDDD)","2.1",R.drawable.rapidosyfuriosos){
                    expanded.value = !expanded.value
                },
                Pelicula("Machuca","2004","Pelicula generica de dictadura " +
                        "zzzzz","2,8",R.drawable.machuca){
                    expanded.value = !expanded.value
                },
                Pelicula("Malena","2000","Monica Bellucci , Bravissima , Bellisima ,  " +
                        "10/10","10",R.drawable.monicabellucci){
                    expanded.value = !expanded.value
                }

        )
    }

    var nombre by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = extraPadding)) {
        items(peliculas) { pelicula ->
                ElevatedButton(onClick = pelicula.onClick) {
                    Image(
                        painter = painterResource(id = pelicula.icon),
                        contentDescription = pelicula.nombre,
                        modifier = Modifier.padding(8.dp).size(48.dp)
                    )
                    Text(
                        if (expanded.value) pelicula.nombre else
                            pelicula.nombre + "\nFecha Emision:" + pelicula.anio +
                                    "\n" + pelicula.descripcion + "\nRating IMDB:" + pelicula.rating
                    )
                }

        }
        item{
            var errorNumeros by remember { mutableStateOf(false) }

            Text(stringResource(id=R.string.agregar_pelicula))
            OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(stringResource(id=R.string.nombre_pelicula)) },
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
        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating IMDB") },
            modifier = Modifier.fillMaxWidth()
        )

        ElevatedButton(onClick =   {
            if(nombre.isNotBlank()){
                peliculas.add(
                    Pelicula(nombre,anio, descripcion,rating,R.drawable.claqueta){
                        expanded.value=!expanded.value
                    }
                )
                nombre=""
                anio=""
                descripcion=""
                rating=""
            }
        } ) { Text(stringResource(id=R.string.agregar_pelicula))  }
        ElevatedButton(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) { Text(stringResource(id=R.string.volver_menu)) }
        }

    }
}
data class Pelicula(
    val nombre: String,
    val anio: String,
    val descripcion: String,
    val rating:String,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun PantallaPeliculas() {
    ListaPeliculas()
}

@Preview(showBackground = true)
@Composable
fun ListaPeliculasPreview() {
    AAAAAAAATheme {
        ListaPeliculas()
    }
}
package com.example.aaaaaaaa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aaaaaaaa.ui.theme.AAAAAAAATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AAAAAAAATheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) { innerPadding ->
                    uno(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        context = this
                    )
                }
            }
        }
    }
}


@Composable
fun uno(name: String, modifier: Modifier = Modifier, context : Context) {
    val expanded = remember { mutableStateOf(true) }
    val extraPadding = if(expanded.value) 60.dp else 0.dp
    val categorias = remember {
        mutableStateOf(
            mutableListOf(
                Categoria("Películas", R.drawable.claqueta) {
                    context.startActivity(Intent(context, Peliculas::class.java))
                },
                Categoria("Series", R.drawable.series) {
                    context.startActivity(Intent(context, Series::class.java))
                },
                Categoria("Anime", R.drawable.anime) {
                    context.startActivity(Intent(context, Anime::class.java))
                }



            )
        )
    }
    var nombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(bottom = extraPadding)) {
        Text(text = "MediaExplorer")


        categorias.value.forEach { categoria ->
            ElevatedButton(onClick = categoria.onClick) {
                Image(
                    painter = painterResource(id = categoria.icon),
                    contentDescription = categoria.nombre,
                    modifier = Modifier.padding(8.dp).size(48.dp)
                )
                Text(if (expanded.value) categoria.nombre else "MMMMM")
            }
        }

        Text("Agregar categoria")
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la categoria") },
            modifier = Modifier.fillMaxWidth()
        )
        ElevatedButton(onClick = {
            if(nombre.isNotBlank()){
                categorias.value.add(
                    Categoria(nombre,R.drawable.mas){
                        expanded.value=!expanded.value
                    }
                )
                nombre=""
            }
        }) { Text("Agregar categoria")}

    }
}


data class Categoria(
    val nombre: String,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun unoPreview(modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(true) }
    val extraPadding = if (expanded.value) 60.dp else 0.dp

    Column(modifier = Modifier.padding(bottom = extraPadding)) {
        Text(text = "")
        Text(text = "MediaExplorer")
        ElevatedButton(onClick = {}) {
            Text(if (expanded.value) "Peliculas" else "MMMMM")
        }
        ElevatedButton(onClick = {}) {
            Text(if (expanded.value) "Series" else "MMMMM")
        }
        ElevatedButton(onClick = {}) {
            Text(if (expanded.value) "Anime" else "MMMMM")
        }
        ElevatedButton(onClick = {}) {
            Text(if (expanded.value) "Añadir Categoria" else "MMMMM")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AAAAAAAATheme {
        unoPreview()
    }
}
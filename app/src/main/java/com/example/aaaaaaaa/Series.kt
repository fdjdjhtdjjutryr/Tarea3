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

class Series: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        PantallaSeries()
    }
}
}

@Composable
fun ListaSeries() {
    val context= LocalContext.current
    val expanded = remember { mutableStateOf(true) }
    val extraPadding = if(expanded.value) 60.dp else 0.dp
    val series=remember {
        mutableStateOf(
            mutableListOf(
                Serie("Game of Thrones","2011","Una atrapante serie donde los juegos de " +
                        "poder la serie murio en las ultimas temporadas","9.2",R.drawable.got){
                    expanded.value = !expanded.value
                },
                Serie("Vikings","2013","Acompana a salvajes germanicos en esta historia donde atacan a " +
                        "otros salvajes germanicos , 10 de 10","8.5",R.drawable.vikings){
                    expanded.value = !expanded.value
                },
                Serie("Breaking Bad","2008","Acompana al pelao heisenberg a ganar plata para su familia " +
                        "para que la skyler se la regale al amante XD","9.5",R.drawable.brbad){
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


        series.value.forEach { serie ->
            ElevatedButton(onClick = serie.onClick) {
                Image(
                    painter = painterResource(id = serie.icon),
                    contentDescription = serie.nombre,
                    modifier = Modifier.padding(8.dp).size(48.dp)
                )
                Text(if (expanded.value) serie.nombre else
                    serie.nombre+ "\nFecha Emision:"+serie.anio+
                            "\n"+serie.descripcion+"\nRating IMDB:"+serie.rating)
            }
        }

        var errorNumeros by remember { mutableStateOf(false) }
        Text("Agregar serie")
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la serie") },
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
                series.value.add(
                    Serie(nombre,anio, descripcion,rating,R.drawable.series){
                        expanded.value=!expanded.value
                    }
                )
                nombre=""
                anio=""
                descripcion=""
                rating=""
            }
        } ) { Text("Agregar Serie") }
        ElevatedButton(onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
        }) { Text(stringResource(id=R.string.volver_menu))}

    }
}
data class Serie(
    val nombre: String,
    val anio: String,
    val descripcion: String,
    val rating:String,
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun PantallaSeries() {
    ListaSeries()
}

@Preview(showBackground = true)
@Composable
fun ListaSeriesPreview() {
    AAAAAAAATheme {
        ListaSeries()
    }
}
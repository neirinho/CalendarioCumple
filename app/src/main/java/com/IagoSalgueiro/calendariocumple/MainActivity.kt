package com.IagoSalgueiro.calendariocumple


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.IagoSalgueiro.calendariocumple.ui.theme.CalendarioCumpleTheme
import com.IagoSalgueiro.calendariocumple.R

// Simulación de datos inicial
val initialCumpleaneros = listOf(
    Cumpleanero("Iago Salgueiro", "01/01/1990"),
    Cumpleanero("María López", "15/03/1995"),
    Cumpleanero("Carlos Pérez", "22/07/1988")
)

data class Cumpleanero(val nombre: String, val fecha: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarioCumpleTheme {
                BirthdayApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayApp() {
    var cumpleaneros by remember { mutableStateOf(initialCumpleaneros) }
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var fecha by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Calendario de Cumpleaños") }
            )
        },
        containerColor = Color(0xFFADD8E2),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Texto descriptivo
                Text(
                    text = "Cumpleaños de tus amigos o familiares.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Imagen decorativa
                Image(
                    painter = painterResource(id = R.drawable.birthday_image),
                    contentDescription = "Imagen de Tarta de cumpleaños",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Formulario para añadir cumpleaños
                Text(text = "Añadir nuevo cumpleaños", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha (dd/mm/yyyy)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (nombre.text.isNotBlank() && fecha.text.isNotBlank()) {
                            cumpleaneros = cumpleaneros + Cumpleanero(nombre.text, fecha.text)
                            nombre = TextFieldValue()
                            fecha = TextFieldValue()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Añadir")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de cumpleaños
                Text(text = "Próximos cumpleaños", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(cumpleaneros) { cumpleanero ->
                        CumpleaneroItem(cumpleanero)
                    }
                }
            }
        }
    )
}

@Composable
fun CumpleaneroItem(cumpleanero: Cumpleanero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = cumpleanero.nombre, style = MaterialTheme.typography.bodyLarge)
            Text(text = cumpleanero.fecha, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalendarioCumpleTheme {
        BirthdayApp()
    }
}


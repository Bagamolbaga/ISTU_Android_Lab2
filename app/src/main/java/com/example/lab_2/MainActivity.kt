package com.example.lab_2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab_2.theme.Lab_2Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.VerticalAlignmentLine
import coil.compose.rememberAsyncImagePainter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen()
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val list: List<ImageCard> = getWorldLandmarks()

    // Состояние: текущий индекс (сохраняется при ротации)
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentArt = list[currentIndex]

    fun handleNext(){
        println(currentIndex)
        if (currentIndex < list.size - 1) currentIndex++
    }

    fun handlePrev() {
        if (currentIndex > 0) currentIndex--
    }

    val onNextEnabled = currentIndex < list.size - 1;
    val onPreviousEnabled = currentIndex > 0;
    val pageText = "${currentIndex + 1}/${list.size}";

    if (isLandscape) {
        // Landscape: Row (изображение слева, инфо+кнопки справа)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),  // Scroll если контент большой
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtImage(currentArt.imageUrl, modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtInfo(currentArt)
                Spacer(modifier = Modifier.height(16.dp))
                NavigationButtons(
                    onPrevious = ::handlePrev,
                    onNext = ::handleNext,
                    onPreviousEnabled,
                    onNextEnabled,
                    pageText
                )
            }
        }
    } else {
        // Portrait: Column (изображение сверху, инфо+кнопки снизу)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtImage(currentArt.imageUrl, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            ArtInfo(currentArt)
            Spacer(modifier = Modifier.height(24.dp))
            NavigationButtons(
                onPrevious = ::handlePrev,
                onNext = ::handleNext,
                onPreviousEnabled,
                onNextEnabled,
                pageText
            )
        }
    }
}

@Composable
fun ArtImage(imageUrl: String, modifier: Modifier = Modifier) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Artwork",
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxHeight(0.5f),
            contentScale = ContentScale.Fit,
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false; isError = false },
            onError = { isLoading = false; isError = true }
        )
        if (isLoading) {
            Text("Загрузка...")
        }
        if (isError) {
            Text("Ошибка загрузки: $imageUrl")
        }
    }
}

@Composable
fun ArtInfo(card: ImageCard) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = card.title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = card.description,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = card.country,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun NavigationButtons(onPrevious: () -> Unit, onNext: () -> Unit, onPreviousEnabled: Boolean, onNextEnabled: Boolean, pageText: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onPrevious, enabled = onPreviousEnabled) {
            Text(stringResource(R.string.previous))
        }
        Text(text = pageText)
        Button(onClick = onNext, enabled = onNextEnabled) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5")  // Portrait preview
@Preview(showBackground = true, device = "id:pixel_5")  // Landscape preview
@Composable
fun ArtGalleryPreview() {
    Lab_2Theme {
        ArtGalleryScreen()
    }
}
package com.mazizs.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mazizs.movies.data.DataSource
import com.mazizs.movies.model.Topic
import com.mazizs.movies.navigation.NavigationGraph
import com.mazizs.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController //Untuk mengelola navigasi antar layar atau komponen dalam aplikasi secara private
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController() //Untuk menavigasi seperti perpindahan antar tampilan HomeScreen dengan DetailMovie
            MoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NavigationGraph(navController = navController) //Untuk memanggil komponen composable dari NavigationGraph
                    }
                }
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi HomeScreen yaitu umtuk menampilkan dan mengatur tampilan home
@Composable
fun HomeScreen(
    navigationToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        //Untuk membuat grid dengan list item yang dapat discroll secara vertikal
        columns = GridCells.Fixed(2), //Untuk menentukan jumlah kolom yang akan ditampilkan di list grid yaitu 2
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        items(DataSource.topics) { topic ->
            MovieCard(topic, onClick = { navigationToDetail(topic.id) })
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi MovieCard yaitu membuat tampilan seperti card yang menampilkan gambar film atau movie
@Composable
fun MovieCard(topic: Topic, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card {//Untuk membuat seperti bentuk kartu yang dimana pada setiap ujungnya melengkung atau tidak lancip
        Box( //untuk mengatur tampilan gambar film di dalam kartu
            modifier = modifier.fillMaxWidth()
        ) {
            Image( //Untuk menampilkan gambar film di dalam kartu yang dipanggil dari data class Topic dan DataSource
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column( //untuk mengatur tampilan elemen komponen dalam tata letak berbentuk kolom, seperti menatur jarak, lebar, dan posisi
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small)
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text( //Untuk menampilkan judul topik film yang dipanggil dari data class Topic dan DataSource
                text = stringResource(id = topic.name),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Row( //Merupakan yang berisi dua komponen, yaitu ikon dan rating
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon( //Untuk menampilkan ikon bintang disebelah kiri rating
                    painter = painterResource(R.drawable.rating),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = dimensionResource(R.dimen.padding_small))
                        .size(15.dp)
                )
                Text( //Untuk menampilkan rating film
                    text = topic.imdbRating.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                )
            }
            Button( //Untuk mengklik tombol Lihat Deskripsi, berfungsi untuk melihat deskripsi dari film
                onClick = {
                    onClick()
                },
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            ) {
                Text(text = "Lihat Deskripsi")
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi DetailMovie yaitu untuk menampilkan deskripsi movie
@Composable
fun DetailMovie(
    movieData: Int,
    navigateBack: () -> Unit
) {
    val movie: Topic? = DataSource.getMovieById(movieData)

    if (movie != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row( //Top bar
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton( //Untuk menampilkan tombol ikon panah kembali
                    onClick = navigateBack
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text( //Untuk menampilkan teks judul di halaman Detail Movie
                    text = "Deskripsi Movie",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Image( //untuk menampilkan gambar film dan mengaturnya di halaman Detail Movie
                painter = painterResource(id = movie.imageRes),
                contentDescription = "Movie image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text( //untuk menampilkan teks judul film di halaman Detail Movie
                stringResource(id = movie.name),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )
            Text( //Deskripsi film di halaman Detail Movie
                text = stringResource(id = movie.description),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                ),
                modifier = Modifier.padding(16.dp),
            )
        }
    } else {
        Text("Movie not found")
    }
}

//Fungsi di bawah ini adalah komponen Composable yang digunakan untuk menampilkan preview atau pratinjau dari Movies
@Preview(showBackground = true)
@Composable
fun MoviePreview() {
    MoviesTheme {
        val movieData = Topic(0, R.string.the_nun_ii, R.string.the_nun_ii_movie_description,5.7f, R.drawable.the_nun_ii)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieCard(topic = movieData, onClick = {})
        }
    }
}
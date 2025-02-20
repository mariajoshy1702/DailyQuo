import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquo.ui.theme.QuoteViewModel

@Composable
fun HomeScreen(viewModel: QuoteViewModel) {
    val quote by viewModel.randomQuote.collectAsState()

    // Fetch a quote when the screen is first loaded
    LaunchedEffect(Unit) {
        viewModel.fetchRandomQuote()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = quote?.text ?: "Loading...",
            fontSize = 28.sp,  // Bigger, fancier font size
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "- ${quote?.author ?: "Unknown"}",
            fontSize = 12.sp, // Smaller font size for the author
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { viewModel.fetchRandomQuote() },
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
                .padding(10.dp),
            shape = CircleShape // Makes it round
        ) {
            Text(
                text = "Tap for a New Quote!",
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }

    }
}
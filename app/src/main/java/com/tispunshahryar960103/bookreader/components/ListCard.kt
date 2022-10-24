package com.tispunshahryar960103.bookreader.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.tispunshahryar960103.bookreader.model.MBook

//@Preview
@Composable
fun ListCard(book: MBook = MBook(
    "fdsdf",
    "Quantum mechanics",
    " Richard Feynman",
    "Mystery World"
), onPressedDetails:(String) -> Unit = {}) {

    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp

    Card(
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable {
                onPressedDetails.invoke(
                    book.title.toString())
            }
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {

                Image(
                    painter = rememberImagePainter(data = "data:image/jpeg;base64,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"),
                    contentDescription = "book image",
                    modifier = Modifier
                        .padding(4.dp)
                        .height(140.dp)
                        .width(100.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Fav Icon", modifier = Modifier.padding(1.dp))

                    BookRating(score = 3.5)

                }

            }

            Text(text = book.title.toString(),
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Text(text = book.authors.toString(),
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption) }
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {

            RoundedButton(label = "Reading", radius = 70)

        }


    }

}
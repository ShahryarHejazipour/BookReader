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
fun ListCard(book: MBook, onPressedDetails:(String) -> Unit = {}) {

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
                    painter = rememberImagePainter(data = book.photoUrl.toString()),
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
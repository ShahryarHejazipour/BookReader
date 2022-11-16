package com.tispunshahryar960103.bookreader.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

//@Preview
@Composable
fun ReaderAppBar(
    title:String="",
    icon:ImageVector? = null,
    showProfile:Boolean = true,
    navController: NavController,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile){
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Logo Icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f)
                    )
                }
                
                if (icon != null){
                    Icon(
                        imageVector = icon,
                        contentDescription = "arrow back",
                        tint = Color.Red.copy(alpha = 0.7f),
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() }
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
                Text(text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                //Spacer(modifier = Modifier.width(150.dp))
            }
        },
        actions = {
            IconButton(onClick = { FirebaseAuth.getInstance().signOut()
                .run { navController.navigate(ReaderScreens.LoginScreen.name) }
            }) {
              if (showProfile){
                  //Icons.Default.Logout.tintColor.green
                  Icon(
                      imageVector = Icons.Filled.Logout,
                      contentDescription = "Logout",
                      // tint = Color.Green
                  )
              }
            /*  else Box() {
                  
              }*/

            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

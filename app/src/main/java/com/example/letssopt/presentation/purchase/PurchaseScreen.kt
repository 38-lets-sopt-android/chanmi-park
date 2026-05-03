package com.example.letssopt.presentation.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.data.local.database.AppDatabase
import com.example.letssopt.presentation.home.fake.FakeHomeRepository
import kotlinx.serialization.Serializable

@Serializable
data object Purchase {}

@Composable
fun PurchaseRoute(
    paddingValues: PaddingValues,
) {
    val context = LocalContext.current
    val viewModel: PurchaseViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                val dao = AppDatabase.getDatabase(context).savedContentDao()
                @Suppress("UNCHECKED_CAST")
                return PurchaseViewModel(dao) as T
            }
        }
    )
    PurchaseScreen(
        modifier = Modifier.padding(paddingValues),
        onTicketClick = { title, image -> viewModel.save(title, image) }
    )
}

@Composable
private fun PurchaseScreen(
    modifier: Modifier = Modifier,
    onTicketClick: (title: String, image: String) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val contents = FakeHomeRepository.whatgorismContents

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 70.dp)
    ) {
        item(span = { GridItemSpan(3) }) {
            Text(
                text = "개별구매",
                color = LetsTheme.colors.textPrimary,
                style = LetsTheme.typography.title.bold_20,
                modifier = Modifier.padding(bottom = 45.dp)
            )
        }

        items(contents) { content ->
            PurchaseItem(
                image = content.image,
                title = content.title,
                onTicketClick = {
                    val imageName = context.resources.getResourceEntryName(content.image)
                    onTicketClick(content.title, imageName)
                }
            )
        }
    }
}

@Composable
private fun PurchaseItem(
    image: Int,
    title: String,
    onTicketClick: () -> Unit = {},
) {
    Column {
        Box {
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier.size(width = 100.dp, height = 150.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = com.example.letssopt.R.drawable.ic_ticket_18),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 5.dp, end = 5.dp)
                    .clickable { onTicketClick() }
            )
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = title,
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.subtitle.body_16,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PurchaseScreenPreview() {
    LetsTheme {
        PurchaseScreen()
    }
}

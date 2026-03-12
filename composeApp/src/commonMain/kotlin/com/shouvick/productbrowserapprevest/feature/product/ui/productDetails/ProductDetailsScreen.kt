package com.shouvick.productbrowserapprevest.feature.product.ui.productDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import productbrowserapprevest.composeapp.generated.resources.Res
import productbrowserapprevest.composeapp.generated.resources.ic_back_arrow_head

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    state.product?.let {
        ProductDeatilsContent(
            product = it,
            backBtn = navigateBack
        )
    }
}

@Composable
fun ProductDeatilsContent(
    product: Product,
    backBtn: () -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.statusBarsPadding()
            ) {

                IconButton(onClick = backBtn) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_back_arrow_head),
                        contentDescription = "back btn"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            AsyncImage(
                model = product?.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.title ?: "",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Brand: ${product.brand ?: "Unknown"}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Price: $${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Rating: ${product.rating}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.description ?: "",
                fontSize = 14.sp
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewProductContent() {

    val sampleProduct = Product(
        id = 1,
        title = "Red Lipstick",
        description = "The Red Lipstick is a classic and bold choice for adding a pop of color to your lips.",
        category = "beauty",
        price = 12.99,
        discountPercentage = 10.0,
        rating = 4.5,
        stock = 100,
        tags = listOf("beauty", "lipstick"),
        brand = "Chic Cosmetics",
        sku = "BEA-CHI-LIP-004",
        weight = 1,
        dimensions = null,
        warrantyInformation = "3 year warranty",
        shippingInformation = "Ships in 1 week",
        availabilityStatus = "In Stock",
        reviews = emptyList(),
        returnPolicy = "7 days return policy",
        minimumOrderQuantity = 1,
        meta = null,
        images = emptyList(),
        thumbnail = "https://cdn.dummyjson.com/product-images/beauty/red-lipstick/thumbnail.webp"
    )

    ProductDeatilsContent(
        product = sampleProduct,
        backBtn = {}
    )
}
package com.shouvick.productbrowserapprevest.feature.product.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.shouvick.productbrowserapprevest.commonWidget.CustomInputFieldWithDropdown
import com.shouvick.productbrowserapprevest.feature.product.data.models.Category
import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onCardClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        HomeScreenContent(
            allProduct = state.allProducts,
            searchValue = state.searchValue,
            selectedCategory = state.selectedCategory,
            updateSelectedCategory = viewModel::updateSelectedCategory,
            updateSearchValue = viewModel::updateSearchValue,
            onCardClick = onCardClick,
            allCategory = state.allCategory
        )

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> {
                Text(text = state.errorMessage ?: "Unknown Error")
            }

            else -> {

            }
        }
    }

}

@Composable
fun HomeScreenContent(
    allProduct: List<Product>?,
    allCategory: List<Category>?,
    searchValue: String,
    updateSearchValue: (String) -> Unit,
    onCardClick: (Int) -> Unit,
    selectedCategory: String,
    updateSelectedCategory: (String) -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchValue,
                onValueChange = updateSearchValue
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text("Filter By Category")
            allCategory?.let {
                CustomInputFieldWithDropdown(
                    value = selectedCategory,
                    onValueChange = updateSelectedCategory,
                    options = it
                )
            }

            allProduct?.let {
                LazyColumn {
                    items(it) { product ->
                        ProductCard(
                            product = product,
                            onClick = {
                                onCardClick(product.id ?: 0)
                            }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = product.title ?: "",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$${product.price}",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {

    val sampleProducts = listOf(
        Product(
            id = 1,
            title = "Red Lipstick",
            description = "Sample product",
            category = "beauty",
            price = 12.99,
            discountPercentage = 10.0,
            rating = 4.5,
            stock = 20,
            tags = emptyList(),
            brand = "Chic Cosmetics",
            sku = "",
            weight = 1,
            dimensions = null,
            warrantyInformation = "",
            shippingInformation = "",
            availabilityStatus = "In Stock",
            reviews = emptyList(),
            returnPolicy = "",
            minimumOrderQuantity = 1,
            meta = null,
            images = emptyList(),
            thumbnail = ""
        ),
        Product(
            id = 2,
            title = "Perfume",
            description = "Sample product",
            category = "fragrance",
            price = 49.99,
            discountPercentage = 5.0,
            rating = 4.2,
            stock = 15,
            tags = emptyList(),
            brand = "Calvin Klein",
            sku = "",
            weight = 1,
            dimensions = null,
            warrantyInformation = "",
            shippingInformation = "",
            availabilityStatus = "In Stock",
            reviews = emptyList(),
            returnPolicy = "",
            minimumOrderQuantity = 1,
            meta = null,
            images = emptyList(),
            thumbnail = ""
        )
    )

    val sampleCategories = listOf(
        Category(slug = "beauty", name = "Beauty", url = ""),
        Category(slug = "fragrance", name = "Fragrance", url = "")
    )

    HomeScreenContent(
        allProduct = sampleProducts,
        allCategory = sampleCategories,
        searchValue = "",
        updateSearchValue = {},
        onCardClick = {},
        selectedCategory = "",
        updateSelectedCategory = {}
    )
}
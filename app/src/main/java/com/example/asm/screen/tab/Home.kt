package com.example.asm.screen.tab

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.asm.api.Category
import com.example.asm.api.ViewModel.CategoryViewModel
import com.example.asm.api.ViewModel.ProductViewModel
import com.example.asm.components.Category
import com.example.asm.components.ItemProduct
import com.example.asm.ui.theme.ASMTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController?,
    categoryViewModel: CategoryViewModel,
    productViewModel: ProductViewModel
) {
    val context = LocalContext.current


    val categoryResponse by categoryViewModel.categories
    val productResponse by productViewModel.products

    var selectedCategory by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(Unit) {
        categoryViewModel.getCategories()
    }

    LaunchedEffect(key1 = categoryResponse?.data) {
        selectedCategory = categoryResponse?.data?.get(0)?._id
    }

    LaunchedEffect(key1 = selectedCategory) {
        productViewModel.getProductByCategory(selectedCategory.toString())
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Image(imageVector = Icons.Default.Search, contentDescription = "")
            Text(text = buildAnnotatedString {
                withStyle(style = ParagraphStyle()) {
                    withStyle(style = SpanStyle(color = Color(0xFF909090))) {
                        append("Make home\n")
                    }
                    withStyle(style = SpanStyle()) {
                        append("Beautiful".uppercase())
                    }
                }
            }, fontFamily = FontFamily.Serif)
            Image(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {

            items(if (categoryResponse?.data != null) categoryResponse!!.data else emptyList()) {
                Category(
                    url = it.thumb.url,
                    title = it.category_name,
                    isSelect = selectedCategory == it._id
                ) {
                    selectedCategory = it._id
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(if (productResponse?.data != null) productResponse!!.data else emptyList()) {
                ItemProduct(
                    url = it.product_thumb,
                    name = it.product_name,
                    price = it.product_price
                ) {
//                    navController.navigate("")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    ASMTheme {
        val categoryViewModel = CategoryViewModel()
        val productViewModel = ProductViewModel()
        HomeScreen(null, categoryViewModel, productViewModel)
    }
}
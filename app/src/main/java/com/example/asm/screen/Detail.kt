package com.example.asm.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.asm.CartItem
import com.example.asm.R
import com.example.asm.api.ViewModel.ProductViewModel
import com.example.asm.components.ButtonComponent

@Composable
fun Details(
    navController: NavController,
    productId: String,
    productViewModel: ProductViewModel,
    cart: List<CartItem>,
    addToCart: (cartItem: CartItem) -> Unit
) {
    val context = LocalContext.current
    val productDetail by productViewModel.productDetail

    LaunchedEffect(key1 = productId) {
        productViewModel.getDetailProduct(productId)
    }

    var quantity by remember {
        mutableIntStateOf(1)
    }

    var isAddTocart by remember {
        mutableStateOf(false)
    }

    val check = {
        if (cart.any { it.id == productId }) {
            true
        } else {
            false
        }
    }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.2f)
                    .zIndex(10f)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .width(50.dp)
                        .aspectRatio(1f)
                        .background(Color.White)
                        .zIndex(10f)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
            AsyncImage(
                model = productDetail?.data?.product_images?.get(0)?.url,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 100.dp))
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight()
                    .align(alignment = Alignment.TopEnd),
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )
        }
        Column(
            modifier = Modifier.padding(25.dp)
        ) {
            Text(
                text = productDetail?.data?.product_name ?: "",
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$ ${productDetail?.data?.product_price}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                if (!check()) Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFE0E0E0))
                            .padding(5.dp)
                            .width(30.dp)
                            .aspectRatio(1f)
                            .clickable {
                                if (productDetail?.data?.stock != null) {
                                    if (quantity >= productDetail!!.data.stock)
                                        productDetail!!.data.stock
                                    else
                                        quantity++
                                }
                            },
                    )
                    Text(
                        text = quantity.toString(),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Icon(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFE0E0E0))
                            .padding(5.dp)
                            .width(30.dp)
                            .aspectRatio(1f)
                            .clickable {
                                if (quantity <= 1) {
                                    quantity = 1
                                } else {
                                    quantity--
                                }
                            },
                        imageVector = Icons.Default.Menu,
                        contentDescription = ""
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .width(20.dp)
                        .aspectRatio(1f)
                )
                Text(
                    text = "4.5",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 18.sp
                )
                Text(text = "(200 reviews)", color = Color(0xFF808080), fontSize = 14.sp)
            }

            Text(
                text = productDetail?.data?.product_description ?: "",
                modifier = Modifier.padding(top = 10.dp),
                color = Color(0xFF808080),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0F0F0))
                    .padding(18.dp)
                    .width(24.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(15.dp))
            ButtonComponent(
                isDisable = check(),
                text = if (!isAddTocart) "Add to cart" else "Add in cart",
                modifier = Modifier
                    .fillMaxWidth(1f),
                paddingVertical = 10.dp
            ) {
                val cartItem = CartItem(
                    id = productId,
                    product_name = productDetail?.data?.product_name ?: "",
                    price = productDetail?.data?.product_price?.toInt() ?: 0,
                    url = productDetail?.data?.product_images?.get(0)?.url ?: "",
                    quantity = quantity,
                    stock = productDetail?.data?.stock ?: 0,
                )
                addToCart(cartItem)
                isAddTocart = true
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsPreview() {
//    Details()
}
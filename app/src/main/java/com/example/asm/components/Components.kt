package com.example.asm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.asm.R

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    backgroundColor: Color = Color(0xFF242424),
    shape: Shape = RoundedCornerShape(8.dp),
    paddingVertical: Dp = 10.dp,
    isDisable: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = shape,
        modifier = modifier,
        enabled = !isDisable
    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = paddingVertical)
        )
    }
}

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    label: String,
    value: String = "",
    onChangeText: (String) -> Unit,
) {

    var isShowPassword by rememberSaveable {
        mutableStateOf(isPassword)
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            color = Color(
                0xFF909090,
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        TextField(
            singleLine = true,
            value = value,
            onValueChange = onChangeText,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFFE0E0E0),
                unfocusedIndicatorColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (isPassword) {
                    Row {
                        IconToggleButton(
                            checked = isShowPassword,
                            onCheckedChange = { isShowPassword = !isShowPassword }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = "Eye"
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            },
            visualTransformation = if (!isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

@Composable
fun Category(url: Any, title: String, isSelect: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 20.dp)
            .clickable { onClick() }
            .padding(10.dp)
    )
    {
        Box(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(if (isSelect) Color.Black else Color(0xFFF5F5F5))
                .width(60.dp)
                .aspectRatio(1f)
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                model = url,
                contentScale = ContentScale.FillHeight,
                contentDescription = "Menu",
            )
        }
        Text(text = title, color = if (isSelect) Color.Black else Color(0xFF909090))
    }
}

@Composable
fun ItemProduct(
    id: String,
    url: Any,
    name: String,
    price: Number,
    click: () -> Unit
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .clickable { click() }) {
        Box {
            AsyncImage(
                model = url,
                contentDescription = "Product",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .aspectRatio(0.7f),
                contentScale = ContentScale.FillHeight,
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = Color(96, 96, 96, 100))
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = name,
            color = Color(0xFF606060),
            fontSize = 16.sp,
        )
        Text(
            text = "$ $price", color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ItemCart(
    name: String,
    price: Number,
    url: Any,
    quantity: Int,
    stock: Int,
    handleDelete: () -> Unit,
    handleIncrease: (quantity: Int) -> Unit,
    handleDecrease: (quantity: Int) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row {
            AsyncImage(
                model = url,
                contentDescription = "Product",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .width(100.dp)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column {
                    Text(
                        text = name,
                        color = Color(0xFF999999),
                        fontWeight = FontWeight(600)
                    )
                    Text(
                        text = "$ $price",
                        color = Color.Black,
                        fontWeight = FontWeight(600)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (quantity + 1 > stock) {
                                return@IconButton
                            } else
                                handleIncrease(quantity + 1)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = "Add",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(Color(0xFFE0E0E0))
                                .padding(5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = quantity.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    IconButton(
                        onClick = {
                            if (quantity - 1 <= 0) return@IconButton else handleDecrease(
                                quantity - 1
                            )
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = "Add",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(Color(0xFFE0E0E0))
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = { handleDelete() },
            modifier = Modifier.align(alignment = Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Delete",
                modifier = Modifier
                    .width(20.dp)
                    .aspectRatio(1f)
                    .clip(shape = CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
            )
        }
    }
}

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color(0xFF303030),
    onClick: () -> Unit
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(text = text, color = color, fontSize = 18.sp)
    }
}

@Composable
fun TitleCheckOut(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color(0xFF909090),
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.Create,
            contentDescription = "Edit"
        )
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .border(
                    2.dp, Color(0xFFBDBDBD),
                    RoundedCornerShape(2.dp)
                )
                .background(Color(0xFFBDBDBD))
                .weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.logo_asm),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .width(64.dp)
                .aspectRatio(1f)
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .border(
                    2.dp, Color(0xFFBDBDBD),
                    RoundedCornerShape(2.dp)
                )
                .background(Color(0xFFBDBDBD))
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
}

@Preview(showBackground = true)
@Composable
fun PreviewItemProduct() {
//    ItemProduct()
}

@Preview(showBackground = true)
@Composable
fun PreviewItemCart() {
    var password by rememberSaveable {
        mutableStateOf("")
    }
    TextInput(label = "Password", isPassword = true, value = password) {
        password = it
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListProduct() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(10) {
//            ItemProduct()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextButton() {
    TextButtonComponent(text = "Forgot Password") {

    }
}
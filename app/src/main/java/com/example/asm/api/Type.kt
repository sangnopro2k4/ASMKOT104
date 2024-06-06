package com.example.asm.api

data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val email: String, val password: String, val fullname: String)
data class LoginResponse(val status: Boolean, val message: String, val data: LoginData)
data class LoginData(val userId: String, val email: String, val fullname: String)
data class CategoryResponse(val status: Boolean, val message: String, val data: List<Category>)
data class Category(val _id: String, val category_name: String, val thumb: Image)
data class Image(val public_id: String, val url: String)
data class ProductResponse(val status: Boolean, val message: String, val data: List<Product>)
data class Product(
    val _id: String,
    val product_name: String,
    val product_price: Number,
    val product_thumb: String
)
data class ProductDetailResponse(val status: Boolean, val message: String, val data: ProductDetail)
data class ProductDetail(
    val _id: String,
    val product_name: String,
    val product_description: String,
    val product_price: Number,
    val product_images: List<Image>,
    val category_id: String,
    val stock: Number,
    val model: String
)
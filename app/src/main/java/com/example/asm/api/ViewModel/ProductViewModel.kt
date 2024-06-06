package com.example.asm.api.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asm.api.ProductDetail
import com.example.asm.api.ProductDetailResponse
import com.example.asm.api.ProductResponse
import com.example.asm.api.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = mutableStateOf<ProductResponse?>(null)
    val products: State<ProductResponse?> get() = _products
    private val _productDetail = mutableStateOf<ProductDetailResponse?>(null)
    val productDetail: State<ProductDetailResponse?> get() = _productDetail

    fun getProductByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductsByCategory(category_id = category)
                _products.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDetailProduct(_id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductById(_id)
                _productDetail.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
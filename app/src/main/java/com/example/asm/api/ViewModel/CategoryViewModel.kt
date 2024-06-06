package com.example.asm.api.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asm.api.Category
import com.example.asm.api.CategoryResponse
import com.example.asm.api.RetrofitInstance
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private var _categories = mutableStateOf<CategoryResponse?>(null)
    val categories: State<CategoryResponse?> get() = _categories

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.category()
                _categories.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.example.wordpressclient.repository

import com.example.wordpressclient.data.model.*
import com.example.wordpressclient.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordPressRepository {
    
    private val api = RetrofitInstance.api
    
    suspend fun getPosts(
        perPage: Int = 10,
        page: Int = 1,
        search: String? = null,
        categories: String? = null,
        tags: String? = null,
        orderBy: String = "date",
        order: String = "desc"
    ): Result<List<WpPost>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getPosts(perPage, page, search, categories, tags, orderBy, order)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch posts: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPost(id: Int): Result<WpPost> = withContext(Dispatchers.IO) {
        try {
            val response = api.getPost(id)
            if (response.isSuccessful) {
                response.body()?.let { post ->
                    Result.success(post)
                } ?: Result.failure(Exception("Post not found"))
            } else {
                Result.failure(Exception("Failed to fetch post: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUsers(perPage: Int = 10, page: Int = 1): Result<List<WpUser>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getUsers(perPage, page)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch users: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUser(id: Int): Result<WpUser> = withContext(Dispatchers.IO) {
        try {
            val response = api.getUser(id)
            if (response.isSuccessful) {
                response.body()?.let { user ->
                    Result.success(user)
                } ?: Result.failure(Exception("User not found"))
            } else {
                Result.failure(Exception("Failed to fetch user: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCategories(perPage: Int = 10, page: Int = 1): Result<List<WpCategory>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCategories(perPage, page)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch categories: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCategory(id: Int): Result<WpCategory> = withContext(Dispatchers.IO) {
        try {
            val response = api.getCategory(id)
            if (response.isSuccessful) {
                response.body()?.let { category ->
                    Result.success(category)
                } ?: Result.failure(Exception("Category not found"))
            } else {
                Result.failure(Exception("Failed to fetch category: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getMedia(perPage: Int = 10, page: Int = 1): Result<List<WpMedia>> = withContext(Dispatchers.IO) {
        try {
            val response = api.getMedia(perPage, page)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch media: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getMediaById(id: Int): Result<WpMedia> = withContext(Dispatchers.IO) {
        try {
            val response = api.getMediaById(id)
            if (response.isSuccessful) {
                response.body()?.let { media ->
                    Result.success(media)
                } ?: Result.failure(Exception("Media not found"))
            } else {
                Result.failure(Exception("Failed to fetch media: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

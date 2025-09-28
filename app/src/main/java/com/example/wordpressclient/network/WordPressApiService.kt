package com.example.wordpressclient.network

import com.example.wordpressclient.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordPressApiService {
    
    @GET("posts")
    suspend fun getPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("search") search: String? = null,
        @Query("categories") categories: String? = null,
        @Query("tags") tags: String? = null,
        @Query("orderby") orderBy: String = "date",
        @Query("order") order: String = "desc"
    ): Response<List<WpPost>>
    
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<WpPost>
    
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Response<List<WpUser>>
    
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<WpUser>
    
    @GET("categories")
    suspend fun getCategories(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Response<List<WpCategory>>
    
    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") id: Int): Response<WpCategory>
    
    @GET("media")
    suspend fun getMedia(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Response<List<WpMedia>>
    
    @GET("media/{id}")
    suspend fun getMediaById(@Path("id") id: Int): Response<WpMedia>
}

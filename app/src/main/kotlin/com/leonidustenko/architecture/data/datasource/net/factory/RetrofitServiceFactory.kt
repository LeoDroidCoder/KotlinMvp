package com.leonidustenko.architecture.data.datasource.net.factory

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.leonidustenko.architecture.data.datasource.net.RetrofitService
import com.leonidustenko.architecture.data.datasource.net.exception.NetworkConnectionException
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class RetrofitServiceFactory(
        url: String,
        gson: Gson,
        private val context: Context
) {

    private val retrofit: Retrofit

    init {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val cachingInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()

            val originalResponse = it.proceed(requestBuilder.build())
            if (isOnline())
                originalResponse
            else {
                //todo
                val maxStale = 60 * 60 * 24 * 7
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
            }
        }

        val interc = Interceptor {
            if (isOnline()) {
                it.proceed(it.request())
            } else {
                throw NetworkConnectionException()

            }
        }

        // Init or retain cache dir
        val httpCacheDir = File(context.cacheDir, "responses")
        val cacheFileSize = 20 * 1024L * 1024L

        // Create okhttp3 cache
        val cache = Cache(httpCacheDir, cacheFileSize)

        // Create client
        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor (interc)
                .addNetworkInterceptor(cachingInterceptor)
                .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build()
    }

    fun create() = retrofit.create(RetrofitService::class.java)

    private fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return cm?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}

package com.munki.paging.util.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonIOException
import com.munki.paging.BuildConfig
import com.munki.paging.util.common.LogUtil
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit2 Create Util
 * @author 나비이쁜이
 * @since 2021.03.09
 */
object RetroUtil {

    /**
     * Timeout
     */
    private const val RETROFIT_TIMEOUT = 15

    /**
     * OkHttp & Retrofit2 기본 설정
     */
    @JvmStatic
    fun <element> createService(requestApi: String, service: Class<element>): element {
        LogUtil.i("=========================")
        LogUtil.i("requestApi : [$requestApi]")
        LogUtil.i("=========================")

        /**
         * okHttp3 LoggingInterceptor
         */
        val jsonLogger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger { message ->
            try {
                // message가 Json형식이 맞는지 확인하기 위한 문구 -> Json이 아니라면 catch로 넘어감
                JSONObject(message)

                // message를 Json형식으로 맞추어서 보내줌
                Logger.json(message)
            } catch (e: JsonIOException) {
                Logger.d(message)
            } catch (e: JSONException) {
                Logger.d(message)
            }
        }

        val interceptor = HttpLoggingInterceptor(jsonLogger)
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        /**
         * OkHttp3 Bulider
         */
        val okHttpClient: OkHttpClient

        if (BuildConfig.DEBUG) {
            okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
                    chain.proceed(request)
                })
                .addInterceptor(interceptor)                                            // okHttp3 LoggingInterceptor
                .connectTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)            // 기본 연결 제한 시간
                .readTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)               // 쓰기 연결 제한 시간
                .writeTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)              // 읽기 연결 제한 시간
                .build()
        } else {
            okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
                    chain.proceed(request)
                })
                .connectTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)            // 기본 연결 제한 시간
                .readTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)               // 쓰기 연결 제한 시간
                .writeTimeout(RETROFIT_TIMEOUT.toLong(), TimeUnit.SECONDS)              // 읽기 연결 제한 시간
                .build()
        }

        /**
         * Retrofit2 Builder
         */
        val retrofit = Retrofit.Builder()
            .baseUrl(requestApi)                                                        // RequestApi
            .client(okHttpClient)                                                       // OkHttpClient
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())                  // RxJava + Retrofit2
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))   // Gson Create
            .build()

        return retrofit.create(service)
    }
}
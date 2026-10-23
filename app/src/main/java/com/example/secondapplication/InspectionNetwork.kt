package com.example.secondapplication

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

// 声明为 private，表示仅在当前文件可见
private val client = OkHttpClient.Builder()
    .connectTimeout(8, TimeUnit.SECONDS)
    .readTimeout(8, TimeUnit.SECONDS)
    .build()

// 注意：这里不要加 private，任务5受控排错要用
fun fetch(address: String): String = try {
    val request = Request.Builder().url(address).build()
    client.newCall(request).execute().use { response ->
        "地址: $address\nHTTP 状态码: ${response.code}\n\n响应内容:\n${response.body?.string().orEmpty().take(800)}"
    }
} catch (e: Exception) {
    "请求失败：${e.javaClass.simpleName}\n${e.message}"
}
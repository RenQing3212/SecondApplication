package com.example.secondapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // 状态管理
                    var resultText by remember { mutableStateOf("等待发起请求...") }
                    var isBusy by remember { mutableStateOf(false) }
                    val scope = rememberCoroutineScope()

                    InspectionScreen(
                        inspector = "申倩", // ⚠️ 这里改成你的真实姓名
                        room = "A203",
                        total = 10,//不知道为什么，一直提交不了
                        checked = 5, // 任务5会让你改成5测试
                        result = resultText,
                        busy = isBusy,
                        onRequest = { targetRoom ->
                            isBusy = true
                            resultText = "正在请求 $targetRoom ..."

                            // 开启协程在后台线程执行网络请求
                            scope.launch(Dispatchers.IO) {
                                val url = "https://httpbin.org/get?room=$targetRoom"
                                val response = fetch(url)
                                // 切回主线程更新UI
                                withContext(Dispatchers.Main) {
                                    resultText = response
                                    isBusy = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
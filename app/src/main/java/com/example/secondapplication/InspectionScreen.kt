package com.example.secondapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 计算未检查设备
fun remainingDevices(total: Int, checked: Int): Int = total - checked

@Composable
fun InspectionScreen(
    inspector: String,
    room: String,
    total: Int,
    checked: Int,
    result: String,
    busy: Boolean,
    onRequest: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "校园巡检", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "巡检员：$inspector")
        Text(text = "实验室：$room")
        Text(text = "设备总数：$total")
        Text(text = "已检查：$checked")
        Text(text = "未检查：${remainingDevices(total, checked)}")

        Spacer(modifier = Modifier.height(24.dp))

        // 请求当前实验室 (A203)
        Button(
            enabled = !busy,
            onClick = { onRequest(room) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (busy) "请求中..." else "请求 $room")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 请求 A204
        Button(
            enabled = !busy,
            onClick = { onRequest("A204") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (busy) "请求中..." else "请求 A204")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 显示结果
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = result,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
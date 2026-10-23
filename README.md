# 校园巡检 App

## 运行方法
1. 使用 Android Studio 打开本项目。
2. 等待 Gradle 同步完成。
3. 连接模拟器或真机，点击 Run 运行。

## 三个文件职责
- `MainActivity.kt`：系统入口。负责设置 Compose 内容，管理状态，安排网络请求与结果更新。
- `InspectionScreen.kt`：页面显示。负责显示巡检数据、触发按钮回调，以及计算未检查数量。
- `InspectionNetwork.kt`：网络处理。负责创建 OkHttpClient 执行请求，并处理响应和异常。

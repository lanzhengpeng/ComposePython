package com.example.composepythondemo.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current

    // 用 remember 保存 Python 结果，避免重组时重复执行
    var greeting by remember { mutableStateOf("") }
    var sum by remember { mutableStateOf(0) }

    // 在 Compose 启动时初始化 Python 并调用方法
    LaunchedEffect(Unit) {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(context))
        }
        val py = Python.getInstance()
        val pyModule = py.getModule("myscript")   // 对应 myscript.py

        greeting = pyModule.callAttr("greet", "Lan").toString()
        sum = pyModule.callAttr("add", 3, 5).toInt()
    }

    // UI 部分
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "主页屏幕",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = greeting,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "3 + 5 = $sum",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

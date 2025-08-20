// 定义包名，通常对应项目的目录结构
package com.example.composepythondemo.viewmodel

// 导入 Compose 状态管理相关的包
import androidx.compose.runtime.*

// 导入 ViewModel 基类
import androidx.lifecycle.ViewModel

// 定义一个继承自 ViewModel 的类，负责管理 UI 的数据
class MainViewModel : ViewModel() {

    // 定义一个可观察的状态变量 result，用来存储结果字符串
    // mutableStateOf 会让 Compose 观察这个变量的变化，从而刷新界面
    var result by mutableStateOf("")
        // private set 表示 result 变量只能在 ViewModel 内部修改
        private set

    // 定义一个方法，用于更新 result 的值
    fun updateResult(text: String) {
        // 将传入的 text 更新到 result 中，Compose 会自动触发界面刷新
        result = text
    }
}

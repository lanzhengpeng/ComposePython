package com.example.composepythondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.composepythondemo.ui.theme.ComposePythonDemoTheme
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.composepythondemo.ui.navigation.BottomNavItem
import com.example.composepythondemo.ui.navigation.bottomNavItems

// 导入独立的屏幕文件
import com.example.composepythondemo.ui.screen.MainScreen
import com.example.composepythondemo.ui.screen.ChatScreen
import com.example.composepythondemo.ui.screen.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePythonDemoTheme {
                AppWithBottomNav()
            }
        }
    }
}

@Composable
fun AppWithBottomNav() {
    // 创建一个 NavController，用于管理页面导航
    val navController = rememberNavController()

    // Scaffold 提供基本的布局结构（顶部栏、底部栏、内容区等）
    Scaffold(
        // 底部导航栏
        bottomBar = {
            NavigationBar {
                // 获取当前回退栈条目状态，用于判断当前页面
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                // 遍历底部导航项列表，生成每个导航按钮
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        // 设置图标
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        // 设置文字标签
                        label = { Text(item.label) },
                        // 判断是否被选中，高亮显示当前页面
                        selected = currentRoute == item.route,
                        // 点击事件，跳转到对应页面
                        onClick = {
                            navController.navigate(item.route) {
                                // 避免重复入栈同一页面
                                launchSingleTop = true
                                // 尝试恢复之前保存的页面状态
                                restoreState = true
                                // 回退到根页面，同时保存状态
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost 管理页面内容区域，根据 route 显示不同的 Composable
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Main.route,  // 默认起始页面
            modifier = Modifier.padding(innerPadding)     // 避免底部导航栏覆盖内容
        ) {
            // 将每个 route 映射到对应页面 Composable
            composable(BottomNavItem.Main.route) { MainScreen(navController) }
            composable(BottomNavItem.Chat.route) { ChatScreen(navController) }
            composable(BottomNavItem.Settings.route) { SettingsScreen(navController) }
        }
    }
}

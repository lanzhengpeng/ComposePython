// BottomNavItem.kt
package com.example.composepythondemo.ui.navigation

// 导入 Compose 自带的图标库
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 封装底部导航栏每一项的数据
 * @param label 显示在导航栏上的文字
 * @param icon 导航栏对应的图标（ImageVector）
 * @param route 对应的导航路由，用于 NavController 跳转
 */
sealed class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    // 首页导航项
    object Main : BottomNavItem(
        label = "主页",
        icon = Icons.Default.Home,  // 默认 Home 图标
        route = "main"             // 路由名
    )

    // 聊天导航项
    object Chat : BottomNavItem(
        label = "聊天",
        icon = Icons.Default.Person,  // 默认 Person 图标
        route = "chat"
    )

    // 设置导航项
    object Settings : BottomNavItem(
        label = "设置",
        icon = Icons.Default.Settings,  // 默认 Settings 图标
        route = "settings"
    )
}

/**
 * 底部导航栏的所有项集合
 * 遍历这个列表即可生成底部导航栏
 */
val bottomNavItems = listOf(
    BottomNavItem.Main,
    BottomNavItem.Chat,
    BottomNavItem.Settings
)

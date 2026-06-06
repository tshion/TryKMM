package io.github.tshion.devmenus

import androidx.compose.runtime.Composable

/**
 * Compose の起点になっている画面の取得
 *
 * Android: Activity
 * iOS: UIViewController
 */
@Composable
internal expect fun getHostPage(): Any

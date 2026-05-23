package io.github.tshion.devmenus

import kotlin.collections.List

/**
 * 開発メニューの定義データの提供者
 */
public interface DevMenuProvider {

    /**
     * 表示する開発メニューの定義データ
     */
    public val devMenuList: List<DevMenuEntity>
}

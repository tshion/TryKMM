package io.github.tshion.devmenus

/**
 * 開発者メニューの定義データの提供者
 */
public interface DevMenuProvider {

    /**
     * 開発者メニューの定義データの取得
     */
    public fun getDevMenuList(): List<DevMenuSpec>
}

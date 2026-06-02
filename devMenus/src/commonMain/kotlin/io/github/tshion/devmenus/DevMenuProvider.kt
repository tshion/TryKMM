package io.github.tshion.devmenus

/**
 * 開発者メニューの定義データの提供者
 */
public interface DevMenuProvider {

    /**
     * 表示する開発者メニューの定義データ
     */
    public val devMenuList: List<DevMenuSpec>
}

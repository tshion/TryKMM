package io.github.tshion.devmenus

import kotlin.collections.List

/**
 * 開発メニューの定義データ
 */
@ConsistentCopyVisibility
public data class DevMenuEntity private constructor(
    internal val children: List<DevMenuEntity>?,
    internal val description: String?,
    internal val title: String,
    internal val action: (() -> Unit)?,
) {

    public companion object {

        /**
         * タップ可能な開発メニューの生成
         *
         * @param title タイトル
         * @param description 説明 (※省略可)
         * @param action タップ時の挙動
         */
        public fun newAction(
            title: String,
            description: String? = null,
            action: () -> Unit,
        ): DevMenuEntity = DevMenuEntity(
            null,
            description,
            title,
            action,
        )

        /**
         * タップした際、子要素が表示される開発メニューの生成
         *
         * @param title タイトル
         * @param children 子要素
         */
        public fun newGroup(
            title: String,
            vararg children: DevMenuEntity,
        ): DevMenuEntity = DevMenuEntity(
            children.let {
                if (it.isNotEmpty()) {
                    it.asList()
                } else {
                    emptyList()
                }
            },
            null,
            title,
            null,
        )
    }
}

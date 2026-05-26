package io.github.tshion.devmenus

/**
 * 開発メニューの定義データ
 */
public sealed class DevMenuSpec {

    /**
     * タップ可能な開発メニュー
     *
     * @param title タイトル
     * @param description 説明 (※省略可)
     * @param action タップ時の挙動
     */
    public data class Action(
        internal val title: String,
        internal val description: String? = null,
        internal val action: () -> Unit,
    ) : DevMenuSpec()

    /**
     * タップした際、子要素が表示される開発メニュー
     */
    @ConsistentCopyVisibility
    public data class Group private constructor(
        internal val title: String,
        internal val children: List<DevMenuSpec>,
    ) : DevMenuSpec() {

        /**
         * @param title タイトル
         * @param children 子要素
         */
        public constructor(
            title: String,
            vararg children: DevMenuSpec,
        ) : this(
            title,
            children = children.let {
                if (it.isNotEmpty()) {
                    it.asList()
                } else {
                    emptyList()
                }
            },
        )
    }
}

package io.github.tshion.devmenus

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

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
    @OptIn(ExperimentalObjCName::class)
    public data class Action(
        @param:ObjCName("_") internal val title: String,
        @param:ObjCName("_") internal val description: String?,
        @param:ObjCName("_") internal val action: (DevMenuViewContract) -> Unit,
    ) : DevMenuSpec() {

        public constructor(
            @ObjCName("_") title: String,
            @ObjCName("_") action: (DevMenuViewContract) -> Unit,
        ) : this(title, null, action)
    }

    /**
     * タップした際、子要素が表示される開発メニュー
     */
    @OptIn(ExperimentalObjCName::class)
    public data class Group(
        @param:ObjCName("_") internal val title: String,
        @param:ObjCName("_") internal val children: List<DevMenuSpec>,
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

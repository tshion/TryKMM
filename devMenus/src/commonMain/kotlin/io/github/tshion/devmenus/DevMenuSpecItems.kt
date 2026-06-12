package io.github.tshion.devmenus

/**
 * よくある開発者メニューの実装
 */
public expect object DevMenuSpecItems {

    /** ローカルプッシュ通知関連のメニューへ遷移する */
    public fun goLocalPushGroup(): DevMenuSpec

    /** OS 設定画面へ遷移するメニュー */
    public fun goOsSettings(): DevMenuSpec
}

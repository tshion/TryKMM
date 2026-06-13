package io.github.tshion.devmenus

/**
 * よくある開発者メニューの実装
 */
public object DevMenuSpecItems {

    /** ローカルプッシュ通知関連へ遷移するメニュー */
    public val LocalPushGroup: DevMenuSpec = getLocalPushGroup()

    /** OS 設定画面へ遷移するメニュー */
    public val OsSettings: DevMenuSpec = getOsSettings()
}


internal expect fun getLocalPushGroup(): DevMenuSpec

internal expect fun getOsSettings(): DevMenuSpec

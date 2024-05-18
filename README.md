# TryKMM
Kotlin Multiplatform Mobile の試し書き。

## 生成したライブラリの導入方法
1. [TryKMMRepo](https://github.com/tshion/TryKMMRepo) にアクセスする
1. バイナリをローカルにダウンロードする
    * Android -> aar
    * iOS -> xcframework
1. 導入したいプロジェクトに、ダウンロードしたバイナリを手動で配置する

## 参考文献
* [GitHub Actions 内で他の repository に push する | blog.euxn.me](https://blog.euxn.me/entry/2024-02-17-01-push-to-other-repo-in-github-actions/)
    * [GitHub Appsトークン解体新書：GitHub ActionsからPATを駆逐する技術](https://zenn.dev/tmknom/articles/github-apps-token)
* Kotlin Multiplatform
    * [Build final native binaries | Kotlin Documentation](https://kotlinlang.org/docs/multiplatform-build-native-binaries.html)
    * IDE 設定 -> [android - Create another module in Compose Multiplatform Project - Stack Overflow](https://stackoverflow.com/questions/77577651/create-another-module-in-compose-multiplatform-project/78390254#78390254)

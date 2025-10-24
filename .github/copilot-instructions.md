When performing a code review, respond in Japanese.

# Project Overview
下記機能を提供するKotlin Multiplatform ライブラリを開発するためのプロジェクト。

* GitHub リポジトリの検索
* 一定時間ごとに現在時刻が流れるデータソース

ライブラリはGitHub Actions でデプロイされ、組み込み側は下記を使って参照することが出来る。

* Android: Maven
* iOS: CocoaPods


## Folder Structure
* `/.github`: GitHub 関連の設定
* `/.vscode`: Visual Studio Code 関連の設定
* `/androidApp`: trykmmlib を組み込んだAndroid アプリのサンプル実装
* `/gradle`: Gradle 関連の設定
* `/iosApp`: trykmmlib を組み込んだiOS アプリのサンプル実装
* `/scripts`: 開発環境のセットアップなど、作業を簡略化するためのスクリプト実装
* `/trykmmlib`: Kotlin Multiplatform ライブラリの実装


## Libraries and Frameworks
### ライブラリ実装(Kotlin Multiplatform)
#### ビルド環境
* Gradle
* Kotlin Gradle Plugin

#### 依存関係
* Kotlin Coroutines
* Kotlin Serialization
* Kotlin Test
* Ktor

### サンプルアプリ(Android)
#### ビルド環境
* Android Gradle Plugin

#### 依存関係
* AndroidX

### サンプルアプリ(iOS)


## Coding Standards
### 共通
* インスタンスが成立した時点で、意図した整合性が取れていること
    * ただしデータ転送オブジェクトは成立過程が異なるため例外とする
* 外部ライブラリに対して、むやみに拡張メソッドを実装しないこと
* クラス外に公開するものは、なるべく変更不可にすること
* コードを記述した開発者以外の誰かにとって明白でない何かを記述する場合を除き、コメントを使用してはならない。
* 正規表現のパターンを記述する際、定義済み文字クラスではなく、なるばく明示的に範囲を記述すること
* 正規表現を使った検証をする前に、可能であれば文字列長のチェックをすること
* 生成コストが高いインスタンスはなるべく使いまわすこと
* データを設計する際、不変性(immutability) を満たすこと

### ライブラリ実装
* 可視性修飾子を明示すること
    * `internal` はJava から参照できるため `@JvmSynthetic` を付与し、Java から参照できないようにすること
* 公開されている実装には必ずドキュメントコメントを記述すること

### サンプルアプリ(Android)
* Android
    * Fragment でViewBinding を使用する際、必ず`onDestroyView` で参照をクリーンアップすること
    * Fragment を動的に配置する際、`FragmentContainerView` に配置すること
    * 画面間でデータを受渡しする際、シリアライズロジックは`Parcelable` を使用すること
    * プロセス間でデータを受け渡しする際、シリアライズロジックとして`Serializable` を避け、代わりにJSON を使用すること
* Kotlin Coroutines
    * `GlobalScope` を使用しないこと
    * `suspend` で実装した関数は、メインスレッドから安全に呼び出せるようにすること
    * `withContext` で指定する`Dispatchers` は外部注入出来るようにし、テストの確実性を上げること

### サンプルアプリ(iOS)
* Swift Regex を実装する際、パフォーマンスを注意深く確認すること


## UI guidelines
* UI を記述する際、まず重なり方向を地 → 天 の順に記述し、同じ重なり階層の場合は左上 → 右上 → 左下 → 右下 の順に記述すること

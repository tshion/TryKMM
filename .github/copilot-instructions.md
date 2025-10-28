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
* 機能の変更はPull Request を通じて行うこと
    * Pull Request は、レビュー担当者が理解しやすいように、１つの目的を満たす、焦点を絞った小さなものにすること
    * ボーイスカウトの原則に則った改善は、目的がぼやけてしまうため、それ専用のPull Request を作成すること

### 共通
* 一般論
    * インスタンスが成立した時点で、意図した整合性が取れていること
        * ただしデータ転送オブジェクトは成立過程が異なるため例外とする
    * 外部ライブラリに対して、むやみに拡張メソッドを実装しないこと
    * クラス外に公開するものは、なるべく変更不可にすること
    * コードを記述した開発者以外の誰かにとって明白でない何かを記述する場合を除き、コメントを使用しないこと
    * 正規表現のパターンを記述する際、定義済み文字クラスではなく、なるべく明示的に範囲を記述すること
    * 正規表現を使った検証をする前に、可能であれば文字列長のチェックをすること
    * 生成コストが高いインスタンスはなるべく使いまわすこと
    * データを設計する際、不変性(immutability) を満たすこと
    * ハリウッドの原則を厳守すること
    * 必要になるまでインスタンス化しないこと
    * ログ
        * 本番リリースする際、攻撃者に与える情報を最小限にするため、ログ出力しないこと
        * ログ出力する際、機密データを記載しないこと
* Android
    * Android Gradle Plugin バージョンを更新する際、Android Studio やGradle 、JDK 、Kotlin Gradle Plugin との整合性を確認すること
    * Android プロセス間でデータの受け渡しをする際、シリアライズが必要な場合は`Parcelable` を使用すること
    * その他のプロセス間でデータの受け渡しをする際、シリアライズが必要な場合は`Serializable` を避け、代わりにJSON を使用すること
* Kotlin
    * スコープ関数
        * `also`: レシーバー自体に何もせず、他のものに紐付ける等、状態を変えない場合に使用すること
        * `apply`: レシーバーに対し、副作用を与えたい場合に使用すること
        * `let`: コレクションの`map` のように値を変換したい場合に使用すること
        * `run`: エルビス演算子の右辺に処理を割り当てたい場合に使用すること
* Kotlin Coroutines
    * `GlobalScope` を使用しないこと
    * `suspend` で実装した関数は、メインスレッドから安全に呼び出せるようにすること
    * `withContext` で指定するDispatchers は外部注入できるようにし、テストの確実性を上げること

### ライブラリ実装
* Java との相互運用性を考慮すること
    * companion object のメソッドには`@JvmStatic` を付与すること
    * Java からの参照を想定しないもの、例えば`internal` なものには`@JvmSynthetic` を付与すること
    * デフォルト引数を持つ関数、あるいはコンストラクターには`@JvmOverloads` を付与すること
    * トップレベル関数のみ実装されているファイルは、先頭に`@file:JvmName` を付与し、Java から呼び出す際のクラス名を指定すること
* `override` されたものを除き、可視性修飾子を明示すること
* ライブラリ外から参照できる実装には必ずドキュメントコメントを記述すること
* ライブラリ実装を追加する際、Android API に依存しないものであれば純水なJava/Kotlin モジュールとして実装し、パフォーマンスを最適化すること

### サンプルアプリ(iOS)
* Swift Regex を実装する際、パフォーマンスを注意深く確認すること
* 型を明示し、ビルドパフォーマンスを維持すること
* 複雑な式を避け、ビルドパフォーマンスを維持すること


## UI guidelines
### Android View
* Fragment
    * Fragment でViewBinding の参照を保持する際、必ず`onDestroyView` で解放すること
    * Fragment に別のFragment を動的に追加する際、必ず`childFragmentManager` を使用すること
    * Fragment をレイアウトに配置する際、必ず`FragmentContainerView` に配置すること
* UI を記述する際、まず重なり方向を地 → 天 の順に記述し、同じ重なり階層の場合は左上 → 右上 → 左下 → 右下 の順に記述すること
* アプリバー
    * Android Theme で`NoActionBar` を設定し、明示的にアプリバーを配置すること
    * Fragment に配置したアプリバーは`setSupportActionBar` を使用せず、直接`Toolbar` API を使用すること
* カスタムビューのレイアウトを記述する際、親クラスに指定したものが割り当てられる`<merge>` を使用すること
* ビュー階層は`ConstraintLayout` を活用し、なるべくネストを減らし、表示パフォーマンスを維持すること
    * 単純に重ねるだけの場合は`FrameLayout` を使用すること

### Jetpack Compose

### SwiftUI

### UIKit

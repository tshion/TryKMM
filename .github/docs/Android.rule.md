# Android に関するルール
## Coding Standards
* `AndroidManifest.xml` を記述する際、他のAndroid アプリから利用できない`<activity>`, `<provider>`, `<receiver>`, `<service>` は、明示的に`android:exported` 属性を`false` に設定すること
* Android Gradle Plugin バージョンを更新する際、Android Studio やGradle 、JDK 、Kotlin Gradle Plugin との整合性を確認すること
* Android プロセス間でデータの受け渡しをする際、シリアライズが必要な場合は`Parcelable` を使用すること
* その他のプロセス間でデータの受け渡しをする際、シリアライズが必要な場合は`Serializable` を避け、代わりにJSON を使用すること

### ライブラリ
* 可視性修飾子`internal` が指定された関数、プロパティ、フィールド変数には、Java からの利用を防ぐため、`@JvmSynthetic` を付与すること
* 可視性修飾子`public` が指定されていて、かつJava からも利用可能な実装は、下記のルールに従うこと
    * `companion object` 内に定義した`const` 以外のプロパティには`@JvmStatic` を付与すること
    * `companion object` 内に定義した関数には`@JvmStatic` を付与すること
    * `Unit` を返すラムダ式は、`fun interface` を使った名前付きインターフェースに書き換えること
    * デフォルト引数を持つ関数、あるいはコンストラクターには`@JvmOverloads` を付与すること
    * ファイルのトップレベルに関数、あるいはプロパティが含まれる場合は、ファイルの先頭に`@file:JvmName` を付与し、Java から呼び出す際のクラス名を指定すること
* ライブラリモジュールを追加する際、Android API に依存しないものであれば純粋なJava/Kotlin モジュールとして実装し、パフォーマンスを最適化すること


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


## 解説
### `android:exported` 属性
https://developer.android.com/privacy-and-security/risks/android-exported を参照してください。

### Android Gradle Plugin の整合性
Android Gradle Plugin, Gradle, JDK, Kotlin Gradle Plugin にはそれぞれサポート範囲があるため、下記を確認して整合性が取れるバージョンを採用すること。

* https://developer.android.com/build/releases/gradle-plugin
* https://kotlinlang.org/docs/gradle-configure-project.html#apply-the-plugin

### Java との相互運用性
https://developer.android.com/kotlin/interop#kotlin-for-java を参照してください。

### モジュール種別の優先順位
Android Developer に下記の記載があるため、優先順位はJava/Kotlin モジュール、Android モジュールの順とする。

> Android Studio がサポートするモジュールには、次の 3 つの重要なタイプがあります。
>
> * アプリ モジュール: アプリケーションのエントリ ポイントです。ソースコード、リソース、アセット、AndroidManifest.xml を含めることができます。アプリ モジュールの出力は、Android App Bundle（AAB）または Android Application Package（APK）です。
> * ライブラリ モジュール: 内容はアプリ モジュールと同じです。これらは他の Android モジュールで依存関係として使用されています。ライブラリ モジュールの出力は Android Archive（AAR）で、構造的にはアプリ モジュールと同一になりますが、Android Archive（AAR）ファイルにコンパイルされ、後で他のモジュールで依存関係として使用できます。ライブラリ モジュールを使用すると、多くのアプリ モジュールで同じロジックとリソースをカプセル化して再利用できます。
> * Kotlin および Java のライブラリ: Android のリソース、アセット、マニフェスト ファイルは含まれていません。
>
> Android モジュールにはオーバーヘッドが伴うため、できるだけ Kotlin または Java を使用することをおすすめします。
>
> 引用元: https://developer.android.com/topic/modularization/patterns#prefer-kotlin

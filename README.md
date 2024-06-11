# TryKMM
Kotlin Multiplatform Mobile の試し書き。

## 生成したライブラリの導入方法
### Android
1. プロジェクトルートのsettings.gradle.kts に参照先を追記する
    ``` kotlin
    // 省略
    dependencyResolutionManagement {
        repositories {
            // 省略
            maven {
                url = uri("https://maven.pkg.github.com/tshion/TryKMM")
            }
            // 省略
        }
    }
    // 省略
    ```
1. 各モジュールのbuild.gradle.kts に依存関係を追記する
    ``` kotlin
    // 省略
    dependencies {
        // 省略
        implementation("io.github.tshion:trykmmlib-android")
        // 省略
    }
    // 省略
    ```

### iOS
1. 導入したいプロジェクトにCocoaPods を導入する
1. Podfile に依存関係を記述する
    ``` ruby
    source 'https://github.com/tshion/TryKMMRepoPod.git'
    # 省略
    target '???' do
      # 省略
      pod 'TryKMMLib'
      # 省略
    end
    # 省略
    ```



## 参考文献
* [GitHub Actions 内で他の repository に push する | blog.euxn.me](https://blog.euxn.me/entry/2024-02-17-01-push-to-other-repo-in-github-actions/)
    * [GitHub Appsトークン解体新書：GitHub ActionsからPATを駆逐する技術](https://zenn.dev/tmknom/articles/github-apps-token)
* Kotlin Multiplatform
    * [Build final native binaries | Kotlin Documentation](https://kotlinlang.org/docs/multiplatform-build-native-binaries.html)
    * IDE 設定 -> [android - Create another module in Compose Multiplatform Project - Stack Overflow](https://stackoverflow.com/questions/77577651/create-another-module-in-compose-multiplatform-project/78390254#78390254)

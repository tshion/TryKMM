// バージョン情報の更新
//
// 注意事項
// * ".java-version" に記載されているバージョンで実行してください
// ※プロジェクトルートで実行してください

var statusCode = 0;
try {
    // 作業対象ファイルの設定と検証
    final var file = new File("trykmmlib/build.properties");
    if (!file.exists() || !file.isFile()) {
        throw new IOException("%s にファイルが存在しません".formatted(file.getAbsolutePath()));
    }


    // コマンドライン引数の検証
    final var args = System.getProperty("args").toString().split(" ");
    if (args.length != 3) {
        throw new IllegalArgumentException("引数を３つ指定してください");
    }

    final var major = Integer.parseInt(args[0]);
    if (major < 0) {
        throw new IllegalArgumentException("major には正整数を指定してください");
    }

    final var minor = Integer.parseInt(args[1]);
    if (minor < 0 || 99 < minor) {
        throw new IllegalArgumentException("minor には1 ~ 2桁の正整数を指定してください");
    }

    final var patch = Integer.parseInt(args[2]);
    if (patch < 0 || 99 < patch) {
        throw new IllegalArgumentException("patch には1 ~ 2桁の正整数を指定してください");
    }


    // バージョン情報の算出
    final var versionCode = major * 10000 + minor * 100 + patch;
    final var versionName = "%d.%d.%d".formatted(major, minor, patch);


    // ファイル出力
    final var path = file.toPath();
    final var text = Files.readString(path)
        .replaceAll("(version_code=)\\d+", "$1%d".formatted(versionCode))
        .replaceAll("(version_name=)\\d[\\d\\.]{0,}\\d", "$1%s".formatted(versionName))
        ;
    Files.writeString(path, text);


    // 終了表示
    System.out.println("Set code: %d, name: %s".formatted(versionCode, versionName));
} catch (Exception e) {
    statusCode = 1;
    System.out.println(e.getMessage());
}

// jshell のREPL モードの終了
/exit statusCode

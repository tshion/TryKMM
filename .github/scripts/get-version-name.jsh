// バージョン文字列の抽出
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

    // ファイル読み込み
    final var text = Files.readString(file.toPath());

    // アプリバージョン文字列の抽出
    final var matches = Pattern.compile("version_name=(\\d+\\.\\d{1,2}\\.\\d{1,2})").matcher(text);
    matches.find();
    final var versionName = matches.group(1);

    // 終了表示
    System.out.println(versionName);
} catch (Exception e) {
    statusCode = 1;
    System.out.println(e.getMessage());
}

// jshell のREPL モードの終了
/exit statusCode

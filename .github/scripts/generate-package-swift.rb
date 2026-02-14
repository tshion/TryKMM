#!/usr/bin/env ruby

# 指定されたディレクトリーにあるXCFramework からPackage.swift を生成するスクリプト
#
# 引数
# [0] XCFramework が配置されているディレクトリーパス (省略した場合はプロジェクトディレクトリー)
#
# 注意事項
# * ".ruby-version" に記載されているバージョンで実行してください

require 'pathname'


# プロジェクトルートのパス取得
PATH_ROOT = Pathname.new(__dir__).join("../..")

# 操作対象のディレクトリーパス
PATH_TARGET = Pathname.new(PATH_ROOT).join(ARGV[0] || ".")

# XCFramework の一覧取得
SDKS = Dir.glob(Pathname.new(PATH_TARGET).join("*.xcframework"))
    .map { |path| File.basename(path, ".*") }
unless 0 < SDKS.size
    puts("Not Found: #{PATH_TARGET}")
    exit(1)
end

# Package.swift の生成
TEXT = <<-"EOF"
// swift-tools-version: 5.4

import PackageDescription

let package = Package(
    name: "TryKMM",
    products: [
        #{SDKS.map { |sdk| ".library(name: \"#{sdk}\", targets: [\"_#{sdk}\"])" }.join(",\n        ") }
    ],
    targets: [
        #{SDKS.map { |sdk| ".binaryTarget(name: \"_#{sdk}\", path: \"#{sdk}.xcframework\")" }.join(",\n        ") }
    ]
)
EOF
File.write(Pathname.new(PATH_TARGET).join("Package.swift"), TEXT)

# 終了表示
puts("Generated Package.swift")

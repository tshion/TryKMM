#!/usr/bin/env sh

# gem の確認
if ! type "gem" > /dev/null; then
    echo '`gem` not found. Install Ruby'
    exit 1
fi


# Bundler のセットアップ
if ! type "bundle" > /dev/null; then
    echo '`bundle` not found. Install bundler'
    gem install bundler
fi


# Gemfile から依存関係を復元
bundle config set path 'vendor/bundle'
bundle install

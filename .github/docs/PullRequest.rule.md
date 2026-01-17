# Pull Request に関するルール
## Coding Standards
* Keep pull requests small and focused a single purpose to make it easily for reviewers to understand
* Separate improvements based on the Boy Scout principles from a pull request to keep clear context


## 解説
### ボーイスカウトの原則
プログラミングにおけるボーイスカウトの原則は、作業のついでにリファクタリングをして、コードを綺麗に保つことを指します。
一見良いことに思えますが、例えば下記のような問題を引き起こします。

* バグ修正の場合、バグ解消するためのコードを特定しづらくなる
* 大幅なロジック変更を行った場合、綺麗にしたコードがロジックに効果を与えているかの判断をしづらくなる
* 新しいAPI への書き換えの場合、作業範囲以外はそのままのため、統一感が無くなる
    * コードが作業対象になる頻度はバラバラのため、いつまでも古いAPI が残置されてしまう
* 主目的がリジェクトされた場合、マージされるタイミングを失う
* テスト範囲が広くなる

なので、専用のPull Request を作成し、因果関係を明示してください。


## 参考文献
* https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/getting-started/helping-others-review-your-changes

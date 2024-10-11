# プロジェクト名: sample(生徒情報取得)

## 概要
このプロジェクトは、教師の情報などをもとに生徒の情報を取得します

## 必要条件（Prerequisites）
以下のソフトウェアがインストールされていることを確認してください:
- **Java 21** 以上
- **SpringBoot 21** 以上
- **Maven 3.6** 以上
- **Docker**

## 起動方法

1. リポジトリをクローンする
2. リポジトリにディレクトリを移動する
3. docker compose upを実行する

## 現況について
- ドキュメント：ビルド時にmdファイルからhtmlファイルを設計書として出力をさせたく、機構を作りましたが、ドキュメントの準備が追い付いていません。
- アプリケーション：アプリケーションの実装を行い、起動するようにしておりますが、
起動後のアクセスにおいて、クエリが期待通り実行されていません。
また、単体テストを実装中の状況となります。
- データベース：テーブル設計を行い、MySQLが起動するようにしております。
ただ、設計書が追いついておりません。

## ディレクトリ構成
```
D:.
|   .gitignore
|   docker-compose.yml
|   Dockerfile
|   HELP.md
|   mvnw
|   mvnw.cmd
|   pom.xml
|   README.md
|   wait-for-it.sh
|   
+---.mvn
|   \---wrapper
|           maven-wrapper.properties
|           
+---.vscode
|       launch.json
|       settings.json
|       
+---docs # ドキュメント管理フォルダ
|   |   amazon-corretto-21-x64-linux-jdk.tar.gz
|   |   Dockerfile
|   |   
|   \---imageuti
|           er.pu
|           
+---mysql
|   |   Dockerfle
|   |   my.cnf
|   |   
|   \---init
|           init.sql　# SQLファイル
|           
+---src
    +---main
    |   +---java
    |   |   \---com
    |   |       \---example
    |   |           \---demo
    |   |               |   DemoApplication.java　# メインクラス
    |   |               |   
    |   |               +---controller
    |   |               |       StudentController.java　# RESTコントローラー
    |   |               |       
    |   |               +---entity
    |   |               |       StudentInfo.java　# エンティティ
    |   |               |       
    |   |               +---exception
    |   |               |       CustomExceptionHandler.java　# 例外処理
    |   |               |       
    |   |               +---repository　
    |   |               |       StudentRepository.java　# データアクセス層
    |   |               |       
    |   |               \---service
    |   |                       StudentService.java　# ビジネスロジック
    |   |                       
    |   \---resources
    |       |   application.properties　# 外部設定値
    |       |   
    |       +---static
    |       \---templates
    \---test
        \---java
            \---com
                \---example
                    \---demo
                        |   DemoApplicationTests.java
                        |   
                        +---controller
                        |       StudentControllerTest.java　# コントローラーUT
                        |       
                        +---repository
                        |       StudentRepositoryTest.java　# データアクセスUT
                        |       
                        \---service
                                StudentServiceTest.java　# ビジネスロジックUT
                               
``` 
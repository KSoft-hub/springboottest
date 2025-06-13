### ●実行前提    
以下を最低限インストール  
・docker 20.10.14  
・node.js v22.16.0  
・java 17　※intelliJ側のGradle設定も17で動くようにしておく(Gradle実行時にエラーでるのでその時インストールでもOK)  
・intelliJ IDEA　※30日間無料トライアル

### ●言語 F/Wなど  
・Kotlin  
・Spring Boot  
・mysql  
・mybadis

### ●docker立ち上げ  
rootディレクトリに移動し、ターミナルより「docker-compose up」を実行しmysqlをdockerに載せる  
この時点ではmysqlはtest_databaseという箱だけ作成

### ●mysqlにデータ投入  
#### 1.create tableを流すため、以下をコンソールより実行  
`docker-compose exec db bash -c "chmod 0775 docker-entrypoint-initdb.d/init-database.sh"`  
`docker-compose exec db bash -c "./docker-entrypoint-initdb.d/init-database.sh"`  
これでtest_database内にtest_tableというテーブルが出来上がる

desc test_table  
+------------+-------------+------+-----+---------+----------------+  
| Field      | Type        | Null | Key | Default | Extra          |  
+------------+-------------+------+-----+---------+----------------+  
| id         | int(20)     | NO   | PRI | NULL    | auto_increment |  
| name       | varchar(20) | NO   |     | NULL    |                |  
| created_at | datetime    | YES  |     | NULL    |                |  
| updated_at | datetime    | YES  |     | NULL    |                |  
+------------+-------------+------+-----+---------+----------------+

#### 2.dockerのmysql接続およびデータ投入 
// mysqlのdockerに接続  
`docker exec -it mysql_host bash`  
// mysql接続  
`mysql -p　※パスはroot`  
// db接続  
`use test_database`  
// テストデータをinsert  
`insert into test_table (name, created_at, updated_at) value ('hoge1', now(), now());`  
`insert into test_table (name, created_at, updated_at) value ('hoge2', now(), now());`  
// select発行  
`select * from test_table;`  
+----+-------+---------------------+---------------------+  
| id | name  | created_at          | updated_at          |  
+----+-------+---------------------+---------------------+  
|  1 | hoge1 | 2025-06-13 21:14:51 | 2025-06-13 21:14:51 |  
|  2 | hoge2 | 2025-06-13 21:15:07 | 2025-06-13 21:15:07 |  
+----+-------+---------------------+---------------------+  


#### ●サーバサイドの立ち上げ  
「com.hf.springboottest.SpringboottestApplication」で実行  
or  
intelliJから、右のGradle>Tasks>application>bootRunから実行

http://localhost:8080  
indexという文字だけが出ること

http://localhost:8080/api/testTables  
以下jsonが取れること
`[{"id":1,"name":"hoge1","createdAt":"2025-06-13T12:14:51Z","updatedAt":"2025-06-13T12:14:51Z"},{"id":2,"name":"hoge2","createdAt":"2025-06-13T12:15:07Z","updatedAt":"2025-06-13T12:15:07Z"}]`
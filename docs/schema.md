## Databaseへのアクセス
```
docker compose up
docker exec -it book-manager-web-db-1 bash
psql -U user -d book_manager
```
### カラムの確認
```
\d テーブル名

booksの場合は`\d books`
```

## テーブル作成
### booksテーブル
```
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  purchase_date DATE,
  created_at TIMESTAMP  DEFAULT CURRENT_TIMESTAMP
 );
 ```
- id: 登録された書籍のID
- title: 書籍タイトル
- purchase_date: 購入日
- レコード作成日時

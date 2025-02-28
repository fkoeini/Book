package bookrecom;

import org.neo4j.driver.*;

public class Neo4jConnection {
    private Driver driver;
    private Session session;

    public void connect() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
        session = driver.session();
    }

    public void close() {
        session.close();
        driver.close();
    }

    public void addBook(Book book) {
        String query = "CREATE (b:Book {title: $title, author: $author})";
        session.writeTransaction(tx -> tx.run(query, 
                parameters("title", book.getTitle(), "author", book.getAuthor())));
    }

    public void recommendBooks(String bookTitle) {
        String query = "MATCH (b:Book {title: $title})-[:SIMILAR_TO]->(recommendedBooks) RETURN recommendedBooks.title AS recommendedTitle";
        Result result = session.run(query, parameters("title", bookTitle));

        System.out.println("Recommended Books for: " + bookTitle);
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("recommendedTitle").asString());
        }
    }
}

public void addBook(Book book) {
    String query = "CREATE (b:Book {title: $title, author: $author, genre: $genre})";
    session.writeTransaction(tx -> tx.run(query, 
            parameters("title", book.getTitle(), "author", book.getAuthor(), "genre", book.getGenre())));
}
public void recommendBooks(String bookTitle) {
    String query = "MATCH (b:Book {title: $title})-[:SIMILAR_TO]->(recommendedBooks) " +
                   "RETURN recommendedBooks.title AS recommendedTitle " +
                   "UNION " +
                   "MATCH (b:Book {title: $title})-[:HAS_GENRE]->(g:Genre)<-[:HAS_GENRE]-(rec:Book) " +
                   "RETURN rec.title AS recommendedTitle";
    Result result = session.run(query, parameters("title", bookTitle));

    System.out.println("Recommended Books for: " + bookTitle);
    while (result.hasNext()) {
        Record record = result.next();
        System.out.println(record.get("recommendedTitle").asString());
    }
}

public void addGenre(String bookTitle, String genre) {
    String query = "MATCH (b:Book {title: $title}) " +
                   "MERGE (g:Genre {name: $genre}) " +
                   "MERGE (b)-[:HAS_GENRE]->(g)";
    session.writeTransaction(tx -> tx.run(query, parameters("title", bookTitle, "genre", genre)));
}



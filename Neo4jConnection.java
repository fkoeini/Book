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

public void addSimilarity(String title1, String title2) {
    String query = "MATCH (b1:Book {title: $title1}), (b2:Book {title: $title2}) " +
                   "MERGE (b1)-[:SIMILAR_TO]->(b2)";
    session.writeTransaction(tx -> tx.run(query, parameters("title1", title1, "title2", title2)));
}

            connection.addSimilarity("1984", "Brave New World");
connection.addSimilarity("1984", "Fahrenheit 451");
connection.addSimilarity("Brave New World", "Fahrenheit 451");


            
        }
    }
}


package bookrecom;

public class Main {
    public static void main(String[] args) {
        Neo4jConnection connection = new Neo4jConnection();
        connection.connect();

        // اضافه کردن کتاب‌ها
        Book book1 = new Book("1984", "George Orwell", "Dystopian");
        Book book2 = new Book("Brave New World", "Aldous Huxley", "Dystopian");
        Book book3 = new Book("Fahrenheit 451", "Ray Bradbury", "Dystopian");
        Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", "Classic");

        connection.addBook(book1);
        connection.addBook(book2);
        connection.addBook(book3);
        connection.addBook(book4);

        // اضافه کردن ژانرها
        connection.addGenre("1984", "Dystopian");
        connection.addGenre("Brave New World", "Dystopian");
        connection.addGenre("Fahrenheit 451", "Dystopian");
        connection.addGenre("To Kill a Mockingbird", "Classic");

        // دریافت توصیه برای کتاب 1984
        connection.recommendBooks("1984");

        connection.close();
    }
}

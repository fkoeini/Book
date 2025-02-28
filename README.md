# Book Recommendation System

This repository contains a **Book Recommendation System** built with **Java** and **Neo4j**. The system provides book recommendations based on user preferences and relationships between books.

## Features
- Manage book information using **Book.java**
- Recommend books based on user interests (**BookRecommender.java**)
- Connect to **Neo4j**, a graph database, to store and retrieve book relationships (**Neo4jConnection.java**)

## Prerequisites
- Java 8 or later
- Neo4j installed and running
- A valid database connection configured

## Installation & Usage
1. Clone the repository:
   ```sh
   git clone https://github.com/fkoeini/Book.git
   cd Book
   ```
2. Set up **Neo4jConnection.java** with your database credentials.
3. Compile and run the project:
   ```sh
   javac *.java
   java MainClassName  # Replace with the main class
   ```

## Contribution
Feel free to submit **issues** or **pull requests** to improve the project.

## License
This project is licensed under the MIT License.


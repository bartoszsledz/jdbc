package com.edu;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Bartosz Śledź
 */
public class JDBCUtil {

    private final Connection connection;
    public static final String ALL_TABLES = "Wszystkie";

    public JDBCUtil(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create structure and insert example data.
     *
     * @throws SQLException
     */
    public void create() throws SQLException {
        final Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE users (id INT UNSIGNED AUTO_INCREMENT NOT NULL, roles JSON NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(255) NOT NULL, public_id BIGINT UNSIGNED NOT NULL, created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, modified DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, UNIQUE INDEX UNIQ_1483A5E9E7927C74 (email), UNIQUE INDEX UNIQ_1483A5E9B5B48B91 (public_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci ENGINE = InnoDB;");
        statement.execute("CREATE TABLE borrow (id INT UNSIGNED AUTO_INCREMENT NOT NULL, user_id INT UNSIGNED DEFAULT NULL, book_id INT UNSIGNED DEFAULT NULL, public_id BIGINT UNSIGNED NOT NULL, created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, modified DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, UNIQUE INDEX UNIQ_55DBA8B0B5B48B91 (public_id), INDEX IDX_55DBA8B0A76ED395 (user_id), INDEX IDX_55DBA8B016A2B381 (book_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci ENGINE = InnoDB;");
        statement.execute("CREATE TABLE history (id INT UNSIGNED AUTO_INCREMENT NOT NULL, user_id INT UNSIGNED DEFAULT NULL, book_id INT UNSIGNED DEFAULT NULL, date_borrow DATETIME NOT NULL, date_return DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, public_id BIGINT UNSIGNED NOT NULL, created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, modified DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, UNIQUE INDEX UNIQ_27BA704BB5B48B91 (public_id), INDEX IDX_27BA704BA76ED395 (user_id), INDEX IDX_27BA704B16A2B381 (book_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci ENGINE = InnoDB;");
        statement.execute("CREATE TABLE book (id INT UNSIGNED AUTO_INCREMENT NOT NULL, isbn BIGINT UNSIGNED NOT NULL, title VARCHAR(64) NOT NULL, author VARCHAR(64) NOT NULL, description LONGTEXT NOT NULL, available TINYINT(1) NOT NULL, public_id BIGINT UNSIGNED NOT NULL, created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, modified DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, UNIQUE INDEX UNIQ_CBE5A3312B36786B (title), UNIQUE INDEX UNIQ_CBE5A331B5B48B91 (public_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci ENGINE = InnoDB;");
        statement.execute("CREATE TABLE reviews (id INT UNSIGNED AUTO_INCREMENT NOT NULL, book_id INT UNSIGNED DEFAULT NULL, user_id INT UNSIGNED DEFAULT NULL, comment LONGTEXT NOT NULL, stars INT UNSIGNED NOT NULL, public_id BIGINT UNSIGNED NOT NULL, created DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, modified DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, UNIQUE INDEX UNIQ_6970EB0FB5B48B91 (public_id), INDEX IDX_6970EB0F16A2B381 (book_id), INDEX IDX_6970EB0FA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci ENGINE = InnoDB;");
        statement.execute("ALTER TABLE borrow ADD CONSTRAINT user_borrow FOREIGN KEY (user_id) REFERENCES users (id);");
        statement.execute("ALTER TABLE borrow ADD CONSTRAINT book_borrow FOREIGN KEY (book_id) REFERENCES book (id);");
        statement.execute("ALTER TABLE history ADD CONSTRAINT user_history FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;");
        statement.execute("ALTER TABLE history ADD CONSTRAINT book_history FOREIGN KEY (book_id) REFERENCES book (id);");
        statement.execute("ALTER TABLE reviews ADD CONSTRAINT book_review FOREIGN KEY (book_id) REFERENCES book (id);");
        statement.execute("ALTER TABLE reviews ADD CONSTRAINT user_review FOREIGN KEY (user_id) REFERENCES users (id)");

        statement.execute("INSERT INTO `users` (`roles`, `email`, `password`, `public_id`, `created`, `modified`) VALUES ('[\"ROLE_USER\"]', 'user1@wp.pl', '$2y$12$K/Z6PlwPPGkVPyg5sqZgBOK2nzLgwcqXbmUOi4LUkiM42EDy4Tg.K', 323537544628, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");
        statement.execute("INSERT INTO `book` (`isbn`, `title`, `author`, `description`, `available`, `public_id`, `created`, `modified`) VALUES (9780135974445, 'Agile Software Development: Principles, Patterns, and Practices.', 'Uncle Bob', 'Written by a software developer for software developers, this book is a unique collection of the latest software development methods. The author includes OOD, UML, Design Patterns, Agile and XP methods with a detailed description of a complete software design for reusable programs in C++ and Java. Using a practical, problem-solving approach, it shows how to develop an object-oriented application—from the early stages of analysis, through the low-level design and into the implementation. Walks readers through the designers thoughts — showing the errors, blind alleys, and creative insights that occur throughout the software design process. The book covers: Statics and Dynamics; Principles of Class Design; Complexity Management; Principles of Package Design; Analysis and Design; Patterns and Paradigm Crossings.', 1, 4023848240, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");
        statement.execute("INSERT INTO `book` (`isbn`, `title`, `author`, `description`, `available`, `public_id`, `created`, `modified`) VALUES (9780132350884, 'Clean Code: A Handbook of Agile Software Craftsmanship.', 'Uncle Bob', 'Clean Code is divided into three parts. The first describes the principles, patterns, and practices of writing clean code. The second part consists of several case studies of increasing complexity. Each case study is an exercise in cleaning up code—of transforming a code base that has some problems into one that is sound and efficient. The third part is the payoff: a single chapter containing a list of heuristics and “smells” gathered while creating the case studies. The result is a knowledge base that describes the way we think when we write, read, and clean code.', 0, 3625848197, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");
        statement.execute("INSERT INTO `history` (`user_id`, `book_id`, `date_borrow`, `date_return`, `public_id`, `created`, `modified`) VALUES (1, 2, '2018-12-08 22:34:20', '2018-12-08 22:34:20', 9101965860712, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");
        statement.execute("INSERT INTO `borrow` (`user_id`, `book_id`, `public_id`, `created`, `modified`) VALUES (1, 1, 57490850558, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");
        statement.execute("INSERT INTO `reviews` (`book_id`, `user_id`, `comment`, `stars`, `public_id`, `created`, `modified`) VALUES (1, 1, 'Great book!', 3, 20583613361128, '2018-12-08 22:34:20', '2018-12-08 22:34:20');");

        System.out.println("Done\n");
    }

    /**
     * Show db structure.
     *
     * @throws SQLException
     */
    public void ShowStructure() throws SQLException {
        final DatabaseMetaData databaseMetaData = connection.getMetaData();
        final ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

        System.out.println("Printing TABLE_TYPE \"TABLE\" ");
        System.out.println("---------------");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
        System.out.println("---------------\n");
    }

    /**
     * Get all tables from db.
     *
     * @param types
     * @param trueTables
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getTables(final String[] types, final boolean trueTables) throws SQLException {
        final DatabaseMetaData databaseMetaData = connection.getMetaData();
        final ArrayList<String> tableList = new ArrayList<>();

        if (!trueTables) {
            tableList.add(ALL_TABLES);
        }

        final ResultSet resultSet = databaseMetaData.getTables(null, null, null, types);
        while (resultSet.next()) {
            tableList.add(resultSet.getString("TABLE_NAME"));
        }

        return tableList;
    }

    /**
     * @param tableName
     * @throws SQLException
     */
    public void show(final String tableName) throws SQLException {
        final DatabaseMetaData databaseMetaData = connection.getMetaData();

        ShowStructure();
        if (tableName.equals(ALL_TABLES)) {
            final ArrayList<String> tableList = getTables(new String[]{"TABLE"}, true);
            for (String table : tableList) {
                printStructure(table, databaseMetaData.getColumns(null, null, table, null));
                printData(table);
            }
        } else {
            printStructure(tableName, databaseMetaData.getColumns(null, null, tableName, null));
            printData(tableName);
        }
    }

    /**
     * Print data from table.
     *
     * @param tableName
     * @throws SQLException
     */
    private void printData(final String tableName) throws SQLException {
        final Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final ResultSet resultSet = statement.executeQuery("select * from " + tableName);
        final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        final int columnsNumber = resultSetMetaData.getColumnCount();
        final String format = "|%1$-20s|%2$-8s\n";

        while (resultSet.next()) {
            System.out.format(format, "Column Name", "Value");
            for (int i = 1; i <= columnsNumber; i++) {
                final String columnValue = resultSet.getString(i);
                System.out.format(format, resultSetMetaData.getColumnName(i), columnValue);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Drop db structure.
     *
     * @throws SQLException
     */
    public void drop() throws SQLException {
        final Statement stmt = connection.createStatement();

        final ArrayList<String> tableList = getTables(new String[]{"TABLE"}, true);
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
        for (String table : tableList) {
            stmt.executeUpdate(String.format("DROP TABLE IF EXISTS `%s`;", table));
        }
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");

        System.out.println("Done\n");
    }

    /**
     * Print structure of table.
     *
     * @param tableName
     * @param columns
     * @throws SQLException
     */
    private void printStructure(final String tableName, final ResultSet columns) throws SQLException {
        final String format = "|%1$-20s|%2$-8s|%3$-10s|%4$-8s|%5$-10s|\n";
        System.out.println(tableName);
        System.out.format(format, "Column Name", "Datatype", "Column Size", "Nullable", "AutoIncrement");
        while (columns.next()) {
            final String columnName = columns.getString("COLUMN_NAME");
            final String datatype = columns.getString("TYPE_NAME");
            final String columnSize = columns.getString("COLUMN_SIZE");
            final String nullable = columns.getString("IS_NULLABLE");
            final String autoIncrement = columns.getString("IS_AUTOINCREMENT");

            System.out.format(format, columnName, datatype, columnSize, nullable, autoIncrement);
        }
        System.out.println("\n");
    }

}
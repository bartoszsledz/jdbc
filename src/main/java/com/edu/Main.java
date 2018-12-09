package com.edu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * @author Bartosz Śledź
 */
public class Main {

    private JDBCUtil jdbcUtil;
    private Connection connection;

    private void start() {
        while (true) {

            showMenu();

            final Scanner input = new Scanner(System.in);
            int option;
            try {
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                out.println("Invalid input.");
                continue;
            }

            try {

                final MySQLConnector connector = new MySQLConnector();
                connection = connector.getConnection();
                jdbcUtil = new JDBCUtil(connection);

                switch (option) {
                    case 1:
                        jdbcUtil.create();
                        break;
                    case 2:
                        final ArrayList<String> tableList = jdbcUtil.getTables(new String[]{"TABLE"}, false);
                        for (int i = 0; i < tableList.size(); i++) {
                            out.println(String.format("%d. %s", i, tableList.get(i)));
                        }

                        out.println("Get data for: ");
                        final Scanner scanner = new Scanner(System.in);
                        int number = -1;

                        try {
                            number = Integer.parseInt(scanner.nextLine());
                        } catch (Exception ex) {
                            out.println("Exception:" + ex.getMessage());
                        }

                        if (number < 0 || number >= tableList.size()) {
                            out.println("Invalid input.");
                            continue;
                        }

                        jdbcUtil.show(tableList.get(number));
                        break;
                    case 3:
                        jdbcUtil.drop();
                        break;
                    case 4:
                        System.exit(1);
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Exception while executing statement. " + e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(connection).close();
                } catch (SQLException ignored) {
                }
            }

        }
    }

    /**
     * Shows menu.
     */
    private void showMenu() {
        out.println("1. Create structure");
        out.println("2. Show structure");
        out.println("3. Delete structure");
        out.println("4. Exit");
    }

    public static void main(final String[] args) {
        final Main main = new Main();
        main.start();
    }
}

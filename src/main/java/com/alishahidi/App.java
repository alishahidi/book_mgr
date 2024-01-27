package com.alishahidi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.alishahidi.core.Cli;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Cli cli = context.getBean(Cli.class);

        System.out.println();
        cli.printCopyright();

        // For add some sample to test application uncomment this line
        // cli.addSamples();

        while (true) {
            cli.displayMenu();
            int choice = cli.getUserChoice();
            System.out.println("************************[Stage Changed]************************");
            switch (choice) {
                case 1 -> cli.addBook();
                case 2 -> cli.removeBook();
                case 3 -> cli.searchAllByRating();
                case 4 -> cli.searchAllByName();
                case 5 -> cli.displayNumberOfBooks();
                case 6 -> cli.printBookDetails();
                case 7 -> cli.printAllBooks();
                case 8 -> cli.exit();
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

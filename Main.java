import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Add Member");
            System.out.println("7. View Members");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(id, title, author));
                }
                case 2 -> lib.viewBooks();
                case 3 -> {
                    System.out.print("Enter Book ID to search: ");
                    String id = sc.nextLine();
                    Book b = lib.searchBook(id);
                    System.out.println(b != null ? b : "Book not found!");
                }
                case 4 -> {
                    System.out.print("Enter Book ID to issue: ");
                    lib.issueBook(sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter Book ID to return: ");
                    lib.returnBook(sc.nextLine());
                }
                case 6 -> {
                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();
                    lib.addMember(new Member(id, name));
                }
                case 7 -> lib.viewMembers();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}

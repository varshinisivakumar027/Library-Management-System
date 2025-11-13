import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private final String bookFile = "books.dat";
    private final String memberFile = "members.dat";

    @SuppressWarnings("unchecked")
    public Library() {
        books = (List<Book>) loadData(bookFile);
        members = (List<Member>) loadData(memberFile);
        if (books == null) books = new ArrayList<>();
        if (members == null) members = new ArrayList<>();
    }

    private void saveData(List<?> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private Object loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    // ---------- Book operations ----------
    public void addBook(Book b) {
        books.add(b);
        saveData(books, bookFile);
        System.out.println("Book added successfully!");
    }

    public void viewBooks() {
        if (books.isEmpty()) System.out.println("No books found!");
        else books.forEach(System.out::println);
    }

    public Book searchBook(String id) {
        for (Book b : books)
            if (b.getId().equalsIgnoreCase(id)) return b;
        return null;
    }

    public void issueBook(String id) {
        Book b = searchBook(id);
        if (b == null) System.out.println("Book not found!");
        else if (b.isIssued()) System.out.println("Book already issued!");
        else {
            b.issue();
            saveData(books, bookFile);
            System.out.println("Book issued successfully!");
        }
    }

    public void returnBook(String id) {
        Book b = searchBook(id);
        if (b == null) System.out.println("Book not found!");
        else if (!b.isIssued()) System.out.println("Book was not issued!");
        else {
            b.returnBook();
            saveData(books, bookFile);
            System.out.println("Book returned successfully!");
        }
    }

    // ---------- Member operations ----------
    public void addMember(Member m) {
        members.add(m);
        saveData(members, memberFile);
        System.out.println("Member added successfully!");
    }

    public void viewMembers() {
        if (members.isEmpty()) System.out.println("No members found!");
        else members.forEach(System.out::println);
    }
}

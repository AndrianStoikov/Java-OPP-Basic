
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int numberOfBooks = Integer.parseInt(reader.readLine());

            List<Book> bookList = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            for (int i = 0; i < numberOfBooks; i++) {
                String[] bookInformation = reader.readLine().split(" ");

                String title = bookInformation[0];
                String author = bookInformation[1];
                String publisher = bookInformation[2];
                LocalDate releaseDate = LocalDate.parse(bookInformation[3], formatter);
                String isbn = bookInformation[4];
                Double price = Double.parseDouble(bookInformation[5]);
                Book book = new Book(title, author, publisher, releaseDate, isbn, price);
                bookList.add(book);
            }

            Map<String, Double> map = new LinkedHashMap<String, Double>();
            for (int i = 0; i < bookList.size(); i++) {
                if(map.containsKey(bookList.get(i).author)){
                    double price = map.get(bookList.get(i).author);
                    double priceToAdd = bookList.get(i).price;
                    map.put(bookList.get(i).author, price+priceToAdd);
                } else {
                    double priceToAdd = bookList.get(i).price;
                    map.put(bookList.get(i).author, priceToAdd);
                }
            }

            Map<String, Double> treeMap = new TreeMap<String, Double>(map);


            entriesSortedByValues(treeMap);
        } catch (IOException ex) {
            ex.getStackTrace();
        }

    }


    static <K,V extends Comparable<? super V>>
    List<Map.Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

        List<Map.Entry<K,V>> sortedEntries = new ArrayList<Map.Entry<K,V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K,V>>() {
                    @Override
                    public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );
        for (int i = 0; i < sortedEntries.size(); i++) {
            System.out.print(sortedEntries.get(i).getKey() + " -> ");
            System.out.println(sortedEntries.get(i).getValue());
        }

        return sortedEntries;
    }
}

class Book {
    String title;
    String author;
    String publisher;
    LocalDate releaseDate;
    String isbn;
    Double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book(String title, String author, String publisher, LocalDate releaseDate, String isbn, Double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.isbn = isbn;
        this.price = price;
    }
}

class BookLibrary {
    public String name;
    public List<Book> books;

    public BookLibrary(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookLibrary{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}




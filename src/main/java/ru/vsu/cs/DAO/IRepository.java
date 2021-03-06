package ru.vsu.cs.DAO;

import ru.vsu.cs.Domain.*;

import javax.transaction.NotSupportedException;
import java.util.List;

public interface IRepository {
    List<Book> getBooks();
    List<Magazine> getMagazines();
    List<Newspaper> getNewspapers();
    List<Paper> getAllProducts();
    Paper getPaper(final int id);
    int addProduct(Paper paper);
    void editProductBD(Paper paper);
    void removeFromBD(Paper paper);
    void pickUpProductBD(Paper paper);
}
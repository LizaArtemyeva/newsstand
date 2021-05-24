//import org.junit.jupiter.api.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import ru.vsu.cs.Domain.*;
import ru.vsu.cs.config.Config;
import ru.vsu.cs.controller.BookController;
import ru.vsu.cs.controller.MagazineController;
import ru.vsu.cs.controller.NewspaperController;
import ru.vsu.cs.controller.ProductsController;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = Config.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsTest {
    @Autowired
    private ProductsController productsController;
    @Autowired
    private BookController bookController;
    @Autowired
    private MagazineController magazineController;
    @Autowired
    private NewspaperController newspaperController;
    private final Book book = new Book("Atlas Shrugged","Ayn Rand","Random House",1168);
    private final Magazine magazine = new Magazine("Kinfolk", 9, "2018-10-10", 26);
    private final Newspaper newspaper = new Newspaper("Newspaper", 6, "2000-05-18");


    @Test
    @Order(1)
    public void createBookTest(){
        int id = bookController.createBook(book.getName(), book.getAuthor(), book.getPublishingHouse(), book.getNumPages());
        Paper product = productsController.show(id).getBody();
        Assertions.assertEquals(book, product);
    }

    @Test
    @Order(2)
    public void createMagazineTest(){
        int id = magazineController.createMagazine(magazine.getName(), magazine.getNumber(), magazine.getDate(), magazine.getNumPages());
        Paper product = productsController.show(id).getBody();
        Assertions.assertEquals(magazine, product);
    }

    @Order(3)
    @Test
    public void createNewspaperTest(){
        int id = newspaperController.createNewspaper(newspaper.getName(), newspaper.getNumber(), newspaper.getDate());
        Paper product = productsController.show(id).getBody();
        Assertions.assertEquals(newspaper, product);
    }
    @Test
    @Order(4)
    public void showTest(){
        Paper product = productsController.show(1).getBody();
        Assertions.assertEquals(book, product);
    }

    @Order(5)
    @Test
    public void deleteTest(){
        int id = bookController.createBook(book.getName(), book.getAuthor(), book.getPublishingHouse(), book.getNumPages());
        productsController.delete(id);
        Paper product = productsController.show(id).getBody();
        Assertions.assertNull(product);
    }

    @Order(6)
    @Test
    public void showBooksTest(){
        List<Paper> bookList = new ArrayList<>();
        bookList.add(book);
        Assertions.assertEquals(bookList, bookController.showBooks().getBody());
    }

    @Order(7)
    @Test
    public void showMagazinesTest(){
        List<Paper> magazineList = new ArrayList<>();
        magazineList.add(magazine);
        Assertions.assertEquals(magazineList, magazineController.showMagazines().getBody());
    }

    @Order(8)
    @Test
    public void showNewspaperTest(){
        List<Paper> newspaperList = new ArrayList<>();
        newspaperList.add(newspaper);
        Assertions.assertEquals(newspaperList, newspaperController.showNewspaper().getBody());
    }

    @Order(9)
    @Test
    public void showAllTest(){
        List<Paper> excpectedList = new ArrayList<>();
        excpectedList.add(book);
        excpectedList.add(magazine);
        excpectedList.add(newspaper);
        Assertions.assertEquals(excpectedList, productsController.showAll().getBody());
    }

    @Order(10)
    @Test
    public void editBookTest(){
        int idBook = bookController.createBook(book.getName(), book.getAuthor(), book.getPublishingHouse(), book.getNumPages());
        book.setName("newBookName");
        book.setNumPages(987654321);
        book.setPublishingHouse("newPH");
        book.setAuthor("Liza Artemyeva");
        bookController.updateBook(idBook, book.getName(), book.getAuthor(), book.getPublishingHouse(), book.getNumPages());
        Assertions.assertEquals(book, productsController.show(idBook).getBody());
    }

    @Order(11)
    @Test
    public void editMagazineTest(){
        int idMagazine = magazineController.createMagazine(magazine.getName(),
                magazine.getNumber(), magazine.getDate(), magazine.getNumPages());
        magazine.setName("newMagazineName");
        magazine.setNumPages(987654321);
        magazine.setNumber(100000);
        magazine.setDate("2000-12-12");
        magazineController.updateMagazine(idMagazine, magazine.getName(),
                magazine.getNumber(), magazine.getDate(), magazine.getNumPages());
        Assertions.assertEquals(magazine, productsController.show(idMagazine).getBody());
    }

    @Test
    @Order(12)
    public void editNewspaperTest(){
        int idNewspaper = newspaperController.createNewspaper(newspaper.getName(), newspaper.getNumber(), newspaper.getDate());
        newspaper.setName("newNewspaperName");
        newspaper.setNumber(100000);
        newspaper.setDate("2000-12-12");
        newspaperController.updateNewspaper(idNewspaper, newspaper.getName(), newspaper.getNumber(), newspaper.getDate());
        Assertions.assertEquals(newspaper, productsController.show(idNewspaper).getBody());
    }

    @Test
    @Order(13)
    public void pickUpTest(){
        int idBook = bookController.createBook(book.getName(), book.getAuthor(), book.getPublishingHouse(), book.getNumPages());
        book.setStatus(Status.OUT_OF_STOCK);
        productsController.pickUpProduct(idBook);
        Assertions.assertEquals(book, productsController.show(idBook).getBody());
    }
}

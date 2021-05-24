package ru.vsu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.Domain.Book;
import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Servecies.Manager;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final Manager manager;

    @Autowired
    public BookController(Manager manager) {
        this.manager = manager;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Paper show(@PathVariable("id") int id) {
        return manager.getPaper(id);
    }

    @PostMapping()
    public int createBook(@RequestParam("name") String name, @RequestParam("author") String author,
                             @RequestParam("publishingHouse") String publishingHouse, @RequestParam("numPages") int numPages) {
        Book book = new Book(name, author, publishingHouse, numPages);
        return manager.addNewProduct(book);

    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("author") String author,
                           @RequestParam("publishingHouse") String publishingHouse, @RequestParam("numPages") int numPages) {
        Book book = new Book(name, author, publishingHouse, numPages);
        book.setID(id);
        manager.editProduct(book);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> showBooks(){
        List<Book> books = manager.getBooks();
        if(books != null) return new ResponseEntity<>(books, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

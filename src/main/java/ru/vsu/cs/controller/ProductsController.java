package ru.vsu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.DAO.IRepository;
import ru.vsu.cs.Domain.Book;
import ru.vsu.cs.Domain.Magazine;
import ru.vsu.cs.Domain.Newspaper;
import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Servecies.Manager;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final Manager manager;

    @Autowired
    public ProductsController(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paper> show(@PathVariable("id") int id) {
        return new ResponseEntity<>(manager.getPaper(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        manager.removeProduct(id);
    }


    @GetMapping()
    public ResponseEntity<List<Paper>> showAll(){
        List<Paper> products = manager.getAllProducts();
        if(products != null) return new ResponseEntity<>(products,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/buy/{id}")
    public void pickUpProduct(@PathVariable("id") int id) {
        manager.buyProduct(id);
    }
}

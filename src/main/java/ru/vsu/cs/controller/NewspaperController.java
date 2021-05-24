package ru.vsu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.Domain.Magazine;
import ru.vsu.cs.Domain.Newspaper;
import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Servecies.Manager;

import java.util.List;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController {
    private final Manager manager;

    @Autowired
    public NewspaperController(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public Paper show(@PathVariable("id") int id) {
        return manager.getPaper(id);
    }

    @PostMapping()
    public int createNewspaper(@RequestParam("name") String name, @RequestParam("number") int number,
                                  @RequestParam("date") String date) {
        Newspaper newspaper = new Newspaper(name, number, date);
        return manager.addNewProduct(newspaper);
    }

    @PutMapping("/{id}")
    public void updateNewspaper(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("number") int number,
                       @RequestParam("date") String date) {
        Newspaper newspaper = new Newspaper(name, number, date);
        newspaper.setID(id);
        manager.editProduct(newspaper);
    }

    @GetMapping()
    public ResponseEntity<List<Newspaper>> showNewspaper() {
        List<Newspaper> newspapers = manager.getNewspapers();
        if(newspapers != null) return new ResponseEntity<>(newspapers, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

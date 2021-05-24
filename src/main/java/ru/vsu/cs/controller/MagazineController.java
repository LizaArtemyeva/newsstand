package ru.vsu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.Domain.Book;
import ru.vsu.cs.Domain.Magazine;
import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Servecies.Manager;

import java.util.List;

@RestController
@RequestMapping("/magazines")
public class MagazineController {
    private final Manager manager;

    @Autowired
    public MagazineController(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    public Paper show(@PathVariable("id") int id) {
        return manager.getPaper(id);
    }

    @GetMapping()
    public ResponseEntity<List<Magazine>> showMagazines() {
        List<Magazine> magazines = manager.getMagazines();
        if(magazines != null) return new ResponseEntity<>(magazines, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public void updateMagazine(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("number") int number,
                       @RequestParam("date") String date, @RequestParam("numPages") int numPages) {
        Magazine magazine = new Magazine(name, number, date, numPages);
        magazine.setID(id);
        manager.editProduct(magazine);
    }

    @PostMapping()
    public int createMagazine(@RequestParam("name") String name, @RequestParam("number") int number,
                                 @RequestParam("date") String date, @RequestParam("numPages") int numPages) {
        Magazine magazine = new Magazine(name, number, date, numPages);
        return manager.addNewProduct(magazine);
    }
}

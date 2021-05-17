package ru.vsu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.DAO.IRepository;
import ru.vsu.cs.Domain.Book;
import ru.vsu.cs.Domain.Magazine;
import ru.vsu.cs.Domain.Newspaper;
import ru.vsu.cs.Servecies.Manager;

@Controller
//@RequestMapping("/products")
public class ProductsController {
    private final Manager manager;
    @Autowired
    public ProductsController(Manager manager) {
        this.manager = manager;
    }
    @GetMapping("/test")
    public String test(){
        return "products/test";
    }
    @GetMapping("/products")
    public String index(Model model){
        //список всех продуктов
        model.addAttribute("products", manager.getAllProducts());
        model.addAttribute("books", manager.getBooks());
        model.addAttribute("magazines", manager.getMagazines());
        model.addAttribute("newspapers", manager.getNewspapers());

        return "products/index";
    }
//    @GetMapping("/beautiful")
//    public String index2(Model model){
//        model.addAttribute("products", manager.getAllProducts());
//        model.addAttribute("books", manager.getBooks());
//        model.addAttribute("magazines", manager.getMagazines());
//        model.addAttribute("newspapers", manager.getNewspapers());
//        return "products/beautiful/index";
//    }
    @GetMapping("/books")
    public String showBooks(Model model){
        model.addAttribute("books", manager.getBooks());
        return "products/books/index";
    }
    @GetMapping("/magazines")
    public String showMagazines(Model model){
        model.addAttribute("magazines", manager.getMagazines());
        return "products/magazines/index";
    }
    @GetMapping("/newspapers")
    public String showNewspapers(Model model){
        model.addAttribute("newspapers", manager.getNewspapers());
        return "products/newspapers/index";
    }

    @GetMapping("/products/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        //вывод одного продукта
        model.addAttribute("paper", manager.getPaper(id));
        return "products/show";
    }
    @GetMapping("/books/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "products/books/new";
    }
    @GetMapping("/magazines/new")
    public String newMagazine(Model model){
        model.addAttribute("magazine", new Magazine());
        return "products/magazines/new";
    }
    @GetMapping("/newspapers/new")
    public String newNewspaper(Model model){
        model.addAttribute("newspaper", new Book());
        return "products/newspapers/new";
    }
    @PostMapping("/books")
    public String createBook(@RequestParam("name") String name, @RequestParam("author") String author,
                             @RequestParam("publishingHouse") String publishingHouse, @RequestParam("numPages") int numPages,
                             Model model){
        Book book = new Book(name,author,publishingHouse,numPages);
        System.out.println(name);
        System.out.println(author);
        System.out.println(publishingHouse);
        System.out.println(numPages);
        System.out.println(book);
        manager.addNewProduct(book);
        model.addAttribute("book", book);
        return "redirect:/products";
    }
    @PostMapping("/magazines")
    public String createMagazine(@RequestParam("name") String name, @RequestParam("number") int number,
                             @RequestParam("date") String date, @RequestParam("numPages") int numPages,
                             Model model){
        Magazine magazine = new Magazine(name,number,date,numPages);
        manager.addNewProduct(magazine);
        model.addAttribute("magazine", magazine);
        return "redirect:/products";
    }
    @PostMapping("/newspapers")
    public String createNewspaper(@RequestParam("name") String name, @RequestParam("number") int number,
                                  @RequestParam("date") String date, Model model){
        Newspaper newspaper = new Newspaper(name,number,date);
        manager.addNewProduct(newspaper);
        model.addAttribute("newspaper", newspaper);
        return "redirect:/products";
    }

}

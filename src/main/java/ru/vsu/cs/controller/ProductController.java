//package ru.vsu.cs.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.vsu.cs.DAO.IRepository;
//import ru.vsu.cs.Servecies.Manager;
//
//@Controller
//@RequestMapping("/products")
//public class ProductController {
//    private final Manager manager;
//    @Autowired
//    public ProductController(Manager manager) {
//        this.manager = manager;
//    }
//    @GetMapping("/test")
//    public String test(){
//        return "products/test";
//    }
//    @GetMapping()
//    public String index(Model model){
//        //список всех продуктов
//        model.addAttribute("products", manager.getAllProducts());
//
//        return "products/index";
//    }
//    @GetMapping("/beautiful")
//    public String index2(Model model){
//        model.addAttribute("products", manager.getAllProducts());
//        model.addAttribute("books", manager.getBooks());
//        model.addAttribute("magazines", manager.getMagazines());
//        model.addAttribute("newspapers", manager.getNewspapers());
//        return "products/beautiful/index";
//    }
//    @GetMapping("/books")
//    public String showBooks(Model model){
//        model.addAttribute("books", manager.getBooks());
//        return "products/books/index";
//    }
//    @GetMapping("/magazines")
//    public String showMagazines(Model model){
//        model.addAttribute("magazines", manager.getMagazines());
//        return "products/magazines/index";
//    }
//    @GetMapping("/newspapers")
//    public String showNewspapers(Model model){
//        model.addAttribute("newspapers", manager.getNewspapers());
//        return "products/newspapers/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id,
//                       Model model){
//        //вывод одного продукта
//        model.addAttribute("paper", manager.getPaper(id));
//        return "products/show";
//    }
//}

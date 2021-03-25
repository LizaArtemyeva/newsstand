package ru.vsu.cs.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import ru.vsu.cs.DAO.IRepository;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IRepository iRepository;
    @Autowired
    public ProductController(IRepository iRepository) {
        this.iRepository = iRepository;
    }
    @GetMapping("/test")
    public String test(){
        return "products/test";
    }
    @GetMapping()
    public String index(Model model){
        //список всех продуктов
        model.addAttribute("products", iRepository.getAllProducts());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        //вывод одного продукта
        model.addAttribute("paper", iRepository.getPaper(id));
        return "products/show";
    }
}

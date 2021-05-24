package ru.vsu.cs.Servecies;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.DAO.IRepository;
import ru.vsu.cs.Domain.*;
//import ru.vsu.cs.UI.ConsoleUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("manager")
public class Manager {
    private IRepository iRepository;
    //private ConsoleUI consoleUI;
//hibernateRepository
    @Autowired
    public Manager(@Qualifier("hibernateRepository") IRepository iRepository) {
        this.iRepository = iRepository;
        //this.consoleUI = consoleUI;
    }
    @Transactional
    public List<Book> getBooks(){
        return iRepository.getBooks();
    }
    @Transactional
    public List<Magazine> getMagazines() {
        return iRepository.getMagazines();
    }
    @Transactional
    public List<Newspaper> getNewspapers() {
        return iRepository.getNewspapers();
    }

    @Transactional
    public List<Paper> getAllProducts(){
        return iRepository.getAllProducts();
    }
    @Transactional
    public int addNewProduct(Paper product){
        //Paper paper = consoleUI.addNewProduct();
        //paper.setID(iRepository.addProduct(paper));
        return iRepository.addProduct(product);
    }
    @Transactional
    public Paper getPaper(int id){
        return iRepository.getPaper(id);
    }

    @Transactional
    public void removeProduct(final int id){
        //int id = consoleUI.getId();
        iRepository.removeFromBD(iRepository.getPaper(id));
    }

//    @Transactional
//    public void getAllBooks(){
//        try {
//            consoleUI.showBooks(iRepository.getAllBooks());
//            //return iRepository.getAllBooks();
//        } catch (NotSupportedException e) {
//            e.printStackTrace();
//        }
//        //return null;
//    }
    @Transactional
    public void editProduct(Paper paper){
        //Type type = consoleUI.getType();
        //int id = consoleUI.getId();
        //consoleUI.whatToEditMessage(type);
//        for (int i = 0; i < iRepository.showWhatToEdit(type).size(); i++) {
//            System.out.println(iRepository.showWhatToEdit(type).get(i));
//        }
//        String whattoedit = consoleUI.editField();
//        String changes = consoleUI.editChanges();
//        if(type==Type.BOOK){
//            Book book = (Book) iRepository.getPaper(id);
//            if(whattoedit.equals("name")){
//                book.setName(changes);
//            }
//            else if(whattoedit.equals("author")){
//                book.setAuthor(changes);
//            }
//            else if(whattoedit.equals("publishingHouse")){
//                book.setPublishingHouse(changes);
//            }
//            else if(whattoedit.equals("numPages")){
//                book.setNumPages(Integer.parseInt(changes));
//            }
//            iRepository.editProductBD(book);
//        }
//        if(type==Type.MAGAZINE){
//            Magazine magazine = (Magazine) iRepository.getPaper(id);
//            if(whattoedit.equals("name")){
//                magazine.setName(changes);
//            }
//            else if(whattoedit.equals("number")){
//                magazine.setNumber(Integer.parseInt(changes));
//            }
//            else if(whattoedit.equals("date")){
//                magazine.setDate(changes);
//            }
//            else if(whattoedit.equals("numPages")){
//                magazine.setNumPages(Integer.parseInt(changes));
//            }
//            iRepository.editProductBD(magazine);
//        }
//        if(type==Type.NEWSPAPER){
//            Newspaper newspaper = (Newspaper) iRepository.getPaper(id);
//            if(whattoedit.equals("name")){
//                newspaper.setName(changes);
//            }
//            else if(whattoedit.equals("number")){
//                newspaper.setNumber(Integer.parseInt(changes));
//            }
//            else if(whattoedit.equals("date")){
//                newspaper.setDate(changes);
//            }

        iRepository.editProductBD(paper);
    }
    @Transactional
    public void buyProduct(int id){
        //int id = consoleUI.getId();
        iRepository.pickUpProductBD(iRepository.getPaper(id));
    }
//    @Transactional
//    public void showProducts(){
//        //consoleUI.showProducts(iRepository.getAllProducts());
//    }
}

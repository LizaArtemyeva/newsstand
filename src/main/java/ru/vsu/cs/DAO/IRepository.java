package ru.vsu.cs.DAO;

import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Domain.Type;

import java.util.List;

public interface IRepository {
    List<Paper> getAllProducts();
    Paper getPaper(int id);
    int addProduct(Paper paper);
    void editProductBD(int ID, Type type,String whattoedit,String changes);
    List<String>  showWhatToEdit(Type type);
    void removeFromBD(int ID, Type type);
    void pickUpProductBD(int ID, Type type);
}
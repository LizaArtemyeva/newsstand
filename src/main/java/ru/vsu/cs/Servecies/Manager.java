package ru.vsu.cs.Servecies;

import ru.vsu.cs.DAO.IRepository;
import ru.vsu.cs.Domain.Paper;
import ru.vsu.cs.Domain.Type;
import ru.vsu.cs.UI.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("manager")
public class Manager {
    private IRepository iRepository;
    private Operations operations;

    @Autowired
    public Manager(IRepository iRepository, Operations operations) {
        this.iRepository = iRepository;
        this.operations = operations;
    }

    public void addNewProduct(){
        Paper paper = operations.addNewProduct();
        paper.setID(iRepository.addProduct(paper));
    }
    public void removeProduct(){
        int id = operations.getId();
        Type type = operations.getType();
        iRepository.removeFromBD(id, type);
    }
    public void editProduct(){
        Type type = operations.getType();
        int id = operations.getId();
        operations.whatToEditMessage();
        for (int i = 0; i < iRepository.showWhatToEdit(type).size(); i++) {
            System.out.println(iRepository.showWhatToEdit(type).get(i));
        }
        String whattoedit = operations.editField();
        String changes = operations.editChanges();
        iRepository.editProductBD(id, type,whattoedit,changes);

        iRepository.editProductBD(id, type, whattoedit, changes);
    }
    public void buyProduct(){
        Type type = operations.getType();
        int id = operations.getId();
        iRepository.pickUpProductBD(id, type);
    }
    public void showProducts(){
        operations.showProducts(iRepository.getAllProducts());
    }
}

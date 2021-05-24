package ru.vsu.cs.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Domain.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateRepository implements IRepository{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    private Session getSession(){

        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<Book> getBooks() {
        Session session = getSession();
        return session.createQuery("from Book", Book.class).list();
    }

    @Override
    public List<Magazine> getMagazines() {
        Session session = getSession();
        return session.createQuery("from Magazine", Magazine.class).list();
    }

    @Override
    public List<Newspaper> getNewspapers() {
        Session session = getSession();
        return session.createQuery("from Newspaper", Newspaper.class).list();
    }

//    @Override
//    public List<Paper> getAllProducts() {
//        List<Paper> products = new ArrayList<>();
//        products.addAll(getBooks());
//        products.addAll(getMagazines());
//        products.addAll(getNewspapers());
//        return products;
//    }
    @Override
    public List<Paper> getAllProducts() {
        Session session = getSession();
        return session.createQuery("from Paper", Paper.class).list();
    }

    @Override
    public Paper getPaper(int id) {
        List<Paper> papers = getAllProducts();
        return papers.stream().filter(paper -> paper.getID()==id).findAny().orElse(null);
    }

    @Override
    public int addProduct(Paper paper) {
        Session session = getSession();
        session.persist(paper);
        return paper.getID();
    }

    @Override
    public void editProductBD(Paper paper) {
        Session session = getSession();
        session.update(paper);
    }


    @Override
    public void removeFromBD(Paper paper) {
        Session session = getSession();
        //Paper paper = session.load(Paper.class, id);
        session.delete(paper);
        //session.flush();
    }

    @Override
    public void pickUpProductBD(Paper paper) {
        Session session = getSession();
        paper.setStatus(Status.OUT_OF_STOCK);
        session.update(paper);
    }
}

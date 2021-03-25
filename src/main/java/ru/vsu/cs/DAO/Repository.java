package ru.vsu.cs.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import ru.vsu.cs.Domain.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.*;
@PropertySource("classpath:database.properties")
@Component("repository")
public class Repository implements IRepository {
    private Connection conn;
    private Statement stmt;

    @Value("${database.connection_url}")
    public String url;
    @Value("${database.user}")
    public String username;
    @Value("${database.pass}")
    public String password;

    @PostConstruct
    public void init(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException
                | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public List<Paper> getAllProducts(){
        List<Paper> products = new ArrayList<>();
        String sqlCommand = "SELECT * FROM book ;";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCommand);
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("publishingHouse"),
                        rs.getInt("numPages")
                );
                book.setID(rs.getInt("idbook"));
                products.add(book);
            }
            sqlCommand = "SELECT * FROM magazine;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlCommand);
            while (rs.next()) {
                Magazine magazine = new Magazine(
                        rs.getString("name"),
                        rs.getInt("number"),
                        rs.getString("date"),
                        rs.getInt("numPages")
                );
                magazine.setID(rs.getInt("idmagazine"));
                products.add(magazine);
            }
            sqlCommand = "SELECT * FROM newspapper;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlCommand);
            while (rs.next()) {
                Newspaper newspaper = new Newspaper(
                        rs.getString("name"),
                        rs.getInt("number"),
                        rs.getString("date")
                );
                newspaper.setID(rs.getInt("idnewspapper"));
                products.add(newspaper);
            }
        }catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
    }
        return products;
    }
    public Paper getPaper(int id){
        List<Paper> papers = getAllProducts();
        return papers.stream().filter(paper -> paper.getID()==id).findAny().orElse(null);
    }
    //возвращает последний автоинткремент
    private int getLastId(){
        String getId = "SELECT LAST_INSERT_ID();";
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getId);
            if(rs.next()) return rs.getInt(1);
        }
        catch(Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        throw new IllegalArgumentException("No last id inserted");
    }
    public int addProduct(Paper product) {
        String sqlCommand="INSERT into";
        if (product instanceof Book){
            sqlCommand = sqlCommand+ " book (name, author, publishingHouse,numPages,status)\n" +
                    "VALUES\n" + "(";
            Book b = (Book)product;
            String name = b.getName();
            String author = b.getAuthor();
            String publishingHouse = b.getPublishingHouse();
            int numPages = b.getNumPages();
            int status = b.getStatus().ordinal();
            sqlCommand = sqlCommand+"'"+name+"', '"+author+"', '"+publishingHouse+"', "+numPages+", "+status+");";
            //System.out.println(sqlCommand);
        }
        if (product.getType()== Type.MAGAZINE){
            sqlCommand = sqlCommand+" magazine (name,number,date,numPages,status)\n"
                    + "VALUES\n" + "(";
            Magazine m = (Magazine)product;
            String name = m.getName();
            int number = m.getNumber();
            String date=m.getDate();
            int numPages=m.getNumPages();
            int status = m.getStatus().ordinal();
            sqlCommand = sqlCommand+"'"+name+"', "+number+", '"+date+"', "+numPages+", "+status+");";
            //System.out.println(sqlCommand);
        }
        if (product.getType()== Type.NEWSPAPER){
            sqlCommand = sqlCommand+" newspapper (name, number, date, status)\n"+
                    "VALUES\n" + "(";
            Newspaper n = (Newspaper)product;
            String name = n.getName();
            int number = n.getNumber();
            String date=n.getDate();
            int status = n.getStatus().ordinal();
            sqlCommand = sqlCommand+"'"+name+"', "+number+", '"+date+"', "+status+");";
            //System.out.println(sqlCommand);
        }

        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlCommand);
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return getLastId();

    }
    //удаляться должно по имени продукта/////по айди?
    public void removeFromBD(int ID, Type type){
        String sqlCommand="DELETE FROM";
        if(type == Type.BOOK){
            sqlCommand = sqlCommand+" book" + " WHERE idbook = " + ID;
        }
        if(type == Type.MAGAZINE){
            sqlCommand = sqlCommand+" magazine" + " WHERE idmagazine = " + ID;
        }
        if(type == Type.NEWSPAPER){
            sqlCommand = sqlCommand+" newspapper" + " WHERE idnewspapper = " + ID;
        }

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlCommand);
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public void pickUpProductBD(int ID, Type type){
        String sqlCommand="UPDATE ";
        if(type == Type.BOOK){
            sqlCommand = sqlCommand+" book "
                    + "SET status" + " = " +1 + " WHERE idbook = "+ID;
        }
        if(type == Type.MAGAZINE){
            sqlCommand = sqlCommand+" magazine "
                    + "SET status" + " = " +1 + " WHERE idmagazine = "+ID;
        }
        if(type == Type.NEWSPAPER){
            sqlCommand = sqlCommand+" newspapper "
                    + "SET status" + " = " +1 + " WHERE idnewspapper = "+ID;
        }
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlCommand);
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }
    public void editProductBD(int ID, Type type,String whattoedit,String changes){
        //UPDATE Clients
        //SET FIO = "Зиновьев Денис Денисович"
        //WHERE idClient = 5;
        String sqlCommand="UPDATE";
        if(type == Type.BOOK){
            sqlCommand = sqlCommand+" book\n"
                    + "SET " + whattoedit + " = '" +changes + "' WHERE idbook = "+ID;
        }
        if(type == Type.MAGAZINE){
            sqlCommand = sqlCommand+" magazine\n"
                    + "SET " + whattoedit + " = '" +changes + "' WHERE idmagazine = "+ID;
        }
        if(type == Type.NEWSPAPER){
            sqlCommand = sqlCommand+" newspapper\n"
                    + "SET " + whattoedit + " = '" +changes + "' WHERE idnewspapper = "+ID;
        }
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlCommand);
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public List<String> showWhatToEdit(Type type){
        List<String> fields = new ArrayList<>();
        Connection con;
        Statement stmt;
        ResultSet rs;
        String sqlCoomand = "SELECT *FROM ";
        try {
            con = DriverManager.getConnection(url, username, password);
            stmt=con.createStatement();

            if(type == Type.BOOK) {
                sqlCoomand= sqlCoomand+"book";
            }
            if(type == Type.MAGAZINE){
                sqlCoomand =sqlCoomand+"magazine;";
            }
            if(type == Type.NEWSPAPER) {
                sqlCoomand = sqlCoomand + "newspapper;";
            }
            rs = stmt.executeQuery(sqlCoomand);
            ResultSetMetaData md = rs.getMetaData();
            int counter = md.getColumnCount();
            String colName[] = new String[counter];
            for (int loop = 2; loop <= counter-1; loop++) {
                colName[loop-1] = md.getColumnLabel(loop);
                //System.out.println(colName[loop-1]);
                fields.add(colName[loop-1]);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return fields;
    }
}
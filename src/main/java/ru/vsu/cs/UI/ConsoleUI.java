package ru.vsu.cs.UI;

import ru.vsu.cs.DAO.IRepository;
import ru.vsu.cs.Domain.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
@Component("operations")
public class ConsoleUI {
//    private Manager manager;
    private Scanner in = new Scanner(System.in);
    private String name, publishingHouse, author,date2, product;
    private int numPages,number;

    public Paper addNewProduct(){
        System.out.print("Что вы хотите добавить?(book/magazine/newspaper) - " );
        product = in.next();
        if(product.equals("book")){
            System.out.println("Введите Название, автора, издательство, кол-во стр.");
            name = in.next();
            author = in.next();
            publishingHouse = in.next();
            numPages=in.nextInt();
            return new Book(name,author,publishingHouse,numPages);
        }
        if(product.equals("magazine")){
            System.out.println("Введите Название, номер, дата выпуска, кол-во стр.");
            name = in.next();
            number = in.nextInt();
            date2 = in.next();
            numPages=in.nextInt();
            return new Magazine(name,number,date2,numPages);
        }
        if(product.equals("newspaper")){
            System.out.println("Введите Название, номер, дата выпуска.");
            name = in.next();
            number = in.nextInt();
            date2 = in.next();
            return new Newspaper(name,number,date2);
        }
        return null;
    }
    public int getId(){
        System.out.println("Введите индекс продукта -");
        int id = in.nextInt();

        return id;
    }
    //Type.values() чтобы вывести все возможные значения
    public Type getType(){
        System.out.println("C каким продуктом будем работать? (book/ magazine/ newspaper) -");
        Type type;
        String userType = in.next();
        if(userType.equals("book")){
            type=Type.BOOK;
            return type;
        }
        if(userType.equals("magazine")){
            type = Type.MAGAZINE;
            return type;
        }
        if(userType.equals("newspaper")){
            type = Type.NEWSPAPER;
            return type;
        }
        return null;
    }
    public void showProducts(final List<Paper> products){
        for(int i = 0; i < products.size(); i++){
           System.out.println(products.get(i));
        }
    }
    public void whatToEditMessage(final Type type){
        System.out.println("Вы можете поменять следующие поля:");
        if(type.equals(Type.BOOK)){
            try {
                Class<Book> book = (Class<Book>) Class.forName("ru.vsu.cs.Domain.Book");
                Field[] fs = book.getDeclaredFields(); // получили массив с объектами Field, соответствующие полям класса SomeClass
                for (Field f : fs) System.out.println(f.getName()); // имя поля
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(type.equals(Type.MAGAZINE)){
            try {
                Class<Magazine> m = (Class<Magazine>) Class.forName("ru.vsu.cs.Domain.Magazine");
                Field[] fs = m.getDeclaredFields(); // получили массив с объектами Field, соответствующие полям класса SomeClass
                for (Field f : fs) System.out.println(f.getName()); // имя поля
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(type.equals(Type.NEWSPAPER)){
            try {
                Class<Newspaper> n = (Class<Newspaper>) Class.forName("ru.vsu.cs.Domain.Newspaper");
                Field[] fs = n.getDeclaredFields(); // получили массив с объектами Field, соответствующие полям класса SomeClass
                for (Field f : fs) System.out.println(f.getName()); // имя поля
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String editField(){
        System.out.println("Какое поле вы хотите изменить? -");
        String whatToEdit =in.next();
        return whatToEdit;
    }
    public String editChanges(){ ;
        System.out.println("На что вы хотите заменить это поле? -");
        String edit =in.next();
        return edit;
    }
}

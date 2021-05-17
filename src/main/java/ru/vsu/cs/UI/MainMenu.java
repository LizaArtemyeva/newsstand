//package ru.vsu.cs.UI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.vsu.cs.Servecies.Manager;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Scanner;
//@Component("mainMenu")
//public class MainMenu {
//    private Scanner scanner = new Scanner(System.in);
//    private Manager manager;
//    @Autowired
//    public MainMenu(Manager manager) {
//        this.manager = manager;
//    }
//
//    public void show() throws ParseException {
//        while (true) {
//            System.out.println();
//            System.out.println("Добро пожаловать в газетный киоск");
//            System.out.println("1 - Посмотреть список товаров");
//            System.out.println("2 - Добавить товар");
//            System.out.println("3 - Забрать товар (изменить статус наличия)");
//            System.out.println("4 - Отредактировать товар");
//            System.out.println("5 - Удалить товар из базы");
//            System.out.println("0 - Выйти");
//
//            int commandCode = scanner.nextInt();
//            switch (commandCode) {
//                case 1:
//                    manager.showProducts();
//                    break;
//                case 2:
//                    manager.addNewProduct();
//                    break;
//                case 3:
//                    manager.buyProduct();
//                    break;
//                case 4:
//                    manager.editProduct();
//                    break;
//                case 5:
//                    manager.removeProduct();
//                    break;
//                case 0:
//                    return;
//            }
//        }
//
//    }
//}

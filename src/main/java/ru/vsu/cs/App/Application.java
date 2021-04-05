package ru.vsu.cs.App;

import ru.vsu.cs.UI.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.util.Arrays;

public class Application {
    public void run() throws ParseException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));

//        IRepository productRepository = context.getBean(Repository.class);
//
//        Operations operations = context.getBean(Operations.class);
//
//        Manager manager = context.getBean(Manager.class);

        MainMenu mainMenu = context.getBean(MainMenu.class);

        mainMenu.show();
    }
}

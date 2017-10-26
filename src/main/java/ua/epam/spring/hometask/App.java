package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.logic.EventLogic;
import ua.epam.spring.hometask.logic.ApplicationLogic;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by dish on 01.10.17.
 */
@Component("app")
public class App {
    
    @Autowired
    private ApplicationLogic applicationLogic;
    
    @Autowired
    private EventLogic eventLogic;
    
    public App(){
    }
    
    public static void main(String[] args) throws ParseException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        App application = (App) context.getBean("app");
        application.start();
    }
    
    public void start() throws ParseException {
        String choice = "";
        Scanner sc = new Scanner(System.in);
        boolean switcher = true;
        while (switcher) {
            System.out.println("Write your Admin(A)/User(U):");
            choice = sc.nextLine();
            switch (choice) {
                case "A":
                case "a":
                    applicationLogic.processAdmin();
                    switcher = false;
                    break;
                case "U":
                case "u":
                    applicationLogic.processUser();
                    switcher = false;
                    break;
            }
        }
    }
    

}

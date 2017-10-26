package ua.epam.spring.hometask.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.IdGenerator;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dish on 01.10.17.
 */

@Component
public class ApplicationLogic {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private IdGenerator idGenerator;
    
    @Autowired
    private EventLogic eventLogic;
//
//    @Autowired
//    private TicketLogic ticketLogic;
    
    @Autowired
    private BookingService bookingService;
    private boolean isAdminMode;
    
    @PostConstruct
    public void init(){
        eventLogic.eventGenerator();
    }
//
    public EventLogic getEventLogic() {
        return eventLogic;
    }
    
    public void setEventLogic(EventLogic eventLogic) {
        this.eventLogic = eventLogic;
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public BookingService getBookingService() {
        return bookingService;
    }
    
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    public void showUsersMenu() {
        System.out.println("1. Register" );
        System.out.println("2. View events" );
        System.out.println("3. Get ticket prices" );
        System.out.println("4. Buy tickets " );
        System.out.println("5. Switch user " );
        System.out.println("6. Exit " );
    }
    
    public void showAdminsMenu() {
        System.out.println("1. View purchased tickets" );
        System.out.println("2. Enter events" );
        System.out.println("3. Switch user " );
        System.out.println("4. Exit " );
    }
    public void processUser() throws NumberFormatException, ParseException {
        isAdminMode = false;
        String choice = "";
        List<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        options.add("5");
        options.add("6");
        Scanner sc = new Scanner(System.in);
        while (!choice.equals("5")){
            showUsersMenu();
            choice = sc.nextLine();
            if (!options.contains(choice)){
                System.out.println("There's no such item, try again");
            }
            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    viewEvents();
                    break;
                case "3":
                    getTicketPrices();
                    break;
                case "4":
                    buyTickets();
                    break;
                case "5":
                    switchUser();
                    break;
            }
        }
        
    }
    
    public void processAdmin() throws ParseException {
        isAdminMode = true;
        String choice = "";
        List<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("4");
        Scanner sc = new Scanner(System.in);
        while (!choice.equals("3")){
            showAdminsMenu();
            choice = sc.nextLine();
            if (!options.contains(choice)){
                System.out.println("There's no such item, try again");
            }
            switch (choice) {
                case "1":
                    viewPurchasedTickets();
                    break;
                case "2":
                    enterEvents();
                    break;
                case "3":
                    switchUser();
                    break;
            }
        }
    }
    
    public void registerUser() throws ParseException {
        User user = new User();
        String fisrtName = "";
        String lastName = "";
        String email = "";
        
        System.out.printf("Write your name: ");
        Scanner sc = new Scanner(System.in);
        fisrtName = sc.nextLine();
        user.setFirstName(fisrtName);
        System.out.printf("Write your surname: ");
        lastName = sc.nextLine();
        user.setLastName(lastName);
        System.out.printf("Write your email: ");
        email = sc.nextLine();
        user.setEmail(email);
        System.out.printf("Write your birthday: ");
        String bd = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        user.setBirthday(sdf.parse(bd));
        long userId = IdGenerator.getNextUserId();
        user.setId(userId);
        System.out.println("User Id: " + userId);
        userService.save(user);
    }
    
    
    public void viewEvents(){
        eventLogic.showEvents();
    }
    
    
    public void getTicketPrices(){
        System.out.printf("Chose event (enter id): ");
        Scanner sc = new Scanner(System.in);
        String eventId = sc.nextLine();
        Event e = eventLogic.getEventService().getById(Long.parseLong(eventId));
        
        System.out.println("Enter date:");
        Map<Integer, Date> dateOptions = new HashMap<>();
        int i = 1;
        for(Date ed : e.getAuditoriums().keySet()){
            System.out.println(i + ". " + ed);
            dateOptions.put(i, ed);
            i++;
        }
        String dateInd = sc.nextLine();
        Date selectedDate = dateOptions.get(Integer.parseInt(dateInd));
        
        System.out.println("Enter comma-separated seats:");
        String csSeats = sc.nextLine();
        Set<Long> seats = Arrays.stream(csSeats.split(",")).map(Long::parseLong).collect(Collectors.toSet());
        
        System.out.println("Enter your ID: ");
        String userId = sc.nextLine();
        double price = bookingService.getTicketsPrice(e, selectedDate, userService.getById(Long.parseLong(userId)), seats);
        System.out.println("Price is: " + price);
    }
    
    public void buyTickets(){
        
        System.out.printf("Chose event (enter id): ");
        Scanner sc = new Scanner(System.in);
        String eventId = sc.nextLine();
        Event e = eventLogic.getEventService().getById(Long.parseLong(eventId));
        
        System.out.println("Enter date:");
        Map<Integer, Date> dateOptions = new HashMap<>();
        int i = 1;
        for(Date ed : e.getAuditoriums().keySet()){
            System.out.println(i + ". " + ed);
            dateOptions.put(i, ed);
            i++;
        }
        String dateInd = sc.nextLine();
        Date selectedDate = dateOptions.get(Integer.parseInt(dateInd));
        
        System.out.println("Enter the seat:");
        String csSeats = sc.nextLine();
        long seat = Long.parseLong(csSeats);
        
        System.out.println("Enter your ID: ");
        String userId = sc.nextLine();
        
        Ticket ticket = new Ticket(userService.getById(Long.parseLong(userId)), e, selectedDate, seat);
        ticket.setUserId(userId);
        ticket.setEventId(e.getId().toString());
        Set<Ticket> ts = new HashSet<>();
        ts.add(ticket);
        bookingService.bookTickets(ts);
    }
    
    public void viewPurchasedTickets(){
        System.out.printf("Chose event (enter id): ");
        Scanner sc = new Scanner(System.in);
        String eventId = sc.nextLine();
        Event e = eventLogic.getEventService().getById(Long.parseLong(eventId));
        
        System.out.println("Enter date:");
        Map<Integer, Date> dateOptions = new HashMap<>();
        int i = 1;
        for(Date ed : e.getAuditoriums().keySet()){
            System.out.println(i + ". " + ed);
            dateOptions.put(i, ed);
            i++;
        }
        
        String dateInd = sc.nextLine();
        Date selectedDate = dateOptions.get(Integer.parseInt(dateInd));
        Set<Ticket> purchasedTickets = bookingService.getPurchasedTicketsForEvent(e, selectedDate);
        if (purchasedTickets != null)
        System.out.println(purchasedTickets.toString());
    }
    
    public void enterEvents(){
    }
    
    public void switchUser() throws ParseException {
        if (!isAdminMode){
            processAdmin();
        } else
            processUser();
        
    }
    
}

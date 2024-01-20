package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
              new Car("model1", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru",
              new Car("model5", 5)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru",
              new Car("model6", 6)));

      User userByCar = userService.getUserByCar("model1", 1);
      if (userByCar != null) {
         System.out.println("Car's owner is :");
         System.out.println("Id = "+userByCar.getId());
         System.out.println("First Name = "+userByCar.getFirstName());
         System.out.println("Last Name = "+userByCar.getLastName());
         System.out.println("Email = "+userByCar.getEmail());
         System.out.println();
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car = model: " + user.getCar().getModel() +
                    ", series: " + user.getCar().getSeries());
         }
         System.out.println();
      }

      context.close();
   }
}

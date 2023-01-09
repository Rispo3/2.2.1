package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
User user1 = new User("User1", "Lastname1", "user1@mail.ru");
User user2 = new User("User2", "Lastname2", "user2@mail.ru");
User user3 = new User("User3", "Lastname3", "user3@mail.ru");
User user4 = new User("User4", "Lastname4", "user4@mail.ru");

user1.setUsersCar(new Car("model1",11));
user2.setUsersCar(new Car("model2",22));
user3.setUsersCar(new Car("model3",33));
user4.setUsersCar(new Car("model4",44));

userService.add(user1);
userService.add(user2);
userService.add(user3);
userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUsersCar());
      }
       System.out.println(userService.getUserFromCarModelAndSeries("model5",55));
      context.close();
   }
}

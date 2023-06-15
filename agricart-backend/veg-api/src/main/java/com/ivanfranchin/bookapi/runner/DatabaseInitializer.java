package com.ivanfranchin.bookapi.runner;

import com.ivanfranchin.bookapi.model.Book;
import com.ivanfranchin.bookapi.model.Product;
import com.ivanfranchin.bookapi.model.User;
import com.ivanfranchin.bookapi.security.WebSecurityConfig;
import com.ivanfranchin.bookapi.service.BookService;
import com.ivanfranchin.bookapi.service.ProductService;
import com.ivanfranchin.bookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final BookService bookService;
    private final ProductService productService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        USERS.forEach(userService::saveUser);
        getBooks().forEach(bookService::saveBook);
        getProducts().forEach(productService::saveProduct);
        log.info("Database initialized");
    }

    private List<Book> getBooks() {
        return Arrays.stream(BOOKS_STR.split("\n"))
                .map(bookInfoStr -> bookInfoStr.split(";"))
                .map(bookInfoArr -> new Book(bookInfoArr[0], bookInfoArr[1]))
                .collect(Collectors.toList());
    }
    
    private List<Product> getProducts() {
        return Arrays.stream(PRODUCTS_STR.split("\n"))
                .map(productInfoStr -> productInfoStr.split(";"))
                .map(productInfoArr -> new Product(productInfoArr[0], productInfoArr[1],productInfoArr[2],productInfoArr[3],productInfoArr[4]))
                .collect(Collectors.toList());
    }
    

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER),
            new User("Mani", "Mani", "Mani", "mani@mycompany.com", WebSecurityConfig.USER)
            
    );

    private static final String BOOKS_STR =
            """
                    101;Tomato
                    9781603090698;August Moon
                    9781891830372;The Barefoot Serpent (softcover) by Scott Morse
                    9781891830723;Will You Still Love Me If I Wet the Bed by Liz Prince
                    9781603094405;Ye
                    """;
    
    
    
    
    private static final String PRODUCTS_STR =
            """
                    101;Tomato;69;1;https://www.freepnglogos.com/uploads/tomato-png/tomato-and-kidney-stone-everyday-life-23.png
                    102;Lemon ;39;1;https://thumbs.dreamstime.com/b/fresh-lemon-29209760.jpg
                    103;Onion ;29;1;https://agrovilla.in/wp-content/uploads/2020/04/36700-0w470h470_Organic_Red_Onion_From_Italy.jpg
                    104;Cabbage;49;1;https://www.freshpoint.com/wp-content/uploads/2020/02/Freshpoint-green-cabbage.jpg
                    105;Raddish;56;1;https://theleafandclay.com/wp-content/uploads/2021/07/Radish-Image.jpg
                    106;Garlic;89;1;https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRf3ZTJsbez0d8HKzOpJsNqmglW09atwqQvLw&usqp=CAU
                    107;Cauliflower;79;1;https://thumbs.dreamstime.com/b/white-cauliflower-5199363.jpg
                    108;Carrot;39;1;http://cdn.shopify.com/s/files/1/0451/1101/7626/products/carrotseeds.jpg?v=1604032858
                    109;Sweet Potatos;89;1;https://www.farmfreshhandpicked.com/pub/media/catalog/product/cache/61f4cbf5acaf9c69e4b207aadb12f207/s/w/sweet-potato510.jpg
                    """;
}

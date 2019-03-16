package ostap.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringdatajpaApplication {

//    @Autowired // створює автоматично біни
//    private MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

//    @PostConstruct // запускається зразу після запуску програми
//    public void postConst() {
//        mainService.createInfo();
//        mainService.printAllPersons();
//    }
}


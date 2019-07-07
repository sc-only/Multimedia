package vacation.work.multimedia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class HelloController {

    @Value("${minMoney}")
    private BigDecimal minMoney;

    @GetMapping("/hello")
    public String say(@RequestParam(value = "username", required=false,defaultValue = "wyy") String username){
        return "username: " + username ;
    }
}

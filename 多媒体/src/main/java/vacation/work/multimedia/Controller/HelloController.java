package vacation.work.multimedia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vacation.work.multimedia.Domain.User;
import vacation.work.multimedia.Repositoty.UserRepository;

@Controller
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/regist_web")
    @ResponseBody
    public String getCode(@RequestParam("code") String code){
        User user = userRepository.findByCode(code);
        if(user==null){
            return "<h1>验证失败，请确认验证码</h1>";
        }else{
            user.setCode("");
            user.setState(1);
            userRepository.save(user);
            return "<h1>验证成功，请前往登录</h1>";
        }
    }
}

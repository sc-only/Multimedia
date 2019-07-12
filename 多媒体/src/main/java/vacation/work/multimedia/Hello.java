package vacation.work.multimedia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vacation.work.multimedia.Utils.CodeUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Hello {

    @RequestMapping(value = "/h")
    public String hello(){
        return "/hello";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        if (!CodeUtil.checkVerifyCode(request)) {
            return "验证码有误！";
        } else {
            return "hello,world";
        }
    }
}
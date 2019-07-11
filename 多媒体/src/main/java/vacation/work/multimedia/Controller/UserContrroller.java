package vacation.work.multimedia.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vacation.work.multimedia.Domain.User;
import vacation.work.multimedia.Repositoty.UserRepository;
import vacation.work.multimedia.Service.MailService;
import vacation.work.multimedia.Utils.UUIDUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
public class UserContrroller {
    private final Logger logger = LoggerFactory.getLogger(UserContrroller.class);

    @Autowired
    private UserRepository userRepository;

    @Resource
    MailService mailService;


    @PostMapping("/register")
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email) throws MessagingException {
        System.out.println("取得参数: " + username + " " + password + " " + email);
        if(!userRepository.findByUsername(username).isEmpty()){
            return "no";
        }else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setState(0); // 0。表示未激活 1.表示已激活
            user.setRoles("user");
            user.setPermission("user:use");
            String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
            user.setCode(code);
            userRepository.save(user);
            sendHtmlMail(email,code);
            return "yes";
        }
    }
//    public void sendHtmlMail(String to,String code) throws MessagingException {
//        String content="<html>\n"+"<body>\n"+"<h1>你好，感谢您的注册，这是一封验证邮件么，请点击下面的链接完成注册，感谢您的支持！</h1></br>"+"<h3><a href='http://localhost/regist_web/ActiveServlet?code="+ code+"'></h3>"+"</body>"+"</html>";
//        mailService.sendHtmlMail(to,"验证邮件",content);
//    }

    public void sendHtmlMail(String to,String code) throws MessagingException {
//        String content="<html>\n"+"<body>\n"+"<h3>hello world ,这是一封html邮件</h3>\n"+"</body>\n"+"</html>";
        String content="<html>\n"+"<body>\n"+"<h1>你好，感谢您的注册，这是一封验证邮件么，请点击下面的链接完成注册，感谢您的支持！</h1></br>"+"<h3><a href='http://localhost/regist_web/ActiveServlet?code="+ code+"'>激活账户</a></h3>"+"</body>"+"</html>";
        mailService.sendHtmlMail(to,"这是一封HTMl邮件",content);
    }

}

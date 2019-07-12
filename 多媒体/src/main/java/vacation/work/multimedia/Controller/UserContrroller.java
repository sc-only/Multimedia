package vacation.work.multimedia.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vacation.work.multimedia.Domain.User;
import vacation.work.multimedia.Realm.MyShiroRealm;
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
        if(userRepository.findByUsername(username)!=null){
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

    public void sendHtmlMail(String to,String code) throws MessagingException {
        String content="<html>\n"+"<body>\n"+"<h1>你好，感谢您的注册，这是一封验证邮件，请点击下面的链接完成注册，感谢您的支持！</h1></br>"+"<h3><a href='http://localhost:8080/regist_web?code="+ code+"'>激活账户</a></h3>"+"</body>"+"</html>";
        mailService.sendHtmlMail(to,"邮箱验证",content);
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password){

        System.out.println("取得参数: " + username + " " + password );
        MyShiroRealm myShiroRealm = new MyShiroRealm();

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(myShiroRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            System.out.println("密码不正确");
            return "no1";
        }catch(UnknownAccountException uae){
            System.out.println("账号不存在");
            return "no2";
        }
        if(subject.isAuthenticated()){
            System.out.println("认证成功");
            return "yes";
        }else{
            token.clear();
            return "no";
        }
    }
}
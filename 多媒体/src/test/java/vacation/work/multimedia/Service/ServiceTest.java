package vacation.work.multimedia.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("1781587589@qq.com","这是第一封邮件","大家好，这是我的第一封邮件");
    }


//    @Test
//    public void sendHtmlMail() throws MessagingException {
//        String content="<html>\n"+"<body>\n"+"<h3>hello world ,这是一封html邮件</h3>\n"+"</body>\n"+"</html>";
//        String content="<html>\n"+"<body>\n"+"<h1>你好，感谢您的注册，这是一封验证邮件么，请点击下面的链接完成注册，感谢您的支持！</h1></br>"+"<h3><a href='http://localhost/regist_web/ActiveServlet?code="+ code+"'></h3>"+"</body>"+"</html>";
//        mailService.sendHtmlMail("1781587589@qq.com","验证邮件",content);
//    }
    @Test
    public void SendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+"<body>\n"+"<h3>hello world ,这是一封html邮件</h3>\n"+"</body>\n"+"</html>";

        mailService.sendHtmlMail("1781587589@qq.com","这是一封HTMl邮件",content);
    }
}

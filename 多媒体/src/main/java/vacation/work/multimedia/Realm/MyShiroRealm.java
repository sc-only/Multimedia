package vacation.work.multimedia.Realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import vacation.work.multimedia.Domain.User;
import vacation.work.multimedia.Repositoty.UserRepository;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    {
        super.setName("customRealm");
    }

    /**
     * 用于获取登录成功后的角色、权限等信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 验证当前登录的Subject
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //拿到账号（username）
        String username = (String) token.getPrincipal();
        System.out.println("username=:"+username);
        User user = userRepository.findByUsername(username);
        if (user==null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"customRealm");
        return info;
    }
}

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.IUserService;
import cn.itcast.travel.service.Impl.userServiceImpl;
import cn.itcast.travel.util.MailUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class userServiceImplTest {
    IUserService userService;
    @Before
    public void before(){
        userService = new userServiceImpl();
    }

    @Test
    public void userRegisterTest() throws IOException {
        User user = new User();
        user.setUsername("xuwenshuo123");
        user.setPassword("jicococ0319");
        boolean b = userService.userRegister(user);
        System.out.println(b);
    }
    @Test
    public void userLoginTest() throws IOException {
        User user = new User();
        user.setUsername("xuwenshuo123");
        user.setPassword("jicococ0319");
        User user1 = userService.userLogin(user);
        System.out.println(user1);
    }
    @Test
    public void sendEmailtest(){
        MailUtils.sendMail("wenshuo-xu@isowater.com","test","test");
    }
}

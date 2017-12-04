import beans.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vladyslav on 03.12.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebApplication.class})
@WebAppConfiguration
public class WebAppTest {
    @Test
    public void test() throws Exception {

    }

}
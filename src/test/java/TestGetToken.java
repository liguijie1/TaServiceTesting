import com.testhome.weixin.wework.Wework;
import com.testhome.weixin.wework.WeworkConfig;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TestGetToken {

    @Test
    public void testgetToken(){
        Wework wework = new Wework();
        String token =wework.getWeworkToken(WeworkConfig.getInstance().secret);
        assertThat(token,not(equalTo(null)));

    }
}

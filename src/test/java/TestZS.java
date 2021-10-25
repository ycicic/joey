import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.entity.DataProviders;
import com.zhongshi.joey.processor.CaseProcessorFacade;
import com.zhongshi.joey.test.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycc
 */
@Slf4j
public class TestZS extends BaseTest {

    @Test(dataProvider = "beforeParticipate", dataProviderClass = DataProviders.class, priority = 1)
    public void before(Case testCase) {
        log.info("[{}]开始执行：{}", testCase.getCaseType(), testCase);
        Object exec = new CaseProcessorFacade().exec(testCase);
        log.info("[{}]执行结果：{}", testCase.getCaseType(), exec);
    }

    @Test(dataProvider = "participate", dataProviderClass = DataProviders.class, priority = 2)
    public void test(Case testCase) {
        log.info("[{}]开始执行：{}", testCase.getCaseType(), testCase);
        Object exec = new CaseProcessorFacade().exec(testCase);
        log.info("[{}]执行结果：{}", testCase.getCaseType(), exec);
        //TODO 响应报文断言断言
        //TODO 数据库断言
    }

    public static void main(String[] args) {
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","Bearer 3f1d37bc-fa2d-4785-a1d3-ab4bb0c9c099");
//        headers.put("host","test02.gdzskj.tech");
        Response post = RestAssured.given().contentType("application/json;charset=UTF-8").headers(headers).body("{}").post("https://test02.gdzskj.tech/order/v1/guangxi/orders/user/882202876308230144/_search");
        System.out.println(post.asString());
    }

}

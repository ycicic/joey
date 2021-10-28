import com.zhongshi.joey.entity.Case;
import com.zhongshi.joey.entity.DataProviders;
import com.zhongshi.joey.processor.CaseProcessorFacade;
import com.zhongshi.joey.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * @author ycc
 */
@Slf4j
public class TestZS extends BaseTest {

    @Test(dataProvider = "beforeParticipate", dataProviderClass = DataProviders.class, priority = 1)
    public void before(Case testCase) {
        log.info("[{}]开始执行：{}", testCase.getCaseType(), testCase);
        String exec = new CaseProcessorFacade().exec(testCase);
        log.info("[{}]执行结果：{}", testCase.getCaseType(), exec);
        assertCase(testCase.getPreResult(), exec);
    }

    @Test(dataProvider = "participate", dataProviderClass = DataProviders.class, priority = 2)
    public void test(Case testCase) {
        log.info("[{}]开始执行：{}", testCase.getCaseType(), testCase);
        String exec = new CaseProcessorFacade().exec(testCase);
        log.info("[{}]执行结果：{}", testCase.getCaseType(), exec);
        assertCase(testCase.getPreResult(), exec);
        //TODO 响应报文断言断言
        //TODO 数据库断言
    }

}

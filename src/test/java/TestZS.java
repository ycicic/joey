import com.zhongshi.joey.processor.CaseProcessorFacade;
import com.zhongshi.joey.test.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * @author ycc
 */
@Feature("自动化测试")
@Slf4j
public class TestZS extends BaseTest {

    @Test(description = "自动化测试")
    public void automation() {
        int i = 1;
        CASE_QUEUE.forEach(testCase -> {
            Allure.story(testCase.getCaseComment());
            Allure.step("开始执行用例[" + i + "]: " + testCase);
            log.info("开始执行：{}", testCase);
            String exec = new CaseProcessorFacade().exec(testCase);
            Allure.step("执行结果：" + exec);
            log.info("执行结果：{}", exec);
            assertCase(testCase.getPreResult(), exec);
        });
    }

}

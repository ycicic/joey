import com.zhongshi.joey.processor.CaseProcessorFacade;
import com.zhongshi.joey.test.BaseTest;
import com.zhongshi.joey.utils.BuildDataUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * @author ycc
 */
@Feature("自动化测试")
@Slf4j
public class TestZS extends BaseTest {

    @Description("自动化测试")
    @Test(description = "执行用例")
    public void automation() {
        int i = 1;
        CASE_QUEUE.forEach(testCase -> {
            Allure.story(testCase.getCaseComment());
            Allure.step("开始执行用例[" + i + "]: " + testCase.getCaseComment());
            log.info("开始执行：{}", testCase);
            String exec = new CaseProcessorFacade().exec(testCase);
            Allure.step("执行结果：\n" + BuildDataUtil.parseJson(exec));
            log.info("执行结果：{}", exec);
            assertCase(testCase.getPreResult(), exec);
        });
    }

}

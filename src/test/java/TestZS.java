import com.zhongshi.joey.processor.CaseProcessorFacade;
import com.zhongshi.joey.test.BaseTest;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * @author ycc
 */
@Slf4j
public class TestZS extends BaseTest {

    @Story("自动化测试")
    @Test
    public void automation() {
        CASE_QUEUE.forEach(testCase -> {
            log.info("开始执行：{}", testCase);
            String exec = new CaseProcessorFacade().exec(testCase);
            log.info("执行结果：{}", exec);
            assertCase(testCase.getPreResult(), exec);
        });
    }

}

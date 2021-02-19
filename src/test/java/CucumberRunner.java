import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.appline.framework.utils.MyCucumberListener"},
        glue = {"steps"},
        features = {"src/test/resources/"},
        tags = "@checkInterestRateError"
)
public class CucumberRunner {
}

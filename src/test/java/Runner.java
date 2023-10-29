import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber.json"
                , "html:target/site/cucumber-report.html"
                , "timeline:target/timelineReport"
                , "rerun:target/failedrerun.txt"})
public class Runner {




}

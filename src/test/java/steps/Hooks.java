package steps;

import driver.DriverInit;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import utils.CucumberUtils;
import utils.Helper;
import utils.slack.Slack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Hooks {

    DriverInit driverInit = new DriverInit();
    CucumberUtils cucumberUtils = new CucumberUtils();

    @Before
    public void beforeAll() throws IOException {
        driverInit.setDriver();
        // File fileCucumber = new File("C:\\Users\\onur.tarar\\IdeaProjects\\obilet_Web_Automation\\target\\cucumber.json");
        // if (fileCucumber.exists()) {
        //     byte[] empty = new byte[0];
        //     FileUtils.writeByteArrayToFile(fileCucumber,empty);
        //     System.out.println("Cucumber File deleted");
        // }
//        if (Helper.testProp.getProperty("isLocal").equalsIgnoreCase("false")) {
//           // File file = new File("C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\CucumberSample\\target\\allure-results");
//            File file = new File("C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\SpriteCloud\\target\\maven-status");
//            if (file.exists()) {
//                file.setWritable(true);
//                FileUtils.cleanDirectory(file);
//                System.out.println("File deleted");
//            }
//        }
    }

    @After
    public void afterSuite() throws FileNotFoundException {
        driverInit.teardown();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }));
        sendSlackMessage();
    }


    private void sendSlackMessage() throws FileNotFoundException {
        if (Helper.testProp.getProperty("isLocal").equalsIgnoreCase("false")) {
            Slack slack = new Slack();
            Helper helper = new Helper();
            String channelId = helper.testProp.getProperty("slack-channel-id");
            List<String> report = cucumberUtils.getCucumberReportStatuses();
            if (CucumberUtils.totalFailed > 0) {
                for (int i = 0; i < report.size(); i++) {
                    slack.postSimpleMessage(report.get(i), channelId);
                }
            }
        }
    }
}

package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CucumberUtils {
    public static int totalFailed = 0;
    public List<String> getCucumberReportStatuses() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonArray originalJSONArray = parser.parse(new FileReader("target/cucumber.json")).getAsJsonArray();
        JsonArray jsonArray = removeDuplicateScenarios(originalJSONArray);

        int scenarioCount = 0;
        int passedScenarioCount = 0;
        int totalSteps = 0;
        int totalPassed = 0;
        int totalSkipped = 0;

        try {
            for (JsonElement element : jsonArray) {
                JsonObject lines = element.getAsJsonObject();
                JsonArray elements = lines.getAsJsonArray("elements");

                for (JsonElement el : elements) {
                    JsonObject scenarios = el.getAsJsonObject();
                    String elementType = scenarios.get("type").getAsString();

                    if ("scenario".equals(elementType)) {
                        scenarioCount++;

                        JsonArray stepResults = scenarios.getAsJsonArray("steps");
                        boolean isScenarioPassed = true;

                        for (JsonElement step : stepResults) {
                            JsonObject result = step.getAsJsonObject().getAsJsonObject("result");
                            String status = result.get("status").getAsString();
                            totalSteps += 1;
                            if ("passed".equals(status)) {
                                totalPassed++;
                            } else if ("failed".equals(status)) {
                                totalFailed++;
                                isScenarioPassed = false;
                            } else if ("skipped".equals(status)) {
                                totalSkipped++;
                                isScenarioPassed = false;
                            }
                        }

                        if (isScenarioPassed) {
                            passedScenarioCount++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> reportElements = new ArrayList<>();
        reportElements.add("_*Automation Test Report:*_ ");
        reportElements.add("_*Total Scenarios:*_ " + scenarioCount);
        reportElements.add("_*Passed Scenarios:*_ " + passedScenarioCount);
        reportElements.add("_*Total steps:*_ " + totalSteps);
        reportElements.add("_*Total Passed steps:*_ " + totalPassed);
        reportElements.add("_*Total Failed:*_ " + totalFailed);
        reportElements.add("_*Total Skipped:*_ " + totalSkipped);
        return reportElements;
    }

    private JsonArray removeDuplicateScenarios(JsonArray jsonArray) {
        JsonArray cleanedArray = new JsonArray();
        Set<String> processedScenarios = new HashSet<>();

        for (JsonElement element : jsonArray) {
            JsonObject feature = element.getAsJsonObject();
            JsonArray elements = feature.getAsJsonArray("elements");
            JsonArray cleanedElements = new JsonArray();

            for (JsonElement el : elements) {
                JsonObject scenario = el.getAsJsonObject();
                String scenarioName = scenario.get("name").getAsString();

                if (!processedScenarios.contains(scenarioName)) {
                    cleanedElements.add(el);
                    processedScenarios.add(scenarioName);
                }
            }

            feature.add("elements", cleanedElements);
            cleanedArray.add(feature);
        }

        return cleanedArray;
    }

}

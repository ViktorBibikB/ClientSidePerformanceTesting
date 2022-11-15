package navigationtiming;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static driver.DriverSingleton.getDriver;

public class PerfNavigationTiming {
    Map<String, Object> timings = null;

    private static FileWriter fileWriter;
    public static File file;

    private final String javaScriptForPerformance = "var timings = performance.timing || {}; return timings;";
    private final String javaScriptForPerformanceInternetExplorer = "return {performance:JSON.stringify(performance.timing)}";

    public Map<String, Object> getAllTiming() {
        JavascriptExecutor jsrunner = (JavascriptExecutor) getDriver();
        if ("InternetExplorer".equalsIgnoreCase("InternetExplorer")) {
            Map<String, Object> ieTimings = (Map<String, Object>) jsrunner.executeScript(javaScriptForPerformanceInternetExplorer);
            timings = parseNavigationTimingDataFromIe(ieTimings);
        } else {
            timings = (Map<String, Object>) jsrunner.executeScript(javaScriptForPerformance);
        }
        return timings;
    }

    private Map<String, Object> parseNavigationTimingDataFromIe(Map<String, Object> ieTimings) {
        Map<String, Object> parsedTimings = new HashMap<String, Object>();
        String mapValue = (String) ieTimings.get("performance");
        mapValue = mapValue.substring(1, mapValue.length() - 1).replace("\"", "");
        String[] keyValuePairs = mapValue.split(",");

        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            parsedTimings.put(entry[0].trim(), Long.valueOf(entry[1]));
        }
        return parsedTimings;
    }

    public void writeToFile(String pageName, String fileName) throws IOException {
        getAllTiming();
        writeMetricsToJsonFile(pageName, fileName, this.getLatency(), this.getTimeToInteract(),
                this.getTimeToLoad(), this.getOnLoad(), this.getTotal_time());
    }

    private void writeMetricsToJsonFile(String pageName, String fileName, long latency, long tti, long ttl, long onload, long totalTime) throws IOException {
        file = new File(fileName + ".json");

        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject;
        jsonObject = file.exists() ? mapper.readValue(file, JSONObject.class) : new JSONObject();
        JSONArray entityArray = new JSONArray();
        JSONObject innerJsonObject = new JSONObject();
        innerJsonObject.put("latency", latency);
        innerJsonObject.put("tti", tti);
        innerJsonObject.put("ttl", ttl);
        innerJsonObject.put("onload", onload);
        innerJsonObject.put("total_time", totalTime);

        entityArray.put(innerJsonObject);
        jsonObject.put(pageName, entityArray);

        try {
            PerfNavigationTiming.fileWriter = new FileWriter(file);
            PerfNavigationTiming.fileWriter.write(jsonObject.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                PerfNavigationTiming.fileWriter.flush();
                PerfNavigationTiming.fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // raw data
    private Long getAnTime(String nane) {
        return (Long) timings.get(nane);
    }

    public Long getNavigationStart() {
        System.out.println(getAnTime("navigationStart"));
        return getAnTime("navigationStart");
    }

    public Long getResponseStart() {
        return getAnTime("responseStart");
    }

    public Long getResponseEnd() {
        return getAnTime("responseEnd");
    }

    public Long getDomLoading() {
        return getAnTime("domLoading");
    }

    public Long getDomInteractive() {
        return getAnTime("domInteractive");
    }

    public Long getDomComplete() {
        return getAnTime("domComplete");
    }

    public Long getLoadEventStart() {
        return getAnTime("loadEventStart");
    }

    public Long getLoadEventEnd() {
        return getAnTime("loadEventEnd");
    }

    // result
    public long getLatency() {
        return getResponseStart() - getNavigationStart();
    }

    public long getBackend_response() {
        return getResponseEnd() - getResponseStart();
    }

    public long getTimeToInteract() {
        return getDomInteractive() - getDomLoading();
    }

    public long getTimeToLoad() {
        return getDomComplete() - getDomInteractive();
    }

    public long getOnLoad() {
        return getLoadEventEnd() - getLoadEventStart();
    }

    public long getTotal_time() {
        return getLoadEventEnd() - getNavigationStart();
    }
}

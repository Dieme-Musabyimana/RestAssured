import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonDataCollector {

    List<String> keys = new ArrayList<>();
    List<Object> values = new ArrayList<>();
    @Test
    public void fetchData() {
        String jsonBody = "[{\"k\":[1,3,5],\"k1\":{\"k10\":4,\"k11\":[4,7,9],\"k12\":{\"k120\":{\"k121\":\"v121\"}},\"k14\":6}},{\"k22\":{\"k221\":\"v122\"}}]";

        JsonPath jp = new JsonPath(jsonBody);
        List<Map<String, Object>> rootArray = jp.getList("$");

        for (Map<String, Object> map : rootArray) {
            fetchNestedData(map);
        }

        System.out.println("keys = " + keys);
        System.out.println("values = " + values);
    }

    public void fetchNestedData(Map<String, Object> data) {
        data.forEach((k, v) -> {
            keys.add(k);

            if (v instanceof Map) {
                fetchNestedData((Map<String, Object>) v);
            } else {
                values.add(v);
            }
        });
    }
}
package Core.DataReaderManager;

import Core.LogManager.LogManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

    private final String Test_Data_Path = "src/test/resources/DataForTest/";
    //لتخزين  محتوي json الي string
    private String jsonReader;
    //اسم الملف الي هتقراه
    private String jsonFileName;

    public JsonReader(String jasonFileName) {
        this.jsonFileName = jasonFileName;
        try {
            //افتح الملف الي عايز اقراه وهات محتواه
            JSONObject data = (JSONObject) new JSONParser().
                    parse(new FileReader(Test_Data_Path + jasonFileName + ".json"));
            //حولو الي string
            jsonReader = data.toJSONString();

        } catch (Exception e) {
            LogManager.Error("Error reading JSON file: ", jasonFileName, e.getMessage());
        }
    }


    public String getJsonData(String jasonPath) {
        try {
            return JsonPath.read(jsonReader, jasonPath).toString();
        } catch (Exception e) {
            LogManager.Error("Error retrieving JSON data for path: ", jasonPath, e.getMessage());
            return "";
        }
    }

}

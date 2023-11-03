package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    //<T> T  --> herhangi bir veri tipini temsil eder. (Generic)
    //readValue() methodu birinci parametrede aldigi String datayi, ikinci parametrede belirttigimiz data tipine cevirir.
    public static <T> T convertJsonToJava(String json, Class<T> cls){     //Generic method
        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

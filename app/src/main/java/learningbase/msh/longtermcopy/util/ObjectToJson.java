package learningbase.msh.longtermcopy.util;

/**
 * TODO
 * author : 马世豪 29350
 * time   : 2018/04/12
 * version: 1.0
 */

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import learningbase.msh.longtermcopy.ClientMessage;

public class ObjectToJson {

    /**
     * javabean to json
     *
     * @param person
     * @return
     */
    public static String javabeanToJson(Object person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        return json;
    }

    /**
     * list to json
     *
     * @param list
     * @return
     */
    public static String listToJson(List<ClientMessage> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * map to json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, ClientMessage> map) {

        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }
}

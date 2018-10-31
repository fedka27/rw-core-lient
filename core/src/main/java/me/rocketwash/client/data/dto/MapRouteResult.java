package me.rocketwash.client.data.dto;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aratj on 20.09.2015.
 */
public class MapRouteResult {

    private String stringData;
    private List<List<HashMap<String, String>>> data;

    public List<List<HashMap<String, String>>> getData() {
        return data;
    }

    public void setData(List<List<HashMap<String, String>>> data) {
        this.data = data;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}

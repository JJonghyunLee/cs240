package model;

import java.util.ArrayList;
import java.util.List;

public class LocationData {
    List<Location> data = new ArrayList<>();

    public LocationData(List<Location> list) {
        this.data = list;
    }

    public List<Location> getList() {
        return data;
    }

    public void setList(List<Location> list) {
        this.data = list;
    }
}

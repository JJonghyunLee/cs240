package model;

import java.util.ArrayList;
import java.util.List;

public class NameData {
    private List<String> data = new ArrayList<>();

    public NameData(List<String> list) {
        this.data = list;
    }

    public List<String> getList() {
        return data;
    }

    public void setList(List<String> list) {
        this.data = list;
    }
}

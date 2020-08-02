package com.example.vactracker.ui.datamap;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("objs")
    @Expose
    private List<MapObj> objs = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("hasMore")
    @Expose
    private Boolean hasMore;

    public List<MapObj> getObjs() {
        return objs;
    }

    public void setObjs(List<MapObj> objs) {
        this.objs = objs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

}
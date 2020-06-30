package com.example.vactracker.ui.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vaccine {

    @SerializedName("objs")
    @Expose
    private List<Obj> objs = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("hasMore")
    @Expose
    private Boolean hasMore;

    /**
     * No args constructor for use in serialization
     *
     */
    public Vaccine() {
    }

    /**
     *
     * @param count
     * @param hasMore
     * @param objs
     */
    public Vaccine(List<Obj> objs, Integer count, Boolean hasMore) {
        super();
        this.objs = objs;
        this.count = count;
        this.hasMore = hasMore;
    }

    public List<Obj> getObjs() {
        return objs;
    }

    public void setObjs(List<Obj> objs) {
        this.objs = objs;
    }

    public Vaccine withObjs(List<Obj> objs) {
        this.objs = objs;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Vaccine withCount(Integer count) {
        this.count = count;
        return this;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Vaccine withHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

}
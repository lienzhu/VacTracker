package com.example.vactracker.ui.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("tenantTagId")
    @Expose
    private Integer tenantTagId;
    @SerializedName("tenant")
    @Expose
    private String tenant;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("fetchInclude")
    @Expose
    private String fetchInclude;
    @SerializedName("fetchType")
    @Expose
    private String fetchType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Meta() {
    }

    /**
     *
     * @param fetchInclude
     * @param updatedBy
     * @param createdBy
     * @param created
     * @param tenantTagId
     * @param tag
     * @param fetchType
     * @param updated
     * @param tenant
     * @param timestamp
     */
    public Meta(Integer tenantTagId, String tenant, String tag, String created, String createdBy, String updated, String updatedBy, String timestamp, String fetchInclude, String fetchType) {
        super();
        this.tenantTagId = tenantTagId;
        this.tenant = tenant;
        this.tag = tag;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.timestamp = timestamp;
        this.fetchInclude = fetchInclude;
        this.fetchType = fetchType;
    }

    public Integer getTenantTagId() {
        return tenantTagId;
    }

    public void setTenantTagId(Integer tenantTagId) {
        this.tenantTagId = tenantTagId;
    }

    public Meta withTenantTagId(Integer tenantTagId) {
        this.tenantTagId = tenantTagId;
        return this;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Meta withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Meta withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Meta withCreated(String created) {
        this.created = created;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Meta withCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Meta withUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Meta withUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Meta withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getFetchInclude() {
        return fetchInclude;
    }

    public void setFetchInclude(String fetchInclude) {
        this.fetchInclude = fetchInclude;
    }

    public Meta withFetchInclude(String fetchInclude) {
        this.fetchInclude = fetchInclude;
        return this;
    }

    public String getFetchType() {
        return fetchType;
    }

    public void setFetchType(String fetchType) {
        this.fetchType = fetchType;
    }

    public Meta withFetchType(String fetchType) {
        this.fetchType = fetchType;
        return this;
    }

}
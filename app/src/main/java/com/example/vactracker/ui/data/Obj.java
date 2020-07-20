package com.example.vactracker.ui.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Obj {

    @SerializedName("productType")
    @Expose
    private String productType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("developer")
    @Expose
    private String developer;
    @SerializedName("stageOfDevelopment")
    @Expose
    private String stageOfDevelopment;
    @SerializedName("therapyType")
    @Expose
    private String therapyType;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("target")
    @Expose
    private String target;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("clinicalTrialsOtherDiseases")
    @Expose
    private String clinicalTrialsOtherDiseases;
    @SerializedName("nextSteps")
    @Expose
    private String nextSteps;

    @SerializedName("fundingSources")
    @Expose
    private String fundingSources;

    /**
     * No args constructor for use in serialization
     *
     */
    public Obj() {
    }

    /**
     *
     * @param clinicalTrialsOtherDiseases
     * @param meta
     * @param origin
     * @param description
     * @param developer
     * @param therapyType
     * @param id
     * @param version
     * @param productType
     * @param stageOfDevelopment
     * @param target
     * @param nextSteps
     * @param fundingSources
     */
    public Obj(String productType, String description, String developer, String stageOfDevelopment, String nextSteps, String therapyType, String origin, String target, String id, Meta meta, Integer version, String clinicalTrialsOtherDiseases, String fundingSources) {
        super();
        this.productType = productType;
        this.description = description;
        this.developer = developer;
        this.stageOfDevelopment = stageOfDevelopment;
        this.therapyType = therapyType;
        this.origin = origin;
        this.target = target;
        this.id = id;
        this.meta = meta;
        this.version = version;
        this.clinicalTrialsOtherDiseases = clinicalTrialsOtherDiseases;
        this.nextSteps = nextSteps;
        this.fundingSources = fundingSources;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Obj withProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Obj withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Obj withDeveloper(String developer) {
        this.developer = developer;
        return this;
    }

    public String getStageOfDevelopment() {
        return stageOfDevelopment;
    }

    public void setStageOfDevelopment(String stageOfDevelopment) {
        this.stageOfDevelopment = stageOfDevelopment;
    }

    public Obj withStageOfDevelopment(String stageOfDevelopment) {
        this.stageOfDevelopment = stageOfDevelopment;
        return this;
    }

    public String getTherapyType() {
        return therapyType;
    }

    public void setTherapyType(String therapyType) {
        this.therapyType = therapyType;
    }

    public Obj withTherapyType(String therapyType) {
        this.therapyType = therapyType;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Obj withOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Obj withTarget(String target) {
        this.target = target;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Obj withId(String id) {
        this.id = id;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Obj withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Obj withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getClinicalTrialsOtherDiseases() {
        return clinicalTrialsOtherDiseases;
    }

    public void setClinicalTrialsOtherDiseases(String clinicalTrialsOtherDiseases) {
        this.clinicalTrialsOtherDiseases = clinicalTrialsOtherDiseases;
    }

    public Obj withClinicalTrialsOtherDiseases(String clinicalTrialsOtherDiseases) {
        this.clinicalTrialsOtherDiseases = clinicalTrialsOtherDiseases;
        return this;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public Obj withNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
        return this;
    }

    public String getFundingSources() {
        return fundingSources;
    }

    public void setFundingSources(String fundingSources) {
        this.fundingSources = fundingSources;
    }
}
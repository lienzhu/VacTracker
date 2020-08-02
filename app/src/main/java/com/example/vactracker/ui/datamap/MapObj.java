package com.example.vactracker.ui.datamap;

import android.location.Location;

import com.example.vactracker.ui.data.Meta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapObj {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("pdf")
    @Expose
    private String pdf;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("trialStatus")
    @Expose
    private String trialStatus;
    @SerializedName("design")
    @Expose
    private String design;
    @SerializedName("blinding")
    @Expose
    private String blinding;
    @SerializedName("arms")
    @Expose
    private Integer arms;
    @SerializedName("covid19Status")
    @Expose
    private String covid19Status;
    @SerializedName("patientSetting")
    @Expose
    private String patientSetting;
    @SerializedName("outcome")
    @Expose
    private String outcome;
    @SerializedName("treatmentType")
    @Expose
    private String treatmentType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("version")
    @Expose
    private Integer version;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTrialStatus() {
        return trialStatus;
    }

    public void setTrialStatus(String trialStatus) {
        this.trialStatus = trialStatus;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getBlinding() {
        return blinding;
    }

    public void setBlinding(String blinding) {
        this.blinding = blinding;
    }

    public Integer getArms() {
        return arms;
    }

    public void setArms(Integer arms) {
        this.arms = arms;
    }

    public String getCovid19Status() {
        return covid19Status;
    }

    public void setCovid19Status(String covid19Status) {
        this.covid19Status = covid19Status;
    }

    public String getPatientSetting() {
        return patientSetting;
    }

    public void setPatientSetting(String patientSetting) {
        this.patientSetting = patientSetting;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}


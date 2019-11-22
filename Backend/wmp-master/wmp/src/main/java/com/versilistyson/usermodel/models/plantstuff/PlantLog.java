package com.versilistyson.usermodel.models.plantstuff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.versilistyson.usermodel.models.common.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "PLANT_LOG")
public class PlantLog extends PersistentObject {

    private int mood;

    private String photo = "";

    private String description = "";

    @Column(name = "log_date")
    private Date logDate = new Date();

    @Column(name = "was_watered", nullable = false)
    boolean watered;

    @Column(name = "was_pruned", nullable = false)
    boolean pruned;

    @Column(name = "was_refertilized", nullable = false)
    boolean refertilized;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;

    public PlantLog() {}

    public PlantLog(int mood, String photo, String description, boolean watered, boolean pruned, boolean refertilized) {
        this.mood = mood;
        this.photo = photo;
        this.description = description;
        this.watered = watered;
        this.pruned = pruned;
        this.refertilized = refertilized;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public boolean isWatered() {
        return watered;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }

    public boolean isPruned() {
        return pruned;
    }

    public void setPruned(boolean pruned) {
        this.pruned = pruned;
    }

    public boolean isRefertilized() {
        return refertilized;
    }

    public void setRefertilized(boolean refertilized) {
        this.refertilized = refertilized;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}

package com.versilistyson.usermodel.models.plantstuff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.versilistyson.usermodel.models.User;
import com.versilistyson.usermodel.models.common.PersistentObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PLANT")
public class Plant extends PersistentObject {

    @Column(nullable = false)
    private String nickname = " ";

    private String photo = " ";

    @Column(nullable = false, name = "plant_type")
    private String plantType = " ";

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    @JsonIgnore
    private User user = new User();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "plant") // FetchType.LAZY loads them when getPlantLogs is called
    @JsonIgnoreProperties("user_plant_id")
    @Column(name = "plant_logs")
    private List<PlantLog> plantLogs = new ArrayList<>();

    public Plant() {
    }

    public Plant(String nickname, String photo, String plantType) {
        this.nickname = nickname;
        this.photo = photo;
        this.plantType = plantType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PlantLog> getPlantLogs() {
        return plantLogs;
    }

    public void setPlantLogs(List<PlantLog> plantLogs) {
        this.plantLogs = plantLogs;
    }
}

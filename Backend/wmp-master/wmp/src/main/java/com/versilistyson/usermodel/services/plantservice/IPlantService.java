package com.versilistyson.usermodel.services.plantservice;

import com.versilistyson.usermodel.models.User;
import com.versilistyson.usermodel.models.plantstuff.Plant;
import com.versilistyson.usermodel.models.plantstuff.PlantLog;

import java.util.List;

public interface IPlantService {

    List<PlantLog> findLogsByPlantID(long plantID);
    List<Plant> findAllPlantsByUserID(long userID);
    Plant findPlantByID(long plantID);
    long findUserIDByUsername(String username);

    Plant savePlant(long userID, Plant newPlant);
    Plant updatePlantPhoto(String newPhoto, long plantID);
    Plant updatePlantName(String newNickname, long plantID);
    void deletePlantByID(long plantID);

    Plant savePlantLog(long plantID, PlantLog newPlantLog);
    void deletePlantLogByID(long plantID);

}

package com.versilistyson.usermodel.services.plantservice;

import com.versilistyson.usermodel.exceptions.ResourceFoundException;
import com.versilistyson.usermodel.exceptions.ResourceNotFoundException;
import com.versilistyson.usermodel.models.User;
import com.versilistyson.usermodel.models.plantstuff.Plant;
import com.versilistyson.usermodel.models.plantstuff.PlantLog;
import com.versilistyson.usermodel.repository.PlantLogRepository;
import com.versilistyson.usermodel.repository.PlantRepository;
import com.versilistyson.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service(value = "plantService")
public class PlantService implements IPlantService {

    @Autowired
    PlantRepository plantRepo;

    @Autowired
    PlantLogRepository plantLogRepo;

    @Autowired
    UserRepository userRepo;



    @Override
    public List<PlantLog> findLogsByPlantID(long plantID) {
       Plant plant = plantRepo.findById(plantID).orElseThrow(
               () -> new ResourceNotFoundException("No plant with this id found")
       );
       return plant.getPlantLogs();
    }

    @Override
    public List<Plant> findAllPlantsByUserID(long userID) {
        List<Plant> plants = plantRepo.findAllByUser_Userid(userID);
        return  plants;
    }

    @Override
    public Plant findPlantByID(long plantID) {
        return plantRepo.findById(plantID).orElseThrow(
                () -> new ResourceNotFoundException("Plant by id not found")
        );
    }

    @Override
    public long findUserIDByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return user.getUserid();
    }

    @Transactional
    @Override
    public Plant savePlant(long userID, Plant newPlant) {
        User user = userRepo.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("UserID does not exist")
        );
        newPlant.setUser(user);
        return plantRepo.save(newPlant);
    }

    @Transactional
    @Override
    public Plant updatePlantPhoto(String newPhoto, long plantID) {
        Plant plantToUpdate = plantRepo.findById(plantID).orElseThrow(
                () -> new ResourceNotFoundException("no plant with this id found")
        );

        plantToUpdate.setPhoto(newPhoto);
        return plantRepo.save(plantToUpdate);
    }

    @Transactional
    @Override
    public Plant updatePlantName(String newNickname, long plantID) {
        Plant plantToUpdate = plantRepo.findById(plantID).orElseThrow(
                () -> new ResourceNotFoundException("no plant with this id found")
        );

        plantToUpdate.setNickname(newNickname.trim());
        return plantRepo.save(plantToUpdate);
    }

    @Transactional
    @Override
    public void deletePlantByID(long plantID) {
        Plant plantToDelete = plantRepo.findById(plantID).orElseThrow(
                () -> new ResourceNotFoundException("no plant with this id found")
        );
        plantRepo.delete(plantToDelete);
    }

    @Transactional
    @Override
    public Plant savePlantLog(long plantID, PlantLog newPlantLog) {

        if(newPlantLog.getMood() < 1 || newPlantLog.getMood() > 3) {
            throw new ResourceFoundException("Set the mood between 1 - 3 ");
        }
        Plant plantToUpdate = plantRepo.findById(plantID).orElseThrow(
                () -> new ResourceNotFoundException("Plant ID was not found")
        );
        Date logDate = new Date();
        newPlantLog.setLogDate(logDate);
        newPlantLog.setPlant(plantToUpdate);

        plantToUpdate.getPlantLogs().add(newPlantLog);
        return plantRepo.save(plantToUpdate);
    }

    @Transactional
    @Override
    public void deletePlantLogByID(long plantID) {
        plantLogRepo.deleteById(plantID);
    }
}

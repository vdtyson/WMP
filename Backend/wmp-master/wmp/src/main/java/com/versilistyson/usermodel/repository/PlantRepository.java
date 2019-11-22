package com.versilistyson.usermodel.repository;

import com.versilistyson.usermodel.models.plantstuff.Plant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends CrudRepository<Plant, Long> {
    List<Plant> findAllByUser_Userid(long userID);
}

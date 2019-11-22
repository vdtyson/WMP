package com.versilistyson.usermodel.repository;

import com.versilistyson.usermodel.models.plantstuff.PlantLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlantLogRepository extends CrudRepository<PlantLog, Long> {

}

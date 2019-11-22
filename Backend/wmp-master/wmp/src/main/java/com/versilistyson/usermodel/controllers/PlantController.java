package com.versilistyson.usermodel.controllers;

import com.versilistyson.usermodel.models.plantstuff.Plant;
import com.versilistyson.usermodel.models.plantstuff.PlantLog;
import com.versilistyson.usermodel.services.plantservice.IPlantService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Api(tags = "PlantEndpoints")
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    IPlantService plantService;

    // https://vdtyson-watermyplants.herokuapp.com/plants/user/{userID}
    // WORKS
    @ApiOperation(value = "Retrieve all user plants by id", response = Plant.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Plants(s) found", response = Plant.class, responseContainer = "List")
    @GetMapping(
            value = "user/{userID}",
            produces = {"application/json"}
    )
    public ResponseEntity<?> getPlantsByUserID(
            HttpServletRequest request,
            @PathVariable long userID
    ) {
        List<Plant> userPlants = plantService.findAllPlantsByUserID(userID);
        return new ResponseEntity<>(userPlants, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}/logs
    // Photos
    @ApiOperation(value = "Get logs for plant by plant ID")
    @GetMapping(
            value = "/{plantID}/logs",
            produces = {"application/json"}
    )
    public ResponseEntity<?> getLogsByPlantID(
            HttpServletRequest request,
            @PathVariable long plantID
    ) {
        List<PlantLog> plantLogs = plantService.findLogsByPlantID(plantID);
        return new ResponseEntity<>(plantLogs, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}
    //WORKS
    @ApiOperation(value = "Get plant by plant id")
    @GetMapping(
            value = "/{plantID}",
            produces = {"application/json"}
    )
    public ResponseEntity<?> getPlantByID(
            HttpServletRequest request,
            @PathVariable long plantID
    ) {
        Plant plant = plantService.findPlantByID(plantID);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{userID}
    //WORKS
    @ApiOperation(value = "Save new plant.")
    @PostMapping(
            value = "/{userID}",
            consumes = {"application/json"}
    )
    public ResponseEntity<?> saveNewPlant(
            HttpServletRequest request,
            @ApiParam(value = "User ID", required = true, example = "4") @PathVariable long userID,
            @ApiParam(value = "New plant to save.")
            @Valid @RequestBody Plant newPlant
    ) throws URISyntaxException {
        Plant plant = plantService.savePlant(userID, newPlant);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPlantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{plantid}")
                .buildAndExpand(plant.getId())
                .toUri();

        responseHeaders.setLocation(newPlantURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}
    // WORKS
    @ApiOperation(value = "Delete plant by plant id")
    @DeleteMapping(value = "/{plantID}")
    public ResponseEntity<?> deletePlantByID(
            HttpServletRequest request,
            @PathVariable long plantID
    ) {
        plantService.deletePlantByID(plantID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}/photo
    @ApiOperation(value = "Update plant photo")
    @PutMapping(
            value = "/{plantID}/photo",
            consumes = {"application/json"}
    )
    public ResponseEntity<?> updatePlantPhoto(
            HttpServletRequest request,
            @RequestBody String photo,
            @PathVariable long plantID
    ) {
        Plant plant = plantService.updatePlantPhoto(photo, plantID);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}/nickname
    // WORKING
    @ApiOperation(value = "Update plant nickname")
    @PutMapping(
            value = "/{plantID}/nickname",
            consumes = {"application/json"}
    )
    public ResponseEntity<?> updatePlantNickname(
            HttpServletRequest request,
            @RequestBody String nickname,
            @PathVariable long plantID
    ) {
        Plant plant = plantService.updatePlantName(nickname, plantID);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/{plantID}/log
    // WORKS
    @ApiOperation(value = "Add a new plant log")
    @PutMapping(
            value = "/{plantID}/log",
            consumes = {"application/json"}
    )
    public ResponseEntity<?> createNewPlantLog(
            HttpServletRequest request,
            @RequestBody PlantLog newPlantLog,
            @PathVariable long plantID
    ) {
        plantService.savePlantLog(plantID, newPlantLog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/plant/{logID}
    // WORKS
    @ApiOperation(value = "Delete plant log by log id")
    @DeleteMapping(
            value = "/plant/{logID}"
    )
    public ResponseEntity<?> deletePlantLog(
            HttpServletRequest request,
            @PathVariable long logID
    ){
        plantService.deletePlantLogByID(logID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // https://vdtyson-watermyplants.herokuapp.com/plants/username/{username}
    @ApiOperation(value = "Get ID for user by username")
    @GetMapping(
            value = "username/{username}"
    )
    public ResponseEntity<?> findIdByUsername(
            HttpServletRequest request,
            @PathVariable String username
    ) {
       long userId =  plantService.findUserIDByUsername(username);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}

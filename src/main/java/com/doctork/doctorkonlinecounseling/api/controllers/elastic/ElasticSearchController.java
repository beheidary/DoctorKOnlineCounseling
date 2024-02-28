package com.doctork.doctorkonlinecounseling.api.controllers.elastic;

import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


//@Api(value = "Donation Controller",produces = "Application/json")
@Controller
@RequestMapping("/api/doctors")
public class ElasticSearchController {


    private final ElasticService elasticService;


    public ElasticSearchController(ElasticService elasticService) {
        this.elasticService = elasticService;

    }

    @GetMapping("/search")
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> findByFullNameAndSpeciality(@RequestParam("queryString") String queryString) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<Doctor> doctors = elasticService.search(queryString);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctors));

        return result;

    }

    @DeleteMapping(value = "/doctorIndex/delete/{id}")
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> removeDoctor(@PathVariable String id)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ElasticDoctorEntity elasticDoctorEntity = elasticService.deleteDoctor(id);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));

        return result;

    }
    @PutMapping(value = "/doctorIndex/edit/{id}")
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editDoctor(@PathVariable String id,
                                                 @RequestBody @Validated ElasticDoctorEntity elasticDoctorEntity)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        elasticDoctorEntity = elasticService.editDoctor(id , elasticDoctorEntity);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));

        return result;

    }
}

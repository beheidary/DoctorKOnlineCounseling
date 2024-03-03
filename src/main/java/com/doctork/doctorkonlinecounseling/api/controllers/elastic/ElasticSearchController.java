package com.doctork.doctorkonlinecounseling.api.controllers.elastic;

import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.List;


//@Api(value = "doctorK Index Controller",produces = "Application/json")
@Controller
@RequestMapping("/api/doctors")
public class ElasticSearchController {


    private final ElasticService elasticService;


    public ElasticSearchController(ElasticService elasticService) {
        this.elasticService = elasticService;

    }

    @GetMapping("/search")
//    @ApiOperation(value = "Search on elastic engine", response = DonorOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> findByFullNameAndSpeciality(@RequestParam("queryString") String queryString) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<Doctor> doctors = elasticService.search(queryString);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctors));

        return result;

    }

    @DeleteMapping(value = "/doctorIndex/delete/{id}")
    //    @ApiOperation(value = "delete a doctor entity", response = DonorOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> removeDoctor(@PathVariable String id)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ElasticDoctorEntity elasticDoctorEntity = elasticService.deleteDoctor(id);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));

        return result;

    }
    @PutMapping(value = "/doctorIndex/edit/{id}")
    //    @ApiOperation(value = "edit entity", response = DonorOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editDoctor(@PathVariable String id,
                                                 @RequestBody @Validated ElasticDoctorEntity elasticDoctorEntity)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        elasticDoctorEntity = elasticService.editDoctor(id , elasticDoctorEntity);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));

        return result;

    }

    @PostMapping(value = "/doctorIndex/add")
    public @ResponseBody
        //    @ApiOperation(value = "add doctor entity", response = DonorOutputDTO.class)
    DeferredResult<ResponseEntity<?>> addDoctor(@RequestBody @Validated Doctor doctor)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        doctor = elasticService.addDoctor(doctor);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctor));

        return result;

    }
}

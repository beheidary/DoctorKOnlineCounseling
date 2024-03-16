package com.doctork.doctorkonlinecounseling.api.controllers.elastic;

import com.doctork.doctorkonlinecounseling.api.adapters.Elastic.ElasticAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchResultDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SuggestOutputDTO;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;



@Api(value = "doctorK Index Controller",produces = "Application/json")
@RestController()
@OpenAPIDefinition(
        info = @Info(title = "Doctor",version = "0.0",description = "dESCRIPTION")
)
@RequestMapping(value = "/elastic/doctor", produces = "application/json", headers = {"Accept-Language"})
public class ElasticSearchController {

    private final ElasticAdapter elasticAdapter;

    public ElasticSearchController(ElasticAdapter elasticAdapter) {
        this.elasticAdapter = elasticAdapter;
    }


    @GetMapping("/search")
    @ApiOperation(value = "Search on elastic engine", response = SearchResultDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> findByNameAndSpeciality(@RequestParam("queryString") String queryString,
                                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer pageNumber,
                                                              @RequestParam(defaultValue = "10") @Positive Integer pageSize) throws IOException {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        SearchResultDTO searchResultDTO = elasticAdapter.search(queryString,pageNumber,pageSize);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(searchResultDTO));
        return result;
    }

    @GetMapping("/suggest")
    @ApiOperation(value = "Search on elastic engine", response = SuggestOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> suggestBySpeciality(@RequestParam("queryString") String queryString) throws IOException {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        SuggestOutputDTO suggestOutputDTO = elasticAdapter.TermSuggest(queryString);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(suggestOutputDTO));
        return result;
    }

    @DeleteMapping(value = "/doctorIndex/delete/{id}")
    @ApiOperation(value = "delete a doctor entity", response = ElasticDoctorEntity.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> removeDoctor(@PathVariable String id)
    {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        ElasticDoctorEntity elasticDoctorEntity = elasticAdapter.deleteDoctor(id);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));
        return result;
    }

    @PutMapping(value = "/doctorIndex/edit/{id}")
    @ApiOperation(value = "edit entity", response = ElasticDoctorEntity.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editDoctor(@PathVariable String id,
                                                 @RequestBody @Validated ElasticDoctorEntity elasticDoctorEntity)
    {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        elasticDoctorEntity = elasticAdapter.editDoctor(id , elasticDoctorEntity);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(elasticDoctorEntity));
        return result;
    }

    @PostMapping(value = "/doctorIndex/add")
    public @ResponseBody
    @ApiOperation(value = "add doctor entity", response = Doctor.class)
    DeferredResult<ResponseEntity<?>> addDoctor(@RequestBody @Validated Doctor doctor)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        doctor = elasticAdapter.addDoctor(doctor);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctor));
        return result;
    }
}
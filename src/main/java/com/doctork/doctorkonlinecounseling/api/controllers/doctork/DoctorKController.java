package com.doctork.doctorkonlinecounseling.api.controllers.doctork;

import com.doctork.doctorkonlinecounseling.api.adapters.Expertise.ExpertiseAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.doctor.DoctorAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;


@Controller
@RequestMapping("/")
public class DoctorKController {



    private final DoctorAdapter doctorAdapter;
    private final ExpertiseAdapter expertiseAdapter;


    public DoctorKController(DoctorAdapter doctorAdapter,ExpertiseAdapter expertiseAdapter) {
        this.doctorAdapter = doctorAdapter;
        this.expertiseAdapter = expertiseAdapter;

    }

    @GetMapping(value = "doctor/{physicianSystemCode}")
    //    @ApiOperation(value = "delete a doctor entity", response = DonorOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchDoctor(@PathVariable String physicianSystemCode)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.fetchDoctor(physicianSystemCode);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

    @PostMapping(value = "doctor/")
    public @ResponseBody
        //    @ApiOperation(value = "add doctor entity", response = DonorOutputDTO.class)
    DeferredResult<ResponseEntity<?>> addDoctor(@RequestBody @Validated DoctorInputDTO doctorInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.addDoctor(doctorInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

    @PostMapping(value = "doctor/addDoctorExpertise/{PSCode}")
    public @ResponseBody
        //    @ApiOperation(value = "add doctor entity", response = DonorOutputDTO.class)
    DeferredResult<ResponseEntity<?>> addDoctorExpertise(@PathVariable String PSCode, @RequestBody @Validated ExpertiseInputDTO expertiseInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDTO expertiseOutputDTO = doctorAdapter.addDoctorExpertise(PSCode, expertiseInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDTO));

        return result;

    }


    @GetMapping(value = "/expertise/")
    @ApiOperation(value = "All Expertise", response = ExpertiseOutputDTO.class)
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertise(/*@RequestParam(required = false) String city */){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<ExpertiseOutputDTO> expertises = expertiseAdapter.getExpertises();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }

}

package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.Patient.PatientAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.PatientOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@EnableMethodSecurity
@RequestMapping("/")
@SecurityRequirement(name = "security_auth")
public class PatientController {


    private final PatientAdapter patientAdapter;


    public PatientController(PatientAdapter patientAdapter) {

        this.patientAdapter = patientAdapter;

    }



    @PostMapping(value = "Patient")
    @PreAuthorize("hasRole('ROLE_Patient')")
    @Operation(summary = "Complete Patient Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> PatientCoP(@RequestBody @Validated PatientInputDto patientInputDto)
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PatientOutputDto patientOutputDto = patientAdapter.patientCompleteProfile(patientInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(patientOutputDto));

        return result;

    }



    @GetMapping(value = "Patient/{nationalCode}")
    @PreAuthorize("hasRole('ROLE_Patient')")
    @Operation(summary = "fetch a Patient")
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchPatient(@Validated @PathVariable String nationalCode )
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PatientOutputDto patientOutputDto = patientAdapter.fetchPatient(nationalCode);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(patientOutputDto));

        return result;

    }

}


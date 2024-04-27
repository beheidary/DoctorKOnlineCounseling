package com.doctork.doctorkonlinecounseling.api.controllers.doctork;

import com.doctork.doctorkonlinecounseling.api.adapters.Expertise.ExpertiseAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.doctor.DoctorAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

import java.util.List;


@Controller
@EnableMethodSecurity
@RequestMapping("/")
@SecurityRequirement(name = "security_auth")
public class DoctorKController {



    private final DoctorAdapter doctorAdapter;
    private final ExpertiseAdapter expertiseAdapter;


    public DoctorKController(DoctorAdapter doctorAdapter,ExpertiseAdapter expertiseAdapter) {
        this.doctorAdapter = doctorAdapter;
        this.expertiseAdapter = expertiseAdapter;

    }

    @PreAuthorize("hasRole('ROLE_Patient')")
    @GetMapping(value = "doctor/{physicianSystemCode}")
    @Operation(summary = "Fetch a Doctor")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchDoctor(@PathVariable String physicianSystemCode)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.fetchDoctor(physicianSystemCode);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

    @PostMapping(value = "doctor/")
    @PreAuthorize("hasRole('ROLE_Admin')")
    @Operation(summary = "Complete Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addDoctor(@RequestBody @Validated DoctorInputDTO doctorInputDTO)
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.addDoctor(doctorInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

    @PutMapping(value = "doctor/")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Edit Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editDoctor(@RequestBody @Validated DoctorInputDTO doctorInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.editDoctor(doctorInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }


    @PostMapping(value = "doctor/addDoctorExpertise/{PSCode}")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Add Doctor Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addDoctorExpertise(@PathVariable String PSCode, @RequestBody @Validated ExpertiseInputDTO expertiseInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDTO expertiseOutputDTO = doctorAdapter.addDoctorExpertise(PSCode, expertiseInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDTO));

        return result;

    }


    @GetMapping(value = "/expertise/")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Get All Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertises(/*@RequestParam(required = false) String city */){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<ExpertiseOutputDTO> expertises = expertiseAdapter.getExpertises();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }

    @GetMapping(value = "/expertise/{lotinName}")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Get a Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertise(@PathVariable ExpertiseLatinNames lotinName){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDTO expertise = expertiseAdapter.getExpertise(lotinName);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertise));

        return result;

    }

    @GetMapping(value = "doctor/changestatus/{physicianSystemCode}/{status}")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Change Doctor Status")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDTO.class)) })
    DeferredResult<ResponseEntity<?>> changeStatus(@Validated @PathVariable String physicianSystemCode , @Validated @PathVariable DoctorStatus status)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.changeStatus(physicianSystemCode,status);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

}

package com.doctork.doctorkonlinecounseling.api.controllers.doctork;

import com.doctork.doctorkonlinecounseling.api.adapters.Expertise.ExpertiseAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.Patient.PatientAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.doctor.DoctorAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.user.PatientOutputDto;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
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

    private final PatientAdapter patientAdapter;


    public DoctorKController(PatientAdapter patientAdapter, DoctorAdapter doctorAdapter,ExpertiseAdapter expertiseAdapter) {
        this.doctorAdapter = doctorAdapter;
        this.patientAdapter = patientAdapter;
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
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Complete Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> DoctorCoP(@RequestBody @Validated DoctorInputDTO doctorInputDTO)
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.doctorCompleteProfile(doctorInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(doctorOutputDTO));

        return result;

    }

    @PutMapping(value = "doctor/")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Edit Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorOutputDTO.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> DoctorEP(@RequestBody @Validated DoctorInputDTO doctorInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        DoctorOutputDTO doctorOutputDTO = doctorAdapter.doctorEditProfile(doctorInputDTO);

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
    @GetMapping(value = "/TopExpertisesPhysicians")
    @PreAuthorize("hasRole('ROLE_Patient')")
    @Operation(summary = "Top Expertises Physicians")
    @ApiResponse(content = { @Content(mediaType = "application/json")})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> TopExpertisesPhysicians(){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<TopExpertisesDto> expertises = doctorAdapter.findBestDoctorByExpertise();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }

    @GetMapping(value = "/TopPhysicians")
    @PreAuthorize("hasRole('ROLE_Patient')")
    @Operation(summary = "Top Physicians")
    @ApiResponse(content = { @Content(mediaType = "application/json")})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> TopPhysicians(){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<DoctorOutputDTO> expertises = doctorAdapter.topDoctors();

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


    @PostMapping(value = "Patient/")
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

}

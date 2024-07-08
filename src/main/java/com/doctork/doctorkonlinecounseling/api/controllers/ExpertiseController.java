package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.Expertise.ExpertiseAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.Physician.PhysicianAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.RequestedExpertiseDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Set;


@Controller
@EnableMethodSecurity
@RequestMapping("/")
public class ExpertiseController extends BaseController {


    private final ExpertiseAdapter expertiseAdapter;


    public ExpertiseController(ExpertiseAdapter expertiseAdapter) {
        this.expertiseAdapter = expertiseAdapter;

    }



    @GetMapping(value = "/expertise/{lotinName}")
    @Operation(summary = "Get a Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertise(@PathVariable String lotinName){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDto expertise = expertiseAdapter.getExpertise(lotinName);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertise));

        return result;

    }


    @GetMapping(value = "/expertise/TopExpertisesPhysicians")
    @Operation(summary = "Top Physicians of each Expertises ")
    @ApiResponse(content = { @Content(mediaType = "application/json")})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> TopExpertisesPhysicians(){

            DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<TopExpertisesDto> expertises = expertiseAdapter.findBestExpertisePhysicians();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }

    @GetMapping(value = "expertise")
    @Operation(summary = "Get All Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertises(/*@RequestParam(required = false) String city */){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<ExpertiseOutputDto> expertises = expertiseAdapter.getExpertises();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }

    @PutMapping(value = "expertise/editExpertise/{expertiseId}")
    @PreAuthorize("hasRole('ROLE_Support')")
    @Operation(summary = "Edit Expertise")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editExpertise(@PathVariable Long expertiseId, @RequestBody @Validated ExpertiseInputDto expertiseInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDto expertiseOutputDTO = expertiseAdapter.editExpertise(expertiseId, expertiseInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDTO));

        return result;

    }

    @PutMapping("expertise/uploadPhysicianExpertises")
    @Operation(summary = "Upload Expertise Set")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> uploadExpertises(@RequestBody Set<ExpertiseInputDto> expertiseInputDtos) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<ExpertiseOutputDto> expertiseOutputDtos = expertiseAdapter.uploadExpertise(getCurrentUser().getId() , expertiseInputDtos);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDtos));

        return result;
    }

    @GetMapping("expertise/physicianExpertises")
    @Operation(summary = "Get All Physician Expertises")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianExpertises() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<ExpertiseOutputDto> expertiseOutputDtos = expertiseAdapter.allPhysicianExpertises(getCurrentUser().getId());

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDtos));

        return result;
    }

    @Operation(summary = "request to add new Expertise")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "expertise/requestToAdd")
    @SecurityRequirement(name = "security_auth")
    public ResponseEntity<Void> addSickness(@RequestParam String expertiseName ) {
        expertiseAdapter.requestToAddExpertise(getCurrentUser().getId() , expertiseName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("expertise/waitingForPublish")
    @Operation(summary = "Get All waiting For Publish Expertises")
    //Todo access increase to support role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RequestedExpertiseDto.class)) })
    public DeferredResult<ResponseEntity<?>> waitingExpertises() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<RequestedExpertiseDto> requestedExpertiseDtos = expertiseAdapter.waitingExpertises();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(requestedExpertiseDtos));

        return result;
    }
    @PostMapping("expertise/newExpertisesChangeState")
    @Operation(summary = "change state waiting For Publish Expertises")
    //Todo access increase to support role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RequestedExpertiseDto.class)) })
    public DeferredResult<ResponseEntity<?>> expertiseChangeState(@Valid @RequestBody RequestedExpertiseDto requestedExpertiseDto) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        RequestedExpertiseDto requestedExpertiseDtos = expertiseAdapter.expertiseChangeState(requestedExpertiseDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(requestedExpertiseDtos));

        return result;
    }

}


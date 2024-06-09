package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.Expertise.ExpertiseAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.Physician.PhysicianAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
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
public class ExpertiseController {


    private final ExpertiseAdapter expertiseAdapter;


    public ExpertiseController(ExpertiseAdapter expertiseAdapter) {
        this.expertiseAdapter = expertiseAdapter;

    }



    @GetMapping(value = "/expertise/{lotinName}")
    @Operation(summary = "Get a Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> getExpertise(@PathVariable ExpertiseLatinNames lotinName){

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

    @PutMapping(value = "expertise/addPhysicianExpertise/{nationalCode}")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Add Physician Expertise")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addPhysicianExpertise(@PathVariable Long nationalCode, @RequestBody @Validated ExpertiseInputDto expertiseInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDto expertiseOutputDTO = expertiseAdapter.addPhysicianExpertise(nationalCode, expertiseInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDTO));

        return result;

    }


    @PostMapping(value = "expertise/addExpertise/")
    @PreAuthorize("hasRole('ROLE_Support')")
    @Operation(summary = "Add Expertise")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExpertiseOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addExpertise(@RequestBody @Validated ExpertiseInputDto expertiseInputDTO)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ExpertiseOutputDto expertiseOutputDTO = expertiseAdapter.addExpertise(expertiseInputDTO);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertiseOutputDTO));

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

}


package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.Physician.PhysicianAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
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
public class PhysicianController extends BaseController {



    private final PhysicianAdapter physicianAdapter;

    public PhysicianController(PhysicianAdapter physicianAdapter) {
        this.physicianAdapter = physicianAdapter;

    }

    @PreAuthorize("hasRole('ROLE_Patient')")
    @GetMapping(value = "physician/{nationalCode}")
    @Operation(summary = "Fetch a Physician")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchPhysician(@PathVariable String nationalCode)
    {




        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.fetchPhysician(nationalCode);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping(value = "physician")
    @Operation(summary = "Physician Get Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> PhysicianGetProfile()
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.fetchPhysician(getCurrentUser());

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }

    @PostMapping(value = "physician")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Complete Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> PhysicianCompleteProfile(@RequestBody PhysicianInputDto physicianInputDto)
    {


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.physicianCompleteProfile(physicianInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }

    @PutMapping(value = "physician")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Edit Physician Profile")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> PhysicianEditProfile( @RequestBody @Validated PhysicianInputDto physicianInputDto)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.physicianEditProfile(physicianInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }


    @GetMapping(value = "/TopPhysicians")
    @PreAuthorize("hasRole('ROLE_Patient')")
    @Operation(summary = "Top Physicians")
    @ApiResponse(content = { @Content(mediaType = "application/json")})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> TopPhysicians(){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<PhysicianOutputDto> expertises = physicianAdapter.topPhysician();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(expertises));

        return result;

    }


    @GetMapping(value = "physician/changeState/{nationalCode}/{state}")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_Support')")
    @Operation(summary = "Change Physician State")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    DeferredResult<ResponseEntity<?>> changeState(@Validated @PathVariable String nationalCode , @Validated @PathVariable State state)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.changeState(nationalCode,state);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }





    @GetMapping(value = "physician/changeStatus/{nationalCode}/{status}")
    public @ResponseBody
    @PreAuthorize("hasRole('ROLE_Physician')")
    @Operation(summary = "Change Physician Status")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    DeferredResult<ResponseEntity<?>> changeStatus(@Validated @PathVariable String nationalCode , @Validated @PathVariable PhysicianStatus status)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PhysicianOutputDto physicianOutputDto = physicianAdapter.changeStatus(nationalCode,status);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianOutputDto));

        return result;

    }





}

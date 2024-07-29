package com.doctork.doctorkonlinecounseling.api.controllers;


import com.doctork.doctorkonlinecounseling.api.adapters.Carecenter.CareCenterAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.RequestedExpertiseDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.careCenter.RequestedCareCenterInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.RequestedCareCenterOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Set;

@Controller
@EnableMethodSecurity
@SecurityRequirement(name = "security_auth")
@RequestMapping("/api")
public class CareCenterController extends BaseController{

    // Todo add api for active deActive CareCenter

    private final CareCenterAdapter careCenterAdapter;

    public CareCenterController(CareCenterAdapter careCenterAdapter) {
        this.careCenterAdapter = careCenterAdapter;
    }

    @Operation(summary = "request to add new careCenter")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "careCenter/requestToAdd")
    @SecurityRequirement(name = "security_auth")
    public ResponseEntity<Void> requestToAddNewCareCenter(@RequestBody RequestedCareCenterInputDto requestedCareCenterInputDto ) {
        careCenterAdapter.requestToAddCareCenter(getCurrentUser().getId(),requestedCareCenterInputDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("careCenter/waitingForAccept")
    @Operation(summary = "Get All waiting For Accept CareCenters")
    //Todo access increase to support role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RequestedCareCenterOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> waitingCareCenters() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<RequestedCareCenterOutputDto> requestedCareCenterOutputDtos = careCenterAdapter.waitingCareCenters();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(requestedCareCenterOutputDtos));

        return result;
    }

    @GetMapping("ProvincesOrCities")
    @Operation(summary = "Get All ProvincesOrCities")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProvinceCitiesOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> GetProvinceCities(@RequestParam Integer provinceId) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<ProvinceCitiesOutputDto> provinceCitiesOutputDtos = careCenterAdapter.GetProvincesOrCities(provinceId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(provinceCitiesOutputDtos));

        return result;
    }

    @GetMapping("careCenter/activeCareCenters")
    @Operation(summary = "Get All Active CareCenters")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CareCenterOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> ActiveCareCenters() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<CareCenterOutputDto> careCenterOutputDtos = careCenterAdapter.allActiveCareCenter();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(careCenterOutputDtos));

        return result;
    }

    @PostMapping("careCenter/newCareCenterAcceptanceDecision")
    @Operation(summary = "Acceptance Decision waiting For Publish CareCenters")
    //Todo access increase to support role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RequestedExpertiseDto.class)) })
    public DeferredResult<ResponseEntity<?>> CareCenterAcceptanceDecision(@Valid @RequestBody RequestedCareCenterOutputDto requestedCareCenterOutputDto , @RequestParam Long careCenterTypeId) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        RequestedCareCenterOutputDto requestedCareCenterOutputDto1 = careCenterAdapter.careCenterAcceptanceDecision(requestedCareCenterOutputDto , careCenterTypeId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(requestedCareCenterOutputDto1));

        return result;
    }


    @GetMapping("/support/careCenter/ActiveCenterTypes")
    @Operation(summary = "Get All Active Center Types")
    //Todo access increase to support role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CareCenterTypesOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> ActiveCareTypes() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<CareCenterTypesOutputDto> careCenterTypesOutputDtos = careCenterAdapter.allActiveCenterTypes();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(careCenterTypesOutputDtos));

        return result;
    }
    @PostMapping("careCenter/addPhysician")
    @Operation(summary = "Add Physician to a Care Center")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    public DeferredResult<ResponseEntity<?>> addPhysicianToCareCenter( @RequestParam Long careCenterId, @RequestParam Set<WeekDay> days) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        PhysicianCareCenterOutputDto responseDto = careCenterAdapter.addPhysicianToCareCenter(getCurrentUser().getId(), careCenterId, days);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(responseDto));
        return result;
    }
    @PostMapping("careCenter/editPhysicianDays/{careCenterId}")
    @Operation(summary = "Edit Physician's Working Days in a Care Center")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    public DeferredResult<ResponseEntity<?>> editPhysicianWorkingDaysWithCareCenter( @PathVariable Long careCenterId, @RequestParam Set<WeekDay> days) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        PhysicianCareCenterOutputDto responseDto = careCenterAdapter.editPhysicianWorkingDaysWithCareCenter(getCurrentUser().getId(), careCenterId, days);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(responseDto));
        return result;
    }

    @DeleteMapping("careCenter/terminateCooperation/{careCenterId}")
    @Operation(summary = "Terminate Cooperation between Physician and Care Center")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    public ResponseEntity<Void> terminationOfCooperationPhysicianWithCareCenter( @PathVariable Long careCenterId) {
        careCenterAdapter.terminationOfCooperationPhysicianWithCareCenter(getCurrentUser().getId(), careCenterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("careCenter/physicianCareCenters")
    @Operation(summary = "Get All Care Centers Where the Physician Works")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianCareCenterOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allTheCareCentersWhereThePhysicianWorks() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        Set<PhysicianCareCenterOutputDto> careCenterOutputDtos = careCenterAdapter.allTheCareCentersWhereThePhysicianWorks(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(careCenterOutputDtos));
        return result;
    }

    @GetMapping("careCenter/{centerId}/physicians")
    @Operation(summary = "Get All Physicians of a Specific Care Center")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CareCenterOutputDtoWithPhysicians.class))})
    public ResponseEntity<CareCenterOutputDtoWithPhysicians> allPhysiciansOfCareCenter(@PathVariable Long centerId) {
        CareCenterOutputDtoWithPhysicians careCenterWithPhysicians = careCenterAdapter.allPhysiciansOfCareCenter(centerId);
        return ResponseEntity.status(HttpStatus.OK).body(careCenterWithPhysicians);
    }

    @GetMapping("careCenterType/{careCenterTypeId}/careCenters")
    @Operation(summary = "Get All Care Centers of a Specific Care Center Type")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @SecurityRequirement(name = "security_auth")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CareCenterTypeOutputWithCareCenters.class))})
    public ResponseEntity<CareCenterTypeOutputWithCareCenters> allCareCentersOfCareCenterType(@PathVariable Long careCenterTypeId) {
        CareCenterTypeOutputWithCareCenters careCenterTypeWithCareCenters = careCenterAdapter.allCareCentersOfCareCenterType(careCenterTypeId);
        return ResponseEntity.status(HttpStatus.OK).body(careCenterTypeWithCareCenters);
    }
}

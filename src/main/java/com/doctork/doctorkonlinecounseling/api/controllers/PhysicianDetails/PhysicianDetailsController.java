package com.doctork.doctorkonlinecounseling.api.controllers.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails.PhysicianDetailsAdapter;
import com.doctork.doctorkonlinecounseling.api.controllers.BaseController;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Set;


@RestController
@EnableMethodSecurity
@SecurityRequirement(name = "security_auth")
@RequestMapping("/api/")
public class PhysicianDetailsController extends BaseController {


    private final PhysicianDetailsAdapter physicianDetailsAdapter;

    public PhysicianDetailsController(PhysicianDetailsAdapter physicianDetailsAdapter) {
        this.physicianDetailsAdapter = physicianDetailsAdapter;
    }

    @Operation(summary = "create new Sickness")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "sickness/create")
    public ResponseEntity<Void> crateSickness(@RequestParam String sicknessName ) {
        physicianDetailsAdapter.crateSickness(sicknessName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("sickness/")
    @Operation(summary = "Upload Sicknesses Set")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SicknessOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> uploadSickneses(@RequestBody Set<SicknessInputDto> sicknessInputDtos) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SicknessOutputDto> sicknessOutputDtos = physicianDetailsAdapter.uploadSickness(getCurrentUser().getId() , sicknessInputDtos);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(sicknessOutputDtos));

        return result;
    }

    @GetMapping("sickness/all")
    @Operation(summary = "Get All Sicknesses")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SicknessOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allSicknesses() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SicknessOutputDto> sicknessOutputDtos = physicianDetailsAdapter.allSicknesses();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(sicknessOutputDtos));

        return result;
    }
    @GetMapping("sickness/allByPhysician")
    @Operation(summary = "Get All Physician Sicknesses")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SicknessOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianSicknesses() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SicknessOutputDto> sicknessOutputDtos = physicianDetailsAdapter.allPhysicianSicknesses(getCurrentUser().getId());

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(sicknessOutputDtos));

        return result;
    }
}

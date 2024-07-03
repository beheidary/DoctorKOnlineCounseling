package com.doctork.doctorkonlinecounseling.api.controllers.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails.PhysicianDetailsAdapter;
import com.doctork.doctorkonlinecounseling.api.controllers.BaseController;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.PhysicianSocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianSocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SocialMediaOutputDto;
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

    @Operation(summary = "add new Sickness")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "sickness/add")
    public ResponseEntity<Void> addSickness(@RequestParam String sicknessName ) {
        physicianDetailsAdapter.addSickness(sicknessName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "add new socialMedia")
    @PreAuthorize("hasRole('ROLE_Physician')")
    // Todo change Authorize To Support Role
    @PostMapping(value = "socialMedia/add")
    public ResponseEntity<Void> addSocialMedia(@RequestParam String persianName , String latinName ) {
        physicianDetailsAdapter.addSocialMedia(persianName , latinName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("sickness/")
    @Operation(summary = "Upload sicknesses Set")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SicknessOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> uploadSickneses(@RequestBody Set<SicknessInputDto> sicknessInputDtos) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SicknessOutputDto> sicknessOutputDtos = physicianDetailsAdapter.uploadSickness(getCurrentUser().getId() , sicknessInputDtos);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(sicknessOutputDtos));

        return result;
    }

    @PutMapping("socialMedias/")
    @Operation(summary = "Upload socialMedia Set")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianSocialMediaOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> uploadSocialMedias(@RequestBody Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<PhysicianSocialMediaOutputDto> physicianSocialMediaOutputDtos = physicianDetailsAdapter.uploadSocialMedias(getCurrentUser().getId() , physicianSocialMediaInputDtos);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianSocialMediaOutputDtos));

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

    @GetMapping("socialMedia/all")
    @Operation(summary = "Get All SocialMedias")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SocialMediaOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allSocialMedias() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SocialMediaOutputDto> socialMediaOutputDtos = physicianDetailsAdapter.allSocialMedias();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(socialMediaOutputDtos));

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
    @GetMapping("socialMedia/allByPhysician")
    @Operation(summary = "Get All Physician SocialMedias")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianSocialMediaOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianSocialMedias() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<PhysicianSocialMediaOutputDto> physicianSocialMediaOutputDtos = physicianDetailsAdapter.allPhysicianSocialMedias(getCurrentUser().getId());

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianSocialMediaOutputDtos));

        return result;
    }

    @Operation(summary = "Sickness change state")
    // Todo change Authorize To Support Role
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping(value = "sickness/changeState")
    public ResponseEntity<Void> sicknessChangeState(@RequestParam Long sicknessId , State state ) {
        physicianDetailsAdapter.sicknessChangeState(sicknessId,state);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

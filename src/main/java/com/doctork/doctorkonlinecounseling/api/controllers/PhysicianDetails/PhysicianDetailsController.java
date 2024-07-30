package com.doctork.doctorkonlinecounseling.api.controllers.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails.PhysicianDetailsAdapter;
import com.doctork.doctorkonlinecounseling.api.controllers.BaseController;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.*;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
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
    @GetMapping("/support/sickness/all")
    //Todo access for support only
    @Operation(summary = "Get All Sicknesses")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SicknessOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allSicknesses(@RequestParam State state ) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        Set<SicknessOutputDto> sicknessOutputDtos = physicianDetailsAdapter.allSicknessesWithState(state);

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

    @Operation(summary = "add Education")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "education/add")
    public ResponseEntity<Void> addEducation(@RequestBody EducationInputDto educationInputDto) {
        physicianDetailsAdapter.addEducation(getCurrentUser().getId(),educationInputDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "edit Education")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping(value = "education/edit/{educationId}")
    public ResponseEntity<Void> editEducation(@PathVariable Long educationId, @RequestBody EducationInputDto educationInputDto) {
        physicianDetailsAdapter.editEducation(getCurrentUser().getId(),educationInputDto,educationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete Education")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping(value = "education/delete/{educationId}")
    public DeferredResult<ResponseEntity<?>> deleteEducation(@PathVariable Long educationId) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        educationId = physicianDetailsAdapter.deleteEducation(getCurrentUser().getId(),educationId);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(educationId));
        return result;
    }

    @Operation(summary = "all physician Educations")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping(value = "education/allPhysicianEducations")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EducationOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianEducations() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<EducationOutputDto> educationOutputDtos = physicianDetailsAdapter.allPhysicianEducations(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(educationOutputDtos));
        return result;
    }


    @Operation(summary = "Add Experiences")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("experiences/add")
    public ResponseEntity<Void> addExperiences(@RequestBody ExperiencesInputDto experiencesInputDto) {
        physicianDetailsAdapter.addExperiences(getCurrentUser().getId(), experiencesInputDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Edit Experiences")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping("experiences/edit/{experiencesId}")
    public ResponseEntity<Void> editExperiences(@PathVariable Long experiencesId, @RequestBody ExperiencesInputDto experiencesInputDto) {
        physicianDetailsAdapter.editExperiences(getCurrentUser().getId(), experiencesInputDto, experiencesId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete Experiences")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping("experiences/delete/{experiencesId}")
    public DeferredResult<ResponseEntity<?>> deleteExperiences(@PathVariable Long experiencesId) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        experiencesId = physicianDetailsAdapter.deleteExperiences(getCurrentUser().getId(), experiencesId);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(experiencesId));
        return result;
    }

    @Operation(summary = "All Physician Experiences")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("experiences/allPhysicianExperiences")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExperiencesOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianExperiences() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<ExperiencesOutputDto> experiencesOutputDtos = physicianDetailsAdapter.allPhysicianExperiences(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(experiencesOutputDtos));
        return result;
    }



    @Operation(summary = "Add Membership")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("membership/add")
    public ResponseEntity<Void> addMembership(@RequestBody MembershipInputDto membershipInputDto) {
        physicianDetailsAdapter.addMembership(getCurrentUser().getId(), membershipInputDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Edit Membership")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping("membership/edit/{membershipId}")
    public ResponseEntity<Void> editMembership(@PathVariable Long membershipId, @RequestBody MembershipInputDto membershipInputDto) {
        physicianDetailsAdapter.editMembership(getCurrentUser().getId(), membershipInputDto, membershipId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete Membership")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping("membership/delete/{membershipId}")
    public DeferredResult<ResponseEntity<?>> deleteMembership(@PathVariable Long membershipId) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        membershipId = physicianDetailsAdapter.deleteMembership(getCurrentUser().getId(), membershipId);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(membershipId));
        return result;
    }

    @Operation(summary = "All Physician Memberships")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("membership/allPhysicianMemberships")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MembershipOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianMemberships() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<MembershipOutputDto> membershipOutputDtos = physicianDetailsAdapter.allPhysicianMemberships(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(membershipOutputDtos));
        return result;
    }

    @Operation(summary = "Add Award or Honor")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("awardsAndHonors/add")
    public ResponseEntity<Void> addAwardOrHonor(@RequestBody AwardsAndHonorsInputDto awardsAndHonorsInputDto) {
        physicianDetailsAdapter.addAwardOrHonor(getCurrentUser().getId(), awardsAndHonorsInputDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Edit Award or Honor")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping("awardsAndHonors/edit/{awardOrHonorId}")
    public ResponseEntity<Void> editAwardOrHonor(@PathVariable Long awardOrHonorId, @RequestBody AwardsAndHonorsInputDto awardsAndHonorsInputDto) {
        physicianDetailsAdapter.editAwardOrHonor(getCurrentUser().getId(), awardsAndHonorsInputDto, awardOrHonorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete Award or Honor")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping("awardsAndHonors/delete/{awardOrHonorId}")
    public DeferredResult<ResponseEntity<?>> deleteAwardOrHonor(@PathVariable Long awardOrHonorId) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        awardOrHonorId = physicianDetailsAdapter.deleteAwardOrHonor(getCurrentUser().getId(), awardOrHonorId);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(awardOrHonorId));
        return result;
    }

    @Operation(summary = "All Physician Awards and Honors")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("awardsAndHonors/all")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AwardsAndHonorsOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianAwardsAndHonors() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<AwardsAndHonorsOutputDto> awardsAndHonorsOutputDtos = physicianDetailsAdapter.allPhysicianAwardsAndHonors(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(awardsAndHonorsOutputDtos));
        return result;
    }


    @Operation(summary = "Add Gallery Image")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("galleryImage/add")
    public ResponseEntity<Void> addGalleryImage(@RequestParam String imageName) {
        physicianDetailsAdapter.addGalleryImage(getCurrentUser().getId(), imageName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Gallery Image")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping("galleryImage/{imageId}")
    public ResponseEntity<Void> deleteGalleryImage(@PathVariable Long imageId) {
        physicianDetailsAdapter.deActiveGalleryImage(getCurrentUser().getId(), imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "All Physician Gallery Images")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("galleryImage/all")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = GalleryImageOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> allPhysicianGalleryImages(){
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<GalleryImageOutputDto> galleryImageOutputDtos = physicianDetailsAdapter.allPhysicianGalleryImages(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(galleryImageOutputDtos));
        return result;
    }

    @Operation(summary = "Get Physician Bank Information")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("/bankInfo/get")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianBankInfoOutputDto.class)) })
    public DeferredResult<ResponseEntity<?>> getPhysicianBankInfo(){
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        PhysicianBankInfoOutputDto physicianBankInfoOutputDto = physicianDetailsAdapter.getBankInfo(getCurrentUser().getId());
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(physicianBankInfoOutputDto));
        return result;
    }

    @Operation(summary = "Store Physician Bank Information")
    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("/bankInfo/store")
    public DeferredResult<ResponseEntity<?>> storePhysicianBankInfo(@RequestBody @Validated PhysicianBankInfoInputDto physicianBankInfoInputDto) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        PhysicianBankInfoOutputDto physicianBankInfoOutputDto =  physicianDetailsAdapter.storeBankInfo(getCurrentUser().getId(), physicianBankInfoInputDto);
        result.setResult(ResponseEntity.status(HttpStatus.CREATED).body(physicianBankInfoOutputDto));
        return result;
    }


}

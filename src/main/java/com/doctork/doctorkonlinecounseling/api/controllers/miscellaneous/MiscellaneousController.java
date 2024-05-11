package com.doctork.doctorkonlinecounseling.api.controllers.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous.MiscellaneousAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.PatientInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ServicesOutputDto;
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

import java.util.List;

@Controller
@EnableMethodSecurity
@RequestMapping("/miscellaneous")
@SecurityRequirement(name = "security_auth")
public class MiscellaneousController {

    private final MiscellaneousAdapter miscellaneousAdapter;

    public MiscellaneousController(MiscellaneousAdapter miscellaneousAdapter) {
        this.miscellaneousAdapter = miscellaneousAdapter;
    }


    @PreAuthorize("hasRole('ROLE_Patient')")
    @GetMapping(value = "/faq")
    @Operation(summary = "Get All FAQ")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchFAQ()
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<FAQOutputDto> faqOutputDtos = miscellaneousAdapter.getFaq();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(faqOutputDtos));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping(value = "/Services")
    @Operation(summary = "Add Services")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addServices(@RequestBody @Validated ServicesInputDto servicesInputDto)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ServicesOutputDto servicesOutputDto = miscellaneousAdapter.addServices(servicesInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(servicesOutputDto));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Patient')")
    @GetMapping(value = "/articles")
    @Operation(summary = "Get All articles")
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> fetchArticles()
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<ArticleOutputDto> articleOutputDtos = miscellaneousAdapter.getAllArticles();

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(articleOutputDtos));

        return result;

    }
}

package com.doctork.doctorkonlinecounseling.api.controllers;


import com.doctork.doctorkonlinecounseling.api.adapters.Counseling.OnlineCounselingAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling.OnlineCounselingOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@EnableMethodSecurity
@RequestMapping("/")
@SecurityRequirement(name = "security_auth")
public class DoCounselingController {


    private final OnlineCounselingAdapter onlineCounselingAdapter;

    public DoCounselingController(OnlineCounselingAdapter onlineCounselingAdapter) {
        this.onlineCounselingAdapter = onlineCounselingAdapter;
    }

    @PreAuthorize("hasRole('ROLE_Patient')")
    @PostMapping(value = "Counseling/create")
    @Operation(summary = "create a new counseling")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PhysicianOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> createCounseling(@RequestParam(required = false) Long patientId,
                                                       @RequestParam(required = false) Long physicianId,
                                                       @RequestParam(required = false) Long priceId)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        OnlineCounselingOutputDto onlineCounselingOutputDto = onlineCounselingAdapter.createCounseling(patientId,physicianId,priceId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(onlineCounselingOutputDto));

        return result;

    }





}

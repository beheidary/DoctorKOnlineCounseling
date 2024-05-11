package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous.MiscellaneousAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.InputException;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@Controller
@EnableMethodSecurity
@RequestMapping("/")
@SecurityRequirement(name = "security_auth")
public class PriceController {

    private final MiscellaneousAdapter miscellaneousAdapter;


    public PriceController(MiscellaneousAdapter miscellaneousAdapter) {
        this.miscellaneousAdapter = miscellaneousAdapter;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "{servicesId}/addPrice/{physicianId}/")
    @Operation(summary = "add Physician Price")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addPrice(@PathVariable Long servicesId,
                                                  @PathVariable Long physicianId,
                                                  @RequestBody @Validated PriceInputDto priceInputDto,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InputException(bindingResult.getAllErrors());

        }


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PriceOutputDto priceOutputDto = miscellaneousAdapter.addPrice(priceInputDto, physicianId, servicesId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDto));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping(value = "/editPrice/{priceId}/")
    @Operation(summary = "edit Physician Price")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editPrice(@PathVariable Long priceId,
                                               @RequestBody @Validated PriceInputDto priceInputDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InputException(bindingResult.getAllErrors());

        }

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PriceOutputDto priceOutputDto = miscellaneousAdapter.editPrice(priceId,priceInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDto));

        return result;

    }


    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping(value = "/allPrice/{physicianId}/")
    @Operation(summary = "physician all prices")
    @ApiResponse(content = {@Content(schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> readPrices(@PathVariable Long physicianId) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<PriceOutputDto> priceOutputDtos = miscellaneousAdapter.readPrices(physicianId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDtos));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @DeleteMapping(value = "/deletePrice/{priceId}/")
    @Operation(summary = "delete a prices")
    @ApiResponse(content = {@Content(schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> deletePrice(@PathVariable Long priceId) {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        priceId = miscellaneousAdapter.deletePrice(priceId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceId));

        return result;

    }

}

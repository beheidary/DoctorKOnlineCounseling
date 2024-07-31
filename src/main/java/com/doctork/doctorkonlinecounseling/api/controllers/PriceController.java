package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.adapters.Price.PriceAdapter;
import com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous.MiscellaneousAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.InputException;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@Controller
@EnableMethodSecurity
@RequestMapping("/")
@SecurityRequirement(name = "security_auth")
public class PriceController extends BaseController{

    private final PriceAdapter priceAdapter;

    public PriceController(PriceAdapter priceAdapter) {
        this.priceAdapter = priceAdapter;
    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "{servicesId}/addPrice")
    @Operation(summary = "add Physician Price")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addPrice(@PathVariable Long servicesId,
                                                  @RequestBody @Validated PriceInputDto priceInputDto,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InputException(bindingResult.getAllErrors());

        }


        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PriceOutputDto priceOutputDto = priceAdapter.addPrice(priceInputDto, getCurrentUser().getId(), servicesId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDto));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PutMapping(value = "deActivePrice/{priceId}")
    @Operation(summary = "deActive Physician Price")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> editPrice(@PathVariable Long priceId){

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        PriceOutputDto priceOutputDto = priceAdapter.DeActivePrice(getCurrentUser().getId(),priceId);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDto));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping(value = "Services")
    @Operation(summary = "Add Services")
    // Todo change Authorize To Support Role
    @ApiResponse(content = { @Content(mediaType = "application/json") })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> addServices(@RequestBody @Validated ServicesInputDto servicesInputDto)
    {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        ServicesOutputDto servicesOutputDto = priceAdapter.addServices(servicesInputDto);

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(servicesOutputDto));

        return result;

    }


    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping(value = "allPrice/")
    @Operation(summary = "physician all prices")
    @ApiResponse(content = {@Content(schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> readPrices() {

        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();

        List<PriceOutputDto> priceOutputDtos = priceAdapter.readPrices(getCurrentUser().getId());

        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDtos));

        return result;

    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @PostMapping("priceAcceptance/{priceId}")
    // Todo change Authorize To Support Role
    @Operation(summary = "Decide on Price Acceptance")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PriceOutputDto.class))})
    public @ResponseBody DeferredResult<ResponseEntity<?>> priceAcceptanceDecision(@PathVariable Long priceId,
                                                                                   @RequestParam State state) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        PriceOutputDto priceOutputDto = priceAdapter.priceAcceptanceDecision(priceId, state);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(priceOutputDto));

        return result;
    }

    @PreAuthorize("hasRole('ROLE_Physician')")
    @GetMapping("activeServices")
    @Operation(summary = "List All Active Services")
    @ApiResponse(content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ServicesOutputDto.class))})
    public @ResponseBody DeferredResult<ResponseEntity<?>> allActiveServices() {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        List<ServicesOutputDto> servicesOutputDtos = priceAdapter.AllActiveServices();
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(servicesOutputDtos));

        return result;
    }

}

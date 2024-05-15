package com.doctork.doctorkonlinecounseling.api.controllers.elastic;

import com.doctork.doctorkonlinecounseling.api.adapters.Elastic.ElasticAdapter;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SearchResultDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.SuggestOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;



@RestController()
@OpenAPIDefinition(
        info = @Info(title = "Physician",version = "0.0",description = "DESCRIPTION")
)
@RequestMapping(value = "/elastic/physician", produces = "application/json", headers = {"Accept-Language"})
public class    ElasticSearchController {

    private final ElasticAdapter elasticAdapter;

    public ElasticSearchController(ElasticAdapter elasticAdapter) {
        this.elasticAdapter = elasticAdapter;
    }


    @GetMapping("/search")
    @Operation(summary = "Search on elastic engine")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SearchResultDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> findByNameAndSpeciality(@RequestParam("queryString") String queryString,
                                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer pageNumber,
                                                              @RequestParam(defaultValue = "10") @Positive Integer pageSize) throws IOException {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        SearchResultDto searchResultDTO = elasticAdapter.search(queryString,pageNumber,pageSize);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(searchResultDTO));
        return result;
    }

    @GetMapping("/suggest")
    @Operation(summary = "Get Suggestions")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SuggestOutputDto.class)) })
    public @ResponseBody
    DeferredResult<ResponseEntity<?>> suggestBySpeciality(@RequestParam("queryString") String queryString) throws IOException {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        SuggestOutputDto suggestOutputDTO = elasticAdapter.TermSuggest(queryString);
        result.setResult(ResponseEntity.status(HttpStatus.OK).body(suggestOutputDTO));
        return result;
    }

}
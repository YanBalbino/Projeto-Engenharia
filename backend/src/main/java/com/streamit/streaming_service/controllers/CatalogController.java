package com.streamit.streaming_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streamit.streaming_service.constants.ApiConstants;
import com.streamit.streaming_service.dtos.catalog.CatalogDTO;
import com.streamit.streaming_service.model.CatalogModel;
import com.streamit.streaming_service.response.ApiResponse;
import com.streamit.streaming_service.response.ResponseUtil;
import com.streamit.streaming_service.services.ICatalogService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/catalogs")
@AllArgsConstructor
public class CatalogController {

    private final ICatalogService catalogService;

    @PostMapping
    public ResponseEntity<ApiResponse<CatalogModel>> createCatalog(@RequestBody CatalogDTO catalogDto) {
        CatalogModel createdCatalog = catalogService.create(catalogDto);
        ApiResponse<CatalogModel> response = ResponseUtil.success(createdCatalog, 
                ApiConstants.MESSAGE_RESOURCE_CREATED, 
                ApiConstants.HTTP_STATUS_CREATED, 
                ApiConstants.PATH_CATALOG);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

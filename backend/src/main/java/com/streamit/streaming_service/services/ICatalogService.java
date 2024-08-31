package com.streamit.streaming_service.services;

import java.util.List;
import java.util.UUID;

import com.streamit.streaming_service.dtos.CatalogDTO;
import com.streamit.streaming_service.model.CatalogModel;

public interface ICatalogService {

	CatalogModel create(CatalogDTO catalogDto);
	CatalogModel findById(UUID id);
	List<CatalogModel> findAll();
	CatalogModel addMedia(UUID idMedia);
	CatalogModel removeMedia(UUID idMedia);
	void delete(UUID id);
}

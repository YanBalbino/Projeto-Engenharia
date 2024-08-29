package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CatalogDTO;
import com.streamit.streaming_service.dtos.CreateFilmDTO;
import com.streamit.streaming_service.dtos.CreateSeriesDTO;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.model.CatalogModel;
import com.streamit.streaming_service.model.MediaModel;
import com.streamit.streaming_service.repositories.CatalogRepository;
import com.streamit.streaming_service.repositories.MediaRepository;
import com.streamit.streaming_service.services.ICatalogService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements ICatalogService {

	CatalogRepository catalogRepository;
	MediaRepository mediaRepository;

	@Override
	public CatalogModel create(CatalogDTO catalogDto) {
		CatalogModel entity = new CatalogModel();
		entity.setNome(catalogDto.getNome());

		if (!catalogDto.getMidiaIds().isEmpty()) {
			List<MediaModel> filmList = new ArrayList<>();
			for (UUID mediaId : catalogDto.getMidiaIds()) {
				MediaModel media = mediaRepository.findById(mediaId)
						.orElseThrow(() -> new ResourceNotFoundException("Mídia não encontrada com id " + mediaId));
				filmList.add(media);
			}
			entity.setMedias(filmList);
		}
		return catalogRepository.save(entity);
	}

	@Override
	public CatalogModel findById(UUID id) {
		return catalogRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catálogo não encontrado com id " + id));
	}

	@Override
	public List<CatalogModel> findAll() {
		return catalogRepository.findAll();
	}

	@Override
	public void delete(UUID id) {
		CatalogModel entity = findById(id);
		catalogRepository.delete(entity);
	}

	@Override
	public CatalogModel addFilm(UUID id, List<CreateFilmDTO> films) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogModel removeFilm(UUID id, List<CreateFilmDTO> films) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogModel addSeries(UUID id, List<CreateSeriesDTO> series) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogModel removeSeries(UUID id, List<CreateSeriesDTO> series) {
		// TODO Auto-generated method stub
		return null;
	}

}

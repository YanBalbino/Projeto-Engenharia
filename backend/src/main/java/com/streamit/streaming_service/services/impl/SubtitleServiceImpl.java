package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.subtitle.ReturnSubtitleDTO;
import com.streamit.streaming_service.dtos.subtitle.UpdateSubtitleDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.SubtitleMapper;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.model.SubtitleModel;
import com.streamit.streaming_service.repositories.SubtitleRepository;
import com.streamit.streaming_service.services.ISubtitleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubtitleServiceImpl implements ISubtitleService {

    private SubtitleRepository subtitleRepository;
    private FilmServiceImpl filmServiceImpl;
    private EpisodeServiceImpl episodeServiceImpl;

    public SubtitleModel findModelById(UUID id) {
        return subtitleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legenda não encontrada com id " + id));
    }

    @Override
    public ReturnSubtitleDTO findById(UUID id) {
        SubtitleModel subtitle = findModelById(id);
        ReturnSubtitleDTO entityDto = SubtitleMapper.toDto(subtitle);
        return entityDto;
    }

    @Override
    public ReturnSubtitleDTO update(UpdateSubtitleDTO subtitleDto) {
        SubtitleModel entity = findModelById(subtitleDto.getId());
        
        List<SubtitleModel> entities = subtitleRepository.findAll();
        for (SubtitleModel subtitle : entities) {
            if (subtitle.getUrl().equals(subtitleDto.getLegendaUrl()) && !entity.getId().equals(subtitle.getId())) {
                throw new ResourceAlreadyExistsException("URL de legenda já cadastrada.");
            }
        }

        SubtitleMapper.toUpdateEntity(subtitleDto, entity);
        
        SubtitleModel updatedSubtitle = subtitleRepository.save(entity);

        return SubtitleMapper.toDto(updatedSubtitle);
    }
    
	@Override
	public void delete(UUID id) {
		SubtitleModel entity = findModelById(id);
		FilmModel film = filmServiceImpl.getFilmBySubtitleId(id);
		EpisodeModel episode = episodeServiceImpl.getEpisodeBySubtitleId(id);
		if(film != null) {
			film.getLegendasDisponiveis().remove(entity);
		}else {
			episode.getLegendasDisponiveis().remove(entity);
		}
		subtitleRepository.delete(entity);
	}
}

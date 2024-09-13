package com.streamit.streaming_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.AudioMapper;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.model.EpisodeModel;
import com.streamit.streaming_service.model.FilmModel;
import com.streamit.streaming_service.repositories.AudioRepository;
import com.streamit.streaming_service.services.IAudioService;
import com.streamit.streaming_service.services.IEpisodeService;
import com.streamit.streaming_service.services.IFilmService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AudioServiceImpl implements IAudioService {

    private AudioRepository audioRepository;
    private IFilmService filmService;
    private IEpisodeService episodeService;

    public AudioModel findModelById(UUID id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legenda não encontrada com id " + id));
    }

    @Override
    public ReturnAudioDTO findById(UUID id) {
        AudioModel audio = findModelById(id);
        ReturnAudioDTO entityDto = AudioMapper.toDto(audio);
        return entityDto;
    }

    @Override
    public ReturnAudioDTO update(UpdateAudioDTO audioDto) {
        AudioModel entity = findModelById(audioDto.getId());
        
        List<AudioModel> entities = audioRepository.findAll();
        for (AudioModel Audio : entities) {
            if (Audio.getUrl().equals(audioDto.getAudioUrl()) && !entity.getId().equals(Audio.getId())) {
                throw new ResourceAlreadyExistsException("URL de áudio já cadastrado.");
            }
        }

        AudioMapper.toUpdateEntity(audioDto, entity);
        
        AudioModel updatedAudio = audioRepository.save(entity);

        return AudioMapper.toDto(updatedAudio);
    }
    
	@Override
	public void delete(UUID id) {
		AudioModel entity = findModelById(id);
		FilmModel film = filmService.getFilmByAudioId(id);
		EpisodeModel episode = episodeService.getEpisodeByAudioId(id);
		if(film != null) {
			film.getAudiosDisponiveis().remove(entity);
		}else {
			episode.getAudiosDisponiveis().remove(entity);
		}
		audioRepository.delete(entity);
	}
}

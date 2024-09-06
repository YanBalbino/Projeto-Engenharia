package com.streamit.streaming_service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.audio.ReturnAudioDTO;
import com.streamit.streaming_service.dtos.audio.UpdateAudioDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.AudioMapper;
import com.streamit.streaming_service.model.AudioModel;
import com.streamit.streaming_service.repositories.AudioRepository;
import com.streamit.streaming_service.services.IAudioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AudioServiceImpl implements IAudioService {

    private AudioRepository audioRepository;

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
    public List<ReturnAudioDTO> findAll() {
        List<AudioModel> audioList = audioRepository.findAll();
        List<ReturnAudioDTO> dtoList = new ArrayList<>();

        for (AudioModel audio : audioList) {
            ReturnAudioDTO dto = AudioMapper.toDto(audio);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ReturnAudioDTO update(UUID id, UpdateAudioDTO AudioDto) {
        AudioModel entity = findModelById(id);
        
        List<AudioModel> entities = audioRepository.findAll();
        for (AudioModel Audio : entities) {
            if (Audio.getUrl().equals(AudioDto.getAudioUrl()) && !entity.getId().equals(Audio.getId())) {
                throw new ResourceAlreadyExistsException("URL de áudio já cadastrado.");
            }
        }

        AudioMapper.toUpdateEntity(AudioDto, entity);
        
        AudioModel updatedAudio = audioRepository.save(entity);

        return AudioMapper.toDto(updatedAudio);
    }
}

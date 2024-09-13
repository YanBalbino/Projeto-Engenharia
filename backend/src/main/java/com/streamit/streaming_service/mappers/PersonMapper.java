package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.model.PersonModel;

public class PersonMapper {

    public static PersonModel toModel(CreateUserDTO userDto) {
        if (userDto == null) {
            return null;
        }

        PersonModel person = new PersonModel();
        person.setEmail(userDto.getEmail());
        person.setNome(userDto.getNome());
        return person;
    }
}

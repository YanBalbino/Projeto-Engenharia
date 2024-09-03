package com.streamit.streaming_service.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.streamit.streaming_service.dtos.profile.ReturnProfileDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;

public class UserMapper {

	public static ReturnUserDTO toDtoReturn(UserModel user) {
		if (user == null) {
			throw new IllegalArgumentException("UserModel não pode ser nulo.");
		}

		ReturnUserDTO dto = new ReturnUserDTO();
		dto.setId(user.getId());
		dto.setNome(user.getNome());
		dto.setEmail(user.getEmail());

		List<ReturnProfileDTO> profileDTOs = user.getPerfis().stream().map(ProfileMapper::toDTO)
				.collect(Collectors.toList());

		dto.setPerfis(profileDTOs);

		return dto;
	}

	public static UserModel toModel(CreateUserDTO userDto, LocalDate currentDate, SubscriptionModel subscription,
			PaymentModel payment) {
		UserModel entity = new UserModel();
		entity.setEmail(userDto.getEmail());
		entity.setNome(userDto.getNome());
		entity.setSenha(userDto.getSenha());
		entity.setDataCadastro(currentDate);

		List<ProfileModel> profilesModel = new ArrayList<>();
		userDto.getPerfis().forEach(profile -> profilesModel.add(ProfileMapper.toModel(profile, entity)));
		entity.setPerfis(profilesModel);

		entity.setSubscription(subscription);
		entity.setPayment(payment);

		return entity;
	}
}

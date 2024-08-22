package com.streamit.streaming_service.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreateUserWithPaymentDTO;
import com.streamit.streaming_service.dtos.ProfileDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.PaymentRepository;
import com.streamit.streaming_service.repositories.ProfileRepository;
import com.streamit.streaming_service.repositories.SubscriptionRepository;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IUserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private PaymentRepository paymentRepository;
    private ProfileRepository profileRepository;

    @Transactional
    @Override
    public ReturnUserDTO create(CreateUserWithPaymentDTO userPaymentDto) {
        if (userRepository.findByEmail(userPaymentDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email já cadastrado.");
        }

        LocalDate currentDate = LocalDate.now();

        UserModel entity = new UserModel();
        entity.setEmail(userPaymentDto.getEmail());
        entity.setNome(userPaymentDto.getNome());
        entity.setSenha(userPaymentDto.getSenha());
        entity.setDataCadastro(currentDate);
        
        List<ProfileDTO> profiles = userPaymentDto.getPerfis();
        List<ProfileModel> profilesModel = new ArrayList<>();
        
        // cria os perfis
        for(ProfileDTO profile : profiles) {
        	ProfileModel model = new ProfileModel();
        	model.setNome(profile.getNome());
        	model.setGenerosPreferidos(profile.getGenerosPreferidos());
        	model.setIconUrl(profile.getIconUrl());
        	model.setPerfilInfantil(profile.getPerfilInfantil());
        	profilesModel.add(model);
        }
        
        entity.setPerfis(profilesModel);
        UserModel entitySaved = userRepository.save(entity);
        
        // define o usuário nos perfis e salva
        for(ProfileModel profile : profilesModel) {
        	profile.setUser(entitySaved);
        	profileRepository.save(profile);
        }
        
        // cria e salva a subscrição
        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setUser(entitySaved);
        subscription.setDataInicio(currentDate);
        subscription.setDataTermino(currentDate.plusMonths(1)); 
        subscription.setStatusAtivo(true);

        subscriptionRepository.save(subscription);

        // cria e salva o pagamento
        PaymentModel payment = new PaymentModel();
        payment.setUser(entitySaved);
        payment.setDataPagamento(currentDate);
        payment.setMetodoPagamento(userPaymentDto.getMetodoPagamento());
        payment.setValor(userPaymentDto.getValor());

        paymentRepository.save(payment);

        return UserMapper.toDtoReturn(entitySaved);
    }

    @Override
    public ReturnUserDTO findUserDtoById(UUID id) {
        return UserMapper.toDtoReturn(findUserModelById(id));
    }

    public UserModel findUserModelById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
    }

    @Override
    public List<ReturnUserDTO> findAll() {
        List<UserModel> entities = userRepository.findAll();
        List<ReturnUserDTO> dtos = new ArrayList<>();
        for(UserModel entity : entities) {
            dtos.add(UserMapper.toDtoReturn(entity));
        }
        return dtos;
    }

    @Override
    public boolean update(String name, UUID id) {
    	UserModel entity = findUserModelById(id);
    	entity.setNome(name);
    	userRepository.save(entity);
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        UserModel entity = findUserModelById(id);
        userRepository.delete(entity);
        return true;
    }
}

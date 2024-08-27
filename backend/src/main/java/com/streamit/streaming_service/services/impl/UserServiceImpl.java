package com.streamit.streaming_service.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.CreateProfileDTO;
import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ProfileMapper;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IUserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    PaymentServiceImpl paymentServiceImpl;
    SubscriptionServiceImpl subscriptionServiceImpl;

    @Transactional
    @Override
    public ReturnUserDTO create(CreateUserDTO userPaymentDto) {
        if (userRepository.findByEmail(userPaymentDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email já cadastrado.");
        }

        LocalDate currentDate = LocalDate.now();

        UserModel entity = new UserModel();
        entity.setEmail(userPaymentDto.getEmail());
        entity.setNome(userPaymentDto.getNome());
        entity.setSenha(userPaymentDto.getSenha());
        entity.setDataCadastro(currentDate);
        
        List<CreateProfileDTO> profiles = userPaymentDto.getPerfis();
        List<ProfileModel> profilesModel = new ArrayList<>();
        
        // cria os perfis
        for(CreateProfileDTO profile : profiles) {
        	profilesModel.add(ProfileMapper.toModel(profile, entity));
        }
        
        entity.setPerfis(profilesModel);
        
        // cria a inscrição
        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setUser(entity);
        subscription.setDataInicio(currentDate);
        subscription.setDataTermino(currentDate.plusMonths(1)); 
        subscription.setStatusAtivo(true);
        
        entity.setSubscription(subscription);

        // cria o pagamento
        PaymentModel payment = new PaymentModel();
        payment.setUser(entity);
        payment.setDataPagamento(currentDate);
        payment.setMetodoPagamento(userPaymentDto.getMetodoPagamento());
        payment.setValor(userPaymentDto.getValor());
        
        entity.setPayment(payment);

        UserModel entitySaved = userRepository.save(entity);
        
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
    public ReturnUserDTO update(String name, UUID id) {
    	UserModel entity = findUserModelById(id);
    	entity.setNome(name);
        UserModel entitySaved = userRepository.save(entity);
        return UserMapper.toDtoReturn(entitySaved);
    }

    @Override
    public boolean delete(UUID id) {
        UserModel entity = findUserModelById(id);
        userRepository.delete(entity);
        return true;
    }
    
    @Transactional
    @Override
    public ReturnUserDTO renovarInscricao(UUID userId, UUID subscriptionId, UUID paymentId, CreatePaymentDTO paymentDto) {
    	UserModel entity = findUserModelById(userId);
        PaymentModel payment = paymentServiceImpl.processarPagamento(paymentDto, paymentId);
        SubscriptionModel subscription = subscriptionServiceImpl.renovarInscricao(subscriptionId);
        entity.setPayment(payment);
        entity.setSubscription(subscription);
        UserModel entitySaved = userRepository.save(entity);
        return UserMapper.toDtoReturn(entitySaved);
    }

}

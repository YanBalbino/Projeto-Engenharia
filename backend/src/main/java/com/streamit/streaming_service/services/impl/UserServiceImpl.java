package com.streamit.streaming_service.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.CreateUserDTO;
import com.streamit.streaming_service.dtos.ReturnUserDTO;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.PaymentMapper;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.PaymentRepository;
import com.streamit.streaming_service.repositories.SubscriptionRepository;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private PaymentRepository paymentRepository;

    @Override
    public ReturnUserDTO create(CreateUserDTO userDto, CreatePaymentDTO paymentDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email já cadastrado.");
        }

        LocalDate currentDate = LocalDate.now();
        userDto.setDataCadastro(currentDate);

        UserModel entity = UserMapper.toModel(userDto);
        UserModel entitySaved = userRepository.save(entity);

        // cria e salva a subscrição
        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setUser(entitySaved);
        subscription.setDataInicio(currentDate);
        subscription.setDataTermino(currentDate.plusMonths(1)); 
        subscription.setStatusAtivo(true);

        subscriptionRepository.save(subscription);

        // cria e salva o pagamento
        paymentDto.setUserId(entitySaved.getId());
        paymentDto.setDataPagamento(currentDate);
        paymentDto.setValor(30.0); // supondo preço do serviço

        PaymentModel payment = PaymentMapper.toModel(paymentDto, entitySaved);
        paymentRepository.save(payment);

        return UserMapper.toDtoReturn(entitySaved);
    }

    @Override
    public ReturnUserDTO findById(UUID id) {
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
    public boolean update(CreateUserDTO user, CreatePaymentDTO paymentDTO) {
        // TODO
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        UserModel entity = findUserModelById(id);
        userRepository.delete(entity);
        return true;
    }
}

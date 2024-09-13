package com.streamit.streaming_service.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.payment.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.enums.UserRole;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.PersonMapper;
import com.streamit.streaming_service.mappers.ProfileMapper;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IEmailService;
import com.streamit.streaming_service.services.IPaymentService;
import com.streamit.streaming_service.services.ISubscriptionService;
import com.streamit.streaming_service.services.IUserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    IPaymentService paymentService;
    ISubscriptionService subscriptionService;
    IEmailService emailService;
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ReturnUserDTO register(CreateUserDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email " + userDto.getEmail() + " já cadastrado.");
        }

        LocalDateTime currentDate = LocalDateTime.now();

        PersonModel person = PersonMapper.toModel(userDto); 
        person.setSenha(passwordEncoder.encode(userDto.getSenha()));
        person.setRole(UserRole.USER);

        UserModel user = new UserModel();
        user.setPerson(person);
        user.setCreatedDate(currentDate);

        List<ProfileModel> profiles = new ArrayList<>();
        for (CreateProfileDTO profileDto : userDto.getPerfis()) {
            ProfileModel profile = ProfileMapper.toModel(profileDto, user);
            profiles.add(profile);
        }
        user.setPerfis(profiles);

        SubscriptionModel subscription = subscriptionService.createSubscription(user, currentDate);
        PaymentModel payment = paymentService.createPayment(user, userDto, currentDate);

        user.setSubscription(subscription);
        user.setPayment(payment);

        UserModel savedUser = userRepository.save(user);

        return UserMapper.toDtoReturn(savedUser);
    }

    @Override
    public ReturnUserDTO findUserDtoById(UUID id) {
        return UserMapper.toDtoReturn(findUserModelById(id));
    }

    @Override
    public List<ReturnUserDTO> findAll(Pageable pageable) {
        Page<UserModel> entities = userRepository.findAll(pageable);
        List<ReturnUserDTO> dtos = new ArrayList<>();
        for(UserModel entity : entities) {
            dtos.add(UserMapper.toDtoReturn(entity));
        }
        return dtos;
    }

    @Override
    public ReturnUserDTO updateName(UpdateUserDTO userDto) {
    	UserModel entity = findUserModelById(userDto.getId());
    	entity.getPerson().setNome(userDto.getNome());
        UserModel entitySaved = userRepository.save(entity);
        return UserMapper.toDtoReturn(entitySaved);
    }

    @Override
    public void delete(UUID id) {
        UserModel entity = findUserModelById(id);
        userRepository.delete(entity);
    }
    
    @Transactional
    @Override
    public ReturnUserDTO renovarInscricao(UUID userId, UUID subscriptionId, UUID paymentId, CreatePaymentDTO paymentDto) {
    	UserModel entity = findUserModelById(userId);
        PaymentModel payment = paymentService.processarPagamento(paymentDto, paymentId);
        SubscriptionModel subscription = subscriptionService.renovarInscricao(subscriptionId);
        entity.setPayment(payment);
        entity.setSubscription(subscription);
        UserModel entitySaved = userRepository.save(entity);
        return UserMapper.toDtoReturn(entitySaved);
    }

	@Override
	public boolean getProfilesQuantity(UUID id) {
		UserModel user = findUserModelById(id);
		if(user.getPerfis().size() == 4) {
			return true;
		}
		return false;
	}
	
    @Override
    public void solicitarAlteracaoSenha(String email) {
    	UserModel user = findUserModelByEmail(email);

        String codigoVerificacao = UUID.randomUUID().toString();
        user.setCodigoEmail(codigoVerificacao);
        userRepository.save(user);

        emailService.enviarEmailVerificacao(user.getPerson().getEmail(), codigoVerificacao);
    }

    @Override
    public boolean verificarCodigo(String email, String codigo) {
    	UserModel user = findUserModelByEmail(email);
        if (!user.getCodigoEmail().equals(codigo)) {
            return false;
        }
        user.setCodigoEmail(null);
        userRepository.save(user);
        return true;
    }

    @Override
    public void alterarSenha(String email, String novaSenha) {
    	UserModel user = findUserModelByEmail(email);
        
        String senhaCodificada = passwordEncoder.encode(novaSenha);
        user.getPerson().setSenha(senhaCodificada);
        userRepository.save(user);
    }
    
    public UserModel findUserModelById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
    }
    
    public UserModel findUserModelByEmail(String email) {
    	return userRepository.findByEmail(email)
    			.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email " + email));
    }

}

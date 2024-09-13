package com.streamit.streaming_service.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.login.LoginDTO;
import com.streamit.streaming_service.dtos.payment.CreatePaymentDTO;
import com.streamit.streaming_service.dtos.profile.CreateProfileDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.enums.UserRole;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.ProfileMapper;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.model.ProfileModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.ITokenService;
import com.streamit.streaming_service.services.IUserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    PaymentServiceImpl paymentServiceImpl;
    SubscriptionServiceImpl subscriptionServiceImpl;
    private AuthenticationManager authenticationManager;
    private ITokenService tokenService;
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ReturnUserDTO create(CreateUserDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email " + userDto.getEmail() + " já cadastrado.");
        }

        LocalDateTime currentDate = LocalDateTime.now();
        
        PersonModel person = new PersonModel();
        person.setEmail(userDto.getEmail());
        person.setNome(userDto.getNome());
        person.setSenha(passwordEncoder.encode(userDto.getSenha()));
        person.setRole(UserRole.USER);

        UserModel entity = new UserModel();
        entity.setPerson(person);
        entity.setCreatedDate(currentDate);
        
        List<CreateProfileDTO> profiles = userDto.getPerfis();
        List<ProfileModel> profilesModel = new ArrayList<>();
        
        // cria os perfis
        for(CreateProfileDTO profile : profiles) {
        	profilesModel.add(ProfileMapper.toModel(profile, entity));
        }
        entity.setPerfis(profilesModel);
        
        // cria a inscrição
        SubscriptionModel subscription = subscriptionServiceImpl.createSubscription(entity, currentDate);
        entity.setSubscription(subscription);

        // cria o pagamento
        PaymentModel payment = paymentServiceImpl.createPayment(entity, userDto, currentDate);
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
        PaymentModel payment = paymentServiceImpl.processarPagamento(paymentDto, paymentId);
        SubscriptionModel subscription = subscriptionServiceImpl.renovarInscricao(subscriptionId);
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
	public String login(LoginDTO loginDto) {
    	var email = userRepository.findByEmail(loginDto.getEmail());
    	if(email == null) {
    		throw new UsernameNotFoundException("O email " + loginDto.getEmail() + " não existe.");
    	}
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            PersonModel user = (PersonModel) auth.getPrincipal();
            String token = tokenService.generateToken(user);
            return token;
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Senha incorreta. Tente novamente.", ex);
        } catch (InternalAuthenticationServiceException ex) {
            throw new InternalAuthenticationServiceException("Erro interno de autenticação", ex);
        }
	}
}

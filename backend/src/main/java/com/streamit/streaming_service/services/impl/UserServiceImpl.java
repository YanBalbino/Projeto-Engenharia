package com.streamit.streaming_service.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamit.streaming_service.dtos.renew.RenewDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTO;
import com.streamit.streaming_service.dtos.user.CreateUserDTOWithCreditCard;
import com.streamit.streaming_service.dtos.user.ReturnUserDTO;
import com.streamit.streaming_service.dtos.user.UpdateUserDTO;
import com.streamit.streaming_service.enums.UserRole;
import com.streamit.streaming_service.exceptions.ResourceAlreadyExistsException;
import com.streamit.streaming_service.exceptions.ResourceNotFoundException;
import com.streamit.streaming_service.mappers.PersonMapper;
import com.streamit.streaming_service.mappers.UserMapper;
import com.streamit.streaming_service.model.CreditCardTokenModel;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.model.SubscriptionModel;
import com.streamit.streaming_service.model.UserModel;
import com.streamit.streaming_service.repositories.CreditCardTokenRepository;
import com.streamit.streaming_service.repositories.UserRepository;
import com.streamit.streaming_service.services.IEmailService;
import com.streamit.streaming_service.services.IPaymentService;
import com.streamit.streaming_service.services.ISubscriptionService;
import com.streamit.streaming_service.services.ITokenizationService;
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
    ITokenizationService tokenizationService;
    PasswordEncoder passwordEncoder;
    CreditCardTokenRepository creditCardTokenRepository;

    @Transactional
    @Override
    public ReturnUserDTO registerWithCreditCard(CreateUserDTOWithCreditCard userDto) {
        if (userRepository.findByEmail(userDto.getUserDto().getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email " + userDto.getUserDto().getEmail() + " já cadastrado.");
        }

        LocalDateTime currentDate = LocalDateTime.now();

        PersonModel person = PersonMapper.toModel(userDto.getUserDto()); 
        person.setSenha(passwordEncoder.encode(userDto.getUserDto().getSenha()));
        person.setRole(UserRole.USER);

        UserModel user = new UserModel();
        user.setPerson(person);
        user.setCreatedDate(currentDate);

        SubscriptionModel subscription = subscriptionService.createSubscription(user, currentDate);
        PaymentModel payment = paymentService.createPayment(user, userDto.getUserDto(), currentDate);

        user.setSubscription(subscription);
        user.setPayment(payment);

        UserModel savedUser = userRepository.save(user);
        
        String token = tokenizationService.generateTokenFromCardData(
        		userDto.getCreditCardDto().getCardNumber(),
        		userDto.getCreditCardDto().getCardHolder(),
        		userDto.getCreditCardDto().getExpiryDate(),
        		userDto.getCreditCardDto().getCvv());
        
        CreditCardTokenModel cct = new CreditCardTokenModel();
        cct.setUser(savedUser);
        cct.setToken(token);
        creditCardTokenRepository.save(cct);
        
        return UserMapper.toDtoReturn(savedUser);
    }
    
    @Transactional
    @Override
    public ReturnUserDTO registerWithBankSlip(CreateUserDTO userDto) {
        UserModel savedUser = processUserRegistration(userDto);
        //gerar um qr code
        return UserMapper.toDtoReturn(savedUser);
    }
    
    private UserModel processUserRegistration(CreateUserDTO userDto) {
        LocalDateTime currentDate = LocalDateTime.now();

        PersonModel person = PersonMapper.toModel(userDto); 
        person.setSenha(passwordEncoder.encode(userDto.getSenha()));
        person.setRole(UserRole.USER);

        UserModel user = new UserModel();
        user.setPerson(person);
        user.setCreatedDate(currentDate);

        SubscriptionModel subscription = subscriptionService.createSubscription(user, currentDate);
        PaymentModel payment = paymentService.createPayment(user, userDto, currentDate);

        user.setSubscription(subscription);
        user.setPayment(payment);

        return userRepository.save(user);
    }


    @Override
    public ReturnUserDTO findUserDtoById(UUID id) {
        return UserMapper.toDtoReturn(findUserModelById(id));
    }

    @Override
    public Page<ReturnUserDTO> findAll(Pageable pageable) {
        Page<UserModel> entities = userRepository.findAll(pageable);

        List<ReturnUserDTO> dtos = entities.getContent().stream()
            .map(UserMapper::toDtoReturn) 
            .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
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
    public ReturnUserDTO renovarInscricao(RenewDTO renewDto) {
    	UserModel entity = findUserModelById(renewDto.getIdUser());
        PaymentModel payment = paymentService.processarPagamento(renewDto.getMetodoPagamento(), renewDto.getValor(), renewDto.getIdPayment());
        SubscriptionModel subscription = subscriptionService.renovarInscricao(renewDto.getIdSubscription());
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

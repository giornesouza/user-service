package com.gs.user.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.gs.user.exception.RequiredParamException;
import com.gs.user.exception.UnexpectedParamException;
import com.gs.user.resource.constants.ResourceMessageConstant;
import com.gs.user.service.UserCampaignService;
import com.gs.user.service.UserService;
import com.gs.user.service.dto.UserDTO;

@RestController
@RequestMapping(path = "/api/users", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserResource {

    private final UserService userService;
    private final UserCampaignService userCampaignService;

    public UserResource(UserService userService, UserCampaignService userCampaignService) {
	this.userService = userService;
	this.userCampaignService = userCampaignService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request)
	    throws Exception {

	if (userDTO.getId() != null) {
	    throw new UnexpectedParamException(ResourceMessageConstant.UNEXPECTED_ID_PARAM);
	}

	Optional<UserDTO> user = userService.findByEmail(userDTO.getEmail());
	String responseMessage = null;
	Long resourceId = null;
	
	try {
        	if (user.isPresent()) {
        	    userCampaignService.associate(user.get().getId());
        	    responseMessage = ResourceMessageConstant.REGISTRATION_HAS_ALREADY_BEEN_DONE.toString();
        	    resourceId = user.get().getId();
        	} else {
        	    UserDTO savedUser = userService.save(userDTO);
        	    resourceId = savedUser.getId();
        	    userCampaignService.associate(savedUser.getId());
        	}
	} catch (HttpServerErrorException se) {
	    responseMessage = ResourceMessageConstant.CAMPAIGN_SERVICE_UNAVALIABLE.toString();
	}
	URI location = new URI(request.getRequestURL() + "/" + resourceId);
	return ResponseEntity.created(location).body(responseMessage);
    }

    @GetMapping(path = "/{id:[0-9]+}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
	Optional<UserDTO> userDTO = userService.findById(id);
	if (!userDTO.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	return ResponseEntity.ok(userDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
	return ResponseEntity.ok().body(userService.findAll());
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
	if (userDTO.getId() == null) {
	    throw new RequiredParamException(ResourceMessageConstant.REQUIRED_ID_PARAM);
	}
	Optional<UserDTO> existingUser = userService.findById(userDTO.getId());
	if (!existingUser.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	return ResponseEntity.ok(userService.update(userDTO));

    }

    @DeleteMapping(path = "/{id:[0-9]+}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	Optional<UserDTO> userDTO = userService.findById(id);
	if (!userDTO.isPresent()) {
	    throw new EntityNotFoundException(ResourceMessageConstant.ENTITY_NOT_FOUND);
	}
	userService.delete(id);
	return ResponseEntity.ok().build();
    }

}

package com.gs.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gs.user.domain.User;
import com.gs.user.domain.UserCampaign;
import com.gs.user.repository.UserCampaignRepository;
import com.gs.user.repository.UserRepository;
import com.gs.user.service.dto.CampaignDTO;

@Service
public class UserCampaignService {

    private static final String CAMPAIGN_API_ENDPONT = "https://cryptic-river-18159.herokuapp.com/api/campaigns/team/";
    
    private UserCampaignRepository userCampaignRepository;
    
    private UserRepository userRepository;
    
    public UserCampaignService(UserCampaignRepository userCampaignRepository,  UserRepository userRepository) {
	this.userCampaignRepository = userCampaignRepository;
	this.userRepository = userRepository;
    } 

    public void associate(Long userId) throws Exception {
	
	List<Long> campaignIds = new ArrayList<Long>();
	List<Long> userCampaingIds = new ArrayList<Long>();
	Optional<User> user = userRepository.findById(userId);
	
	if (!user.isPresent()) {
	   return;
	}

	ResponseEntity<CampaignDTO[]> response = consumeCampaignsByTeamService(user.get().getFavouriteTeam());
	
	if (HttpStatus.OK.equals(response.getStatusCode())) {
	    
	    campaignIds = getCampaignIds(Arrays.asList(response.getBody()));
	    userCampaingIds = getUserCampaignIds(user.get().getCampaigns());
	    campaignIds.removeAll(userCampaingIds);
	    
	    campaignIds.forEach(id -> {
		UserCampaign userCampaignToSave = new UserCampaign(user.get(), id);
		userCampaignRepository.save(userCampaignToSave);
	    });
	}
    }
    
    private ResponseEntity<CampaignDTO[]> consumeCampaignsByTeamService(String favouriteTeam) {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate.getForEntity(CAMPAIGN_API_ENDPONT + favouriteTeam, CampaignDTO[].class);
    }
    
    private List<Long> getCampaignIds(List<CampaignDTO> campaignDTOs) {
	return campaignDTOs.stream().map(c -> c.getId()).collect(Collectors.toList());
    }
    
    private List<Long> getUserCampaignIds(List<UserCampaign> userCampaigns) {
	return userCampaigns.stream().map(c -> c.getCampaignId()).collect(Collectors.toList());
    }

}

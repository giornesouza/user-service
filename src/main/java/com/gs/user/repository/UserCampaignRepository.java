package com.gs.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.user.domain.User;
import com.gs.user.domain.UserCampaign;

public interface UserCampaignRepository extends JpaRepository<UserCampaign, Long> {
    
    List<Long> findCampaignIdByUser(User user);

}

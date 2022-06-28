package com.acormier.groupup.service;

import static java.util.stream.Collectors.toList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.dto.UserProfileDto;
import com.acormier.groupup.mapper.ProfileMapper;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ProfileService {
	
	private final UserRepository userRepo;
	private final ProfileMapper profileMap;
    
    //get all userprofiles (not currently in use)
    @Transactional(readOnly=true)
    public List<UserProfileDto> getAll(){
    	return userRepo.findAll()
    			.stream()
    			.map(profileMap::map)
    			.collect(toList());
    } 
    
    //get profile by the username from the pathvariable in the restcontroller
    @Transactional(readOnly=true)
    public UserProfileDto getProfile(String username) {
    	User user = userRepo.findByUsername(username).orElseThrow();
    	return profileMap.map(user);
    }

    
}

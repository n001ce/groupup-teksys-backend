package com.acormier.groupup.controller;

import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.dto.UserProfileDto;
import com.acormier.groupup.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
	private final ProfileService profileService;
	
	//get users profile
	@GetMapping("/{username}")
	public ResponseEntity<UserProfileDto> getProfile(@PathVariable String username){
		log.info("userProfile loaded");
		return ResponseEntity.status(OK).body(profileService.getProfile(username));
	}
	
	//get all profiles *not currently in use
	@GetMapping
	public ResponseEntity<List<UserProfileDto>> getAllProfiles(){
		return ResponseEntity.status(OK).body(profileService.getAll());
	}
	
}

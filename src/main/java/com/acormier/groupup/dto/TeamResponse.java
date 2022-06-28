package com.acormier.groupup.dto;
import java.time.Instant;
import java.util.Set;

import com.acormier.groupup.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Dto created from the backend and pushed to the frontend

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
	private Long teamId;
	private String teamName;
	private String url;
	private String console;
	private String teamLeader;
	private Integer teamSize;
	private String gameTitle;
	private String background_image;
	private Integer commentCount;
	private Integer teamCount;
	private String createdDate;
	private Set<User> teamMembers;
}

package com.perscholas.groupupspringbootbackend.dto;

import java.util.List;

import com.perscholas.groupupspringbootbackend.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
	private Long teamId;
	private String teamName;
	private String url;
	private String console;
	private String teamLeader;
	private String gameTitle;
	private Integer commentCount;
	private Integer teamCount;
	private List<User> teamMembers;
}

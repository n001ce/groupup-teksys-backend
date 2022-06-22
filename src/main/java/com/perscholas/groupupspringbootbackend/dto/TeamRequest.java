package com.perscholas.groupupspringbootbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
	private Long teamId;
	private String teamName;
	private String gameTitle;
	private String url;
	private String console;
	private Integer teamSize;
}
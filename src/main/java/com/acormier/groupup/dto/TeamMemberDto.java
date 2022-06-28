package com.acormier.groupup.dto;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO created from request in the frontend and backend

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto {
	private String username;
	private String url;
}
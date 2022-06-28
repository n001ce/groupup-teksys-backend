package com.acormier.groupup.dto;
import java.util.Set;
import com.acormier.groupup.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO created from the backend and pushed to the frontend

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
	private Long userId;
	private String username;
	private String joinedDate;
	private Set<Game> games;
	private Set<TeamResponse> teams;
	private Set<TeamResponse> joinedTeams;
	private Set<CommentsDto> comments;
}

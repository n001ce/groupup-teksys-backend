package com.acormier.groupup.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO data transfered from the front end then updated in the backend with data.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
	private Long id;
	private Long teamId;
	private String teamName;
	private String createdDate;
	private String text;
	private String userName;
}

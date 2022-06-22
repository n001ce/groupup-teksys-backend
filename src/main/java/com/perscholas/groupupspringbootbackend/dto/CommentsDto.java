package com.perscholas.groupupspringbootbackend.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
	private Long id;
	private Long teamId;
	private Long gameId;
	private Instant createdDate;
	private String text;
	private String userName;
}

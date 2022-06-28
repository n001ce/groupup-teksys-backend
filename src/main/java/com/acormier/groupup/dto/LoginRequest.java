package com.acormier.groupup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO created from front end data and built with backend db.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	private String username;
	private String password;
}

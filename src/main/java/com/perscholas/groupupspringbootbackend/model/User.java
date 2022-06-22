package com.perscholas.groupupspringbootbackend.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1512440051113351784L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(unique=true)
	@NotBlank(message="Username is required")
	private String username;
	@NotBlank(message = "Password is required")
	private String password;
	@Column(unique=true)
	@Email
	@NotEmpty(message = "Email is required")
	private String email;
	private Instant created;
	private boolean enabled;
	
	@OneToMany
    @JoinTable(
    		name= "user_games",
    		joinColumns = @JoinColumn(name="userId"),
    		inverseJoinColumns = @JoinColumn(name="gameTitle")
    		)
	private List<Game> collectedGames;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinTable(name="user_teams",
		joinColumns= {@JoinColumn(name="userId", referencedColumnName="userId")},
		inverseJoinColumns= {@JoinColumn(name="teamId", referencedColumnName="teamId")}
			)
	private List<Team> teams;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Team team;
}




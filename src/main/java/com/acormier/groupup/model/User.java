package com.acormier.groupup.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable{	
	private static final long serialVersionUID = 1L;
	
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

	
	@JsonManagedReference 
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "collected_games", 
            joinColumns = { @JoinColumn(name = "fk_user") }, 
            inverseJoinColumns = { @JoinColumn(name = "fk_game") })
    private Set<Game> collectedGames;
    
}




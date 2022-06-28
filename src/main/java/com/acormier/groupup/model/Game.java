package com.acormier.groupup.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long gameId;
	private Long rawgId;
	
    private String gameTitle;
	@Column(columnDefinition="TEXT")
	
	private String description;
	@Column(columnDefinition="TEXT")
    private String background_image;
    
	@JsonIgnore
	@JsonManagedReference
    @OneToMany(mappedBy="game")
    private List<Team> teams;
    
    @JsonBackReference
    @ManyToMany(mappedBy="collectedGames")
    private Set<User> usersCollected;
 
}
package com.perscholas.groupupspringbootbackend.model;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Team implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7254239387456818541L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;
	
	@NotBlank(message="Team Name cannot be empty or Null")
	private String teamName;
	
	@Nullable
	private String teamSize;
	
	@NotBlank(message="Console Required")
	private String console;	
	private String url;
	private Instant createdDate;
	
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User teamLeader;
	
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval=true)
	@JoinTable(name="team_members",
		joinColumns= {@JoinColumn(name="teamId", referencedColumnName="teamId")},
		inverseJoinColumns= {@JoinColumn(name="memberId", referencedColumnName="userId")}
			)
	private List<User> teamMembers;
	
	
	@ManyToOne
	private Game game;
	
}
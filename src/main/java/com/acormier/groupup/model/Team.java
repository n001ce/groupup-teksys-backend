package com.acormier.groupup.model;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Team{	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamId;
	
	@NotBlank(message="Team Name cannot be empty or Null")
	private String teamName;
	
	@Nullable
	private Integer teamSize;
	
	@NotBlank(message="Console Required")
	private String console;	
	private String url;
	private Instant createdDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="teamLeader", nullable=false)
	private User teamLeader;
	
	@JsonBackReference
	@ManyToMany
	@JoinTable(name="team_members",
		joinColumns= {@JoinColumn(name="fk_team")},
		inverseJoinColumns= {@JoinColumn(name="fk_user")}
			)
	private Set<User> teamMembers;
	
	@JsonBackReference
	@ManyToOne
	private Game game;

	
}
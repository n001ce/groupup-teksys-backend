package com.acormier.groupup.model;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String text;
	
	private Instant createdDate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name = "teamId", referencedColumnName = "teamId")
	private Team team;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
}

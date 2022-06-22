package com.perscholas.groupupspringbootbackend.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    
	
    @OneToMany(fetch=FetchType.LAZY)
    private List<Team> teams;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<Comment> comments;
}
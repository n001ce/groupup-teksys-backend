package com.perscholas.groupupspringbootbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.perscholas.groupupspringbootbackend.dto.CommentsDto;
import com.perscholas.groupupspringbootbackend.service.CommentService;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Void> createTeamComment(@RequestBody CommentsDto commentsDto){
		commentService.save(commentsDto);
		return new ResponseEntity<>(CREATED);
	}
	
	
	@GetMapping("by-team/{teamId}")
	    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long teamId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForTeam(teamId));
    }

    @GetMapping("by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(userName));
    }
    
    @GetMapping("by-game/{gameId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForGame(@PathVariable Long gameId){
    	return ResponseEntity.status(OK).body(commentService.getAllCommentsForGame(gameId));
    }
    
    
}

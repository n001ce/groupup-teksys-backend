package com.acormier.groupup.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.dto.CommentsDto;
import com.acormier.groupup.service.CommentService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
	private final CommentService commentService;
	
	//post mapping to post comment to team page
	@PostMapping
	public ResponseEntity<CommentsDto> createTeamComment(@RequestBody CommentsDto commentsDto){
		commentService.saveToTeam(commentsDto);
		return new ResponseEntity<>(CREATED);
	}
	
	//Get all comemnts for team
	@GetMapping("by-team/{teamId}")
	    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long teamId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForTeam(teamId));
    }
	
	//get all comments made by the user
    @GetMapping("by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(userName));
    }
    
    //delete the comment on the team page
    @PostMapping("/delete")
	public ResponseEntity<String> logout(@Valid @RequestBody CommentsDto comment) {
		commentService.deleteComment(comment.getId());
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully");
	}
    
    //edit the comment
    @PutMapping("/edit")
    public ResponseEntity<CommentsDto> editComment(@RequestBody CommentsDto commentDto){
    	commentService.editComment(commentDto);
    	return new ResponseEntity<>(CREATED);
    }
}

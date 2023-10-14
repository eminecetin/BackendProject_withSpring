package com.archis.starter.controller;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.dto.CommentDto;
import com.archis.starter.service.CategoryService;
import com.archis.starter.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getAll(){
        return commentService.getAllComment();
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable Long id) throws Exception{
        return commentService.getCommentById(id);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<String> createCategory(@RequestBody CommentDto commentDto,@PathVariable long id) throws Exception {
        return ResponseEntity.ok(commentService.createComment(commentDto, id));
    }

    @PutMapping("/update/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) throws Exception {
        return commentService.updateComment(id,commentDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteComment(@PathVariable("userId") Long id) throws Exception {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }



}

package com.archis.starter.service;


import com.archis.starter.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComment();
    CommentDto getCommentById(Long id) throws Exception;

    String createComment(CommentDto commentDto, Long productId) throws Exception;

    CommentDto updateComment(Long id, CommentDto commentDto) throws Exception;
    String deleteComment(Long id) throws Exception;
}

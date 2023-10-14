package com.archis.starter.service.impl;

import com.archis.starter.dto.CommentDto;
import com.archis.starter.dto.ProductDto;
import com.archis.starter.entity.Comment;
import com.archis.starter.repository.CommentRepository;
import com.archis.starter.service.CommentService;
import com.archis.starter.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CommentDto> getAllComment() {
        return commentRepository.findAll()
                .stream().map(comment->modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) throws Exception {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            throw new Exception("ıd'li user bulunamadı..");
        }
        return modelMapper.map(comment.get(),CommentDto.class);    }


    @Override
    public String createComment(CommentDto commentDto, Long productId) throws Exception {
        ProductDto product=productService.getProductyById(productId);
        commentDto.setProduct(product);

        modelMapper.map(commentRepository.save(modelMapper.map(commentDto,Comment.class)),
                CommentDto.class);
        return "Yorumunuz başarılı Şekilde oluşturuldu";}


    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) throws Exception {
        Optional<Comment> resultComment=commentRepository.findById(id);
        if(!resultComment.isPresent()){
            throw new Exception("id'li user bulunamadı..");
        }
        resultComment.get().setText(commentDto.getText());

        return modelMapper.map(commentRepository.save(resultComment.get()),CommentDto.class);      }

    @Override
    public String deleteComment(Long id) throws Exception {
        Optional<Comment> comment=commentRepository.findById(id);
        if(comment.isPresent()){
            throw new Exception("id'li kullanıcı bulunamadı!!");
        }
        commentRepository.deleteById(id);
        return "Silindi!";      }
}

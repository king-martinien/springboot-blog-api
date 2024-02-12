package com.kingmartinien.springbootblogapi.mapper;

import com.kingmartinien.springbootblogapi.dto.CommentDto;
import com.kingmartinien.springbootblogapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> comments);

    List<Comment> toEntityList(List<CommentDto> commentDtos);

}

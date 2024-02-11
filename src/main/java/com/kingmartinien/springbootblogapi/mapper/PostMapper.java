package com.kingmartinien.springbootblogapi.mapper;

import com.kingmartinien.springbootblogapi.dto.PostDto;
import com.kingmartinien.springbootblogapi.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);

    List<PostDto> toDtoList(List<Post> posts);

    List<Post> toEntityList(List<PostDto> postDtos);
}

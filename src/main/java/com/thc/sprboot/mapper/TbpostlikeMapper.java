package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostlikeDto;

import java.util.List;

public interface TbpostlikeMapper {
    TbpostlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostlikeDto.DetailResDto> list(TbpostlikeDto.ListReqDto param);

    List<TbpostlikeDto.DetailResDto> scrollList(TbpostlikeDto.ScrollListReqDto param);
    List<TbpostlikeDto.DetailResDto> pagedList(TbpostlikeDto.PagedListReqDto param);
    int pagedListCount(TbpostlikeDto.PagedListReqDto param);
}

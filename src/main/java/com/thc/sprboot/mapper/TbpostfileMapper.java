package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileMapper {
    TbpostfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostfileDto.DetailResDto> list(TbpostfileDto.ListReqDto param);

    List<TbpostfileDto.DetailResDto> scrollList(TbpostfileDto.ScrollListReqDto param);
    List<TbpostfileDto.DetailResDto> pagedList(TbpostfileDto.PagedListReqDto param);
    int pagedListCount(TbpostfileDto.PagedListReqDto param);
}

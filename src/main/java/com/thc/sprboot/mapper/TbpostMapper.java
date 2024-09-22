package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostDto;

import java.util.List;

public interface TbpostMapper {
    TbpostDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbpostDto.DetailResDto> list(TbpostDto.ListServDto param);

    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListServDto param);
    List<TbpostDto.DetailResDto> pagedList(TbpostDto.PagedListServDto param);
    int pagedListCount(TbpostDto.PagedListServDto param);
}

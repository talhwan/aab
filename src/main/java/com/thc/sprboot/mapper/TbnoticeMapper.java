package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbnoticeDto;

import java.util.List;

public interface TbnoticeMapper {
    TbnoticeDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListServDto param);

    List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListServDto param);
    List<TbnoticeDto.DetailResDto> pagedList(TbnoticeDto.PagedListServDto param);
    int pagedListCount(TbnoticeDto.PagedListServDto param);
}

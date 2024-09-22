package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
    TbuserDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbuserDto.DetailResDto> list(TbuserDto.ListServDto param);

    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListServDto param);
    List<TbuserDto.DetailResDto> pagedList(TbuserDto.PagedListServDto param);
    int pagedListCount(TbuserDto.PagedListServDto param);
}

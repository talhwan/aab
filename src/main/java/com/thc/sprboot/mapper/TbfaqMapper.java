package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbfaqDto;

import java.util.List;

public interface TbfaqMapper {
    TbfaqDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbfaqDto.DetailResDto> list(TbfaqDto.ListServDto param);

    List<TbfaqDto.DetailResDto> scrollList(TbfaqDto.ScrollListServDto param);
    List<TbfaqDto.DetailResDto> pagedList(TbfaqDto.PagedListServDto param);
    int pagedListCount(TbfaqDto.PagedListServDto param);
}

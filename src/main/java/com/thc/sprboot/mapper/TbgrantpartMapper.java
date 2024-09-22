package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantpartDto;

import java.util.List;

public interface TbgrantpartMapper {
	TbgrantpartDto.DetailResDto detail(DefaultDto.DetailServDto param);
	List<TbgrantpartDto.DetailResDto> list(TbgrantpartDto.ListServDto param);

	List<TbgrantpartDto.DetailResDto> scrollList(TbgrantpartDto.ScrollListServDto param);
	List<TbgrantpartDto.DetailResDto> pagedList(TbgrantpartDto.PagedListServDto param);
	int pagedListCount(TbgrantpartDto.PagedListServDto param);
}
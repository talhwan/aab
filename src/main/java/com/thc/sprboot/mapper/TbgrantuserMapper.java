package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantuserDto;

import java.util.List;

public interface TbgrantuserMapper {
	TbgrantuserDto.DetailResDto detail(DefaultDto.DetailServDto param);
	List<TbgrantuserDto.DetailResDto> list(TbgrantuserDto.ListServDto param);

	List<TbgrantuserDto.DetailResDto> scrollList(TbgrantuserDto.ScrollListServDto param);
	List<TbgrantuserDto.DetailResDto> pagedList(TbgrantuserDto.PagedListServDto param);
	int pagedListCount(TbgrantuserDto.PagedListServDto param);
}
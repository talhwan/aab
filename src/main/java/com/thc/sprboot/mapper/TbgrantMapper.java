package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;

import java.util.List;

//2024-07-08 추가(클래스 처음 추가함)
public interface TbgrantMapper {
	TbgrantDto.DetailResDto access(TbgrantDto.AccessServDto param);
	/**/
	TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param);
	List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param);

	List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param);
	List<TbgrantDto.DetailResDto> pagedList(TbgrantDto.PagedListServDto param);
	int pagedListCount(TbgrantDto.PagedListServDto param);
}
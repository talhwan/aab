package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtMapper {
    TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param);

    List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param);
    List<TbpostcmtDto.DetailResDto> pagedList(TbpostcmtDto.PagedListReqDto param);
    int pagedListCount(TbpostcmtDto.PagedListReqDto param);
}

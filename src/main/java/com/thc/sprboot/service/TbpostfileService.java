package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileService {

    /**/
    TbpostfileDto.CreateResDto create(TbpostfileDto.CreateReqDto param);
    TbpostfileDto.CreateResDto update(TbpostfileDto.UpdateReqDto param);
    TbpostfileDto.CreateResDto delete(TbpostfileDto.UpdateReqDto param);
    TbpostfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostfileDto.DetailResDto> list(TbpostfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostfileDto.PagedListReqDto param);
    List<TbpostfileDto.DetailResDto> scrollList(TbpostfileDto.ScrollListReqDto param);
}

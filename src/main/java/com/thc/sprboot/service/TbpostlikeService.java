package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostlikeDto;

import java.util.List;

public interface TbpostlikeService {

    boolean exist(TbpostlikeDto.CreateReqDto param);
    TbpostlikeDto.CreateResDto toggle(TbpostlikeDto.CreateReqDto param);
    /**/
    TbpostlikeDto.CreateResDto create(TbpostlikeDto.CreateReqDto param);
    TbpostlikeDto.CreateResDto update(TbpostlikeDto.UpdateReqDto param);
    TbpostlikeDto.CreateResDto delete(TbpostlikeDto.UpdateReqDto param);
    TbpostlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostlikeDto.DetailResDto> list(TbpostlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostlikeDto.PagedListReqDto param);
    List<TbpostlikeDto.DetailResDto> scrollList(TbpostlikeDto.ScrollListReqDto param);
}

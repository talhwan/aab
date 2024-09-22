package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbfaqDto;

import java.util.List;

public interface TbfaqService {
    TbfaqDto.CreateResDto sequence(TbfaqDto.SequenceServDto param);
    /**/
    TbfaqDto.CreateResDto create(TbfaqDto.CreateServDto param);
    TbfaqDto.CreateResDto update(TbfaqDto.UpdateServDto param);
    TbfaqDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbfaqDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbfaqDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbfaqDto.DetailResDto> list(TbfaqDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbfaqDto.PagedListServDto param);
    List<TbfaqDto.DetailResDto> scrollList(TbfaqDto.ScrollListServDto param);
}

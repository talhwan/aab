package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbnoticeDto;

import java.util.List;

public interface TbnoticeService {
    TbnoticeDto.CreateResDto create(TbnoticeDto.CreateServDto param);
    TbnoticeDto.CreateResDto update(TbnoticeDto.UpdateServDto param);
    TbnoticeDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbnoticeDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbnoticeDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbnoticeDto.PagedListServDto param);
    List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListServDto param);
}

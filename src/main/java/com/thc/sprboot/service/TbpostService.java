package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostDto;

import java.util.List;

public interface TbpostService {
    TbpostDto.CreateResDto create(TbpostDto.CreateServDto param);
    TbpostDto.CreateResDto update(TbpostDto.UpdateServDto param);
    TbpostDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbpostDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbpostDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbpostDto.DetailResDto> list(TbpostDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListServDto param);
    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListServDto param);
}

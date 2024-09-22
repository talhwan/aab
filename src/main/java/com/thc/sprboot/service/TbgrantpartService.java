package com.thc.sprboot.service;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantpartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TbgrantpartService {
    TbgrantpartDto.CreateResDto toggle(TbgrantpartDto.ToggleServDto params);
    /**/
    TbgrantpartDto.CreateResDto create(TbgrantpartDto.CreateServDto param);
    TbgrantpartDto.CreateResDto update(TbgrantpartDto.UpdateServDto param);
    TbgrantpartDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbgrantpartDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbgrantpartDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantpartDto.DetailResDto> list(TbgrantpartDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbgrantpartDto.PagedListServDto param);
    List<TbgrantpartDto.DetailResDto> scrollList(TbgrantpartDto.ScrollListServDto param);
}

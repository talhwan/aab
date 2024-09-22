package com.thc.sprboot.service;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantuserDto;
import org.springframework.stereotype.Service;

import java.util.List;

//2024-07-09 추가(클래스 처음 추가함)
@Service
public interface TbgrantuserService {
    /**/
    TbgrantuserDto.CreateResDto create(TbgrantuserDto.CreateServDto param);
    TbgrantuserDto.CreateResDto update(TbgrantuserDto.UpdateServDto param);
    TbgrantuserDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbgrantuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbgrantuserDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantuserDto.DetailResDto> list(TbgrantuserDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbgrantuserDto.PagedListServDto param);
    List<TbgrantuserDto.DetailResDto> scrollList(TbgrantuserDto.ScrollListServDto param);
}

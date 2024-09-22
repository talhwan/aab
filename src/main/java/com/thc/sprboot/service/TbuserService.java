package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbuserDto;

import java.util.List;

public interface TbuserService {

    TbuserDto.SnsLoginResDto sns(TbuserDto.CreateServDto param);
    TbuserDto.CreateResDto logout(DefaultDto.DetailReqDto param);
    TbuserDto.CreateResDto confirm(TbuserDto.ConfirmReqDto param);
    TbuserDto.CreateResDto email(TbuserDto.UidReqDto param);
    TbuserDto.CreateResDto id(TbuserDto.UidReqDto param);
    TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param);

    /**/

    TbuserDto.CreateResDto create(TbuserDto.CreateServDto param);
    TbuserDto.CreateResDto update(TbuserDto.UpdateServDto param);
    TbuserDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbuserDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbuserDto.DetailResDto> list(TbuserDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbuserDto.PagedListServDto param);
    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListServDto param);
}

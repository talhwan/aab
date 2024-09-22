package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbnotice;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbnoticeDto;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.mapper.TbnoticeMapper;
import com.thc.sprboot.repository.TbnoticeRepository;
import com.thc.sprboot.service.TbnoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbnoticeServiceImpl implements TbnoticeService {

    private final TbnoticeRepository tbnoticeRepository;
    private final TbnoticeMapper tbnoticeMapper;
    public TbnoticeServiceImpl(
            TbnoticeRepository tbnoticeRepository
            , TbnoticeMapper tbnoticeMapper
    ) {
        this.tbnoticeRepository = tbnoticeRepository;
        this.tbnoticeMapper = tbnoticeMapper;
    }

    @Override
    public TbnoticeDto.CreateResDto create(TbnoticeDto.CreateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbnoticeDto.CreateResDto createResDto = tbnoticeRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbnoticeDto.CreateResDto update(TbnoticeDto.UpdateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        Tbnotice tbnotice = tbnoticeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));

        if(param.getDeleted() != null){
            tbnotice.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbnotice.setProcess(param.getProcess());
        }
        if(param.getCate() != null){
            tbnotice.setCate(param.getCate());
        }
        if(param.getTitle() != null){
            tbnotice.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbnotice.setContent(param.getContent());
        }
        if(param.getImg() != null){
            tbnotice.setImg(param.getImg());
        }
        tbnoticeRepository.save(tbnotice);
        return tbnotice.toCreateResDto();
    }
    public TbnoticeDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbnoticeDto.UpdateServDto newParam = TbnoticeDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
        return update(newParam);
    }
    public TbnoticeDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbnoticeDto.UpdateServDto newParam = TbnoticeDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
            TbnoticeDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbnoticeDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbnoticeDto.DetailResDto detail(DefaultDto.DetailServDto param){
        //if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbnoticeDto.DetailResDto selectResDto = get(param);
        return selectResDto;
    }

    public TbnoticeDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbnoticeDto.DetailResDto selectResDto = tbnoticeMapper.detail(param);
        return selectResDto;
    }

    @Override
    public List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListServDto param){
        return detailList(tbnoticeMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbnoticeDto.PagedListServDto param){
        int[] returnSize = param.init(tbnoticeMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbnoticeMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListServDto param){
        param.init();
        return detailList(tbnoticeMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbnoticeDto.DetailResDto> detailList(List<TbnoticeDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbnoticeDto.DetailResDto> newList = new ArrayList<>();
        for(TbnoticeDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}

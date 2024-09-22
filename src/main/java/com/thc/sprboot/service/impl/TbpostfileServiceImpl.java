package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbpostfile;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostfileDto;
import com.thc.sprboot.mapper.TbpostfileMapper;
import com.thc.sprboot.repository.TbpostfileRepository;
import com.thc.sprboot.service.TbpostfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostfileServiceImpl implements TbpostfileService {

    private final TbpostfileRepository tbpostfileRepository;
    private final TbpostfileMapper tbpostfileMapper;
    public TbpostfileServiceImpl(
            TbpostfileRepository tbpostfileRepository
            ,TbpostfileMapper tbpostfileMapper
    ) {
        this.tbpostfileRepository = tbpostfileRepository;
        this.tbpostfileMapper = tbpostfileMapper;
    }

    /**/

    @Override
    public TbpostfileDto.CreateResDto create(TbpostfileDto.CreateReqDto param){
        return tbpostfileRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostfileDto.CreateResDto update(TbpostfileDto.UpdateReqDto param){
        Tbpostfile tbpostfile = tbpostfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpostfile.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpostfile.setProcess(param.getProcess());
        }
        tbpostfileRepository.save(tbpostfile);
        return tbpostfile.toCreateResDto();
    }
    @Override
    public TbpostfileDto.CreateResDto delete(TbpostfileDto.UpdateReqDto param){
        Tbpostfile tbpostfile = tbpostfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbpostfileRepository.delete(tbpostfile);
        return TbpostfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbpostfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbpostfileDto.DetailResDto selectResDto = tbpostfileMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbpostfileDto.DetailResDto> list(TbpostfileDto.ListReqDto param){
        return detailList(tbpostfileMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostfileDto.PagedListReqDto param) {
        /*int[] res = param.init(tbpostfileMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbpostfileMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbpostfileDto.DetailResDto> scrollList(TbpostfileDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostfileMapper.scrollList(param));
    }

    public List<TbpostfileDto.DetailResDto> detailList(List<TbpostfileDto.DetailResDto> list){
        List<TbpostfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostfileDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}

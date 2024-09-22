package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbgrantuser;
import com.thc.sprboot.domain.Tbuser;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantuserDto;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.mapper.TbgrantuserMapper;
import com.thc.sprboot.repository.TbgrantuserRepository;
import com.thc.sprboot.repository.TbuserRepository;
import com.thc.sprboot.service.TbgrantuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgrantuserServiceImpl implements TbgrantuserService {

    private final TbgrantuserRepository tbgrantuserRepository;
    private final TbgrantuserMapper tbgrantuserMapper;
    private final TbuserRepository tbuserRepository;
    public TbgrantuserServiceImpl(
            TbgrantuserRepository tbgrantuserRepository
            , TbgrantuserMapper tbgrantuserMapper
            , TbuserRepository tbuserRepository
    ) {
        this.tbgrantuserRepository = tbgrantuserRepository;
        this.tbgrantuserMapper = tbgrantuserMapper;
        this.tbuserRepository = tbuserRepository;
    }


    /**/

    @Override
    public TbgrantuserDto.CreateResDto create(TbgrantuserDto.CreateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException(""); }

        //사용자 아이디로도 추가 가능
        Tbuser tbuser =  tbuserRepository.findByUsername(param.getTbuserId());
        if(tbuser != null){
            param.setTbuserId(tbuser.getId());
        }

        Tbgrantuser tbgrantuser = tbgrantuserRepository.findByTbgrantIdAndTbuserId(param.getTbgrantId(), param.getTbuserId());
        if(tbgrantuser != null){
            //혹시 이미 존재하는 정보일 경우 대비
            return tbgrantuser.toCreateResDto();
        }
        TbgrantuserDto.CreateResDto createResDto = tbgrantuserRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbgrantuserDto.CreateResDto update(TbgrantuserDto.UpdateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException(""); }

        Tbgrantuser tbgrantuser = tbgrantuserRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin()){
            throw new NoAuthorizationException("no auth");
        }
        if(param.getDeleted() != null){
            tbgrantuser.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbgrantuser.setProcess(param.getProcess());
        }
        if(param.getTbgrantId() != null){
            tbgrantuser.setTbgrantId(param.getTbgrantId());
        }
        if(param.getTbuserId() != null){
            tbgrantuser.setTbuserId(param.getTbuserId());
        }
        tbgrantuserRepository.save(tbgrantuser);
        return tbgrantuser.toCreateResDto();
    }
    public TbgrantuserDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbgrantuserDto.UpdateServDto newParam = TbgrantuserDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbgrantuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbgrantuserDto.UpdateServDto newParam = TbgrantuserDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbgrantuserDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbgrantuserDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbgrantuserDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbgrantuserDto.DetailResDto selectResDto = get(param);
        return selectResDto;
    }

    public TbgrantuserDto.DetailResDto get(DefaultDto.DetailServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException(""); }

        TbgrantuserDto.DetailResDto selectResDto = tbgrantuserMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbgrantuserDto.DetailResDto> list(TbgrantuserDto.ListServDto param){
        return detailList(tbgrantuserMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbgrantuserDto.PagedListServDto param){
        int[] returnSize = param.init(tbgrantuserMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbgrantuserMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbgrantuserDto.DetailResDto> scrollList(TbgrantuserDto.ScrollListServDto param){
        param.init();
        return detailList(tbgrantuserMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbgrantuserDto.DetailResDto> detailList(List<TbgrantuserDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbgrantuserDto.DetailResDto> newList = new ArrayList<>();
        for(TbgrantuserDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}

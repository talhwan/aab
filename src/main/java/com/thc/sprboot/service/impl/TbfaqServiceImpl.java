package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbfaq;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbfaqDto;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.exception.NoMatchingDataException;
import com.thc.sprboot.mapper.TbfaqMapper;
import com.thc.sprboot.repository.TbfaqRepository;
import com.thc.sprboot.service.TbfaqService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfaqServiceImpl implements TbfaqService {

    private final TbfaqRepository tbfaqRepository;
    private final TbfaqMapper tbfaqMapper;
    public TbfaqServiceImpl(
            TbfaqRepository tbfaqRepository
            , TbfaqMapper tbfaqMapper
    ) {
        this.tbfaqRepository = tbfaqRepository;
        this.tbfaqMapper = tbfaqMapper;
    }

    public TbfaqDto.CreateResDto sequence(TbfaqDto.SequenceServDto param){
        //권한 확인
        if(!param.isAdmin()){ throw new NoAuthorizationException(""); }

        Tbfaq tbfaq = tbfaqRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        int nowSequence = tbfaq.getSequence(); // 현재 순번을 알아본다. // 2

        int targetSequence = nowSequence; // 2
        if("up".equals(param.getWay())){
            targetSequence++; // 3!!
        } else {
            targetSequence--; // 1
        }
        if(targetSequence == 0 || targetSequence > tbfaqMapper.pagedListCount(new TbfaqDto.PagedListServDto())){
            return null;
        } else {
            //잠시 순번에서 제외
            update(TbfaqDto.UpdateServDto.builder().id(tbfaq.getId()).sequence(-1).isAdmin(param.isAdmin()).build()); // 2-> -1
            //바꾸고자 하는 순번의 정보에 이전 순번 저장
            Tbfaq targetTbfaq = tbfaqRepository.findBySequence(targetSequence); // 현재 3번에 위치한 애 정보 가져오기
            update(TbfaqDto.UpdateServDto.builder().id(targetTbfaq.getId()).sequence(nowSequence).isAdmin(param.isAdmin()).build()); // 3번애 있는 애를 2번으로 업데이트
            //다시 수정하고 리턴
            return update(TbfaqDto.UpdateServDto.builder().id(tbfaq.getId()).sequence(targetSequence).isAdmin(param.isAdmin()).build()); //2번에 있는 애를 3번으로 업데이트
        }
    }

    /**/

    @Override
    public TbfaqDto.CreateResDto create(TbfaqDto.CreateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        param.setSequence(tbfaqMapper.pagedListCount(new TbfaqDto.PagedListServDto()) + 1);
        TbfaqDto.CreateResDto createResDto = tbfaqRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbfaqDto.CreateResDto update(TbfaqDto.UpdateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        Tbfaq tbfaq = tbfaqRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));

        if(param.getDeleted() != null){
            tbfaq.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbfaq.setProcess(param.getProcess());
        }
        if(param.getSequence() != null){
            tbfaq.setSequence(param.getSequence());
        }
        if(param.getCate() != null){
            tbfaq.setCate(param.getCate());
        }
        if(param.getTitle() != null){
            tbfaq.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbfaq.setContent(param.getContent());
        }
        if(param.getImg() != null){
            tbfaq.setImg(param.getImg());
        }
        tbfaqRepository.save(tbfaq);
        return tbfaq.toCreateResDto();
    }
    public TbfaqDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbfaqDto.UpdateServDto newParam = TbfaqDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
        return update(newParam);
    }
    public TbfaqDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbfaqDto.UpdateServDto newParam = TbfaqDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
            TbfaqDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbfaqDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbfaqDto.DetailResDto detail(DefaultDto.DetailServDto param){
        //if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbfaqDto.DetailResDto selectResDto = get(param);
        return selectResDto;
    }

    public TbfaqDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbfaqDto.DetailResDto selectResDto = tbfaqMapper.detail(param);
        return selectResDto;
    }

    @Override
    public List<TbfaqDto.DetailResDto> list(TbfaqDto.ListServDto param){
        return detailList(tbfaqMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbfaqDto.PagedListServDto param){
        int[] returnSize = param.init(tbfaqMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbfaqMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbfaqDto.DetailResDto> scrollList(TbfaqDto.ScrollListServDto param){
        param.init();
        return detailList(tbfaqMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbfaqDto.DetailResDto> detailList(List<TbfaqDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbfaqDto.DetailResDto> newList = new ArrayList<>();
        for(TbfaqDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}

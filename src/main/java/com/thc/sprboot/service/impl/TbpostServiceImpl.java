package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbpost;
import com.thc.sprboot.dto.*;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.mapper.TbpostMapper;
import com.thc.sprboot.repository.TbpostRepository;
import com.thc.sprboot.service.TbpostService;
import com.thc.sprboot.service.TbpostfileService;
import com.thc.sprboot.service.TbpostlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    private final TbpostfileService tbpostfileService;
    private final TbpostlikeService tbpostlikeService;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            , TbpostMapper tbpostMapper
            , TbpostfileService tbpostfileService
            , TbpostlikeService tbpostlikeService
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
        this.tbpostfileService = tbpostfileService;
        this.tbpostlikeService = tbpostlikeService;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateServDto param){
        //사용자 정보 강제 입력
        param.setTbuserId(param.getReqTbuserId());

        TbpostDto.CreateResDto createResDto = tbpostRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbpostfileUrls().size();i++){
            tbpostfileService.create(
                    TbpostfileDto.CreateReqDto.builder()
                            .tbpostId(createResDto.getId())
                            .type(param.getTbpostfileTypes().get(i))
                            .url(param.getTbpostfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateServDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin() && !tbpost.getTbuserId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }

        if(param.getDeleted() != null){
            tbpost.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpost.setProcess(param.getProcess());
        }

        /*if(param.getTbuserId() != null){
            tbpost.setTbuserId(param.getTbuserId());
        }*/
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbpost.setCountread(param.getCountread());
        }
        */
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }
    public TbpostDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbpostDto.UpdateServDto newParam = TbpostDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbpostDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbpostDto.UpdateServDto newParam = TbpostDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbpostDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbpostDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbpostDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbpostDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbpost tbpost = tbpostRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbpost.setCountread(++countread);
        tbpostRepository.save(tbpost);

        //조회수 업데이트!!
        update(TbpostDto.UpdateServDto.builder().id(selectResDto.getId()).countread(++countread).isAdmin(true).build());
        return selectResDto;
    }

    public TbpostDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbpostDto.DetailResDto selectResDto = tbpostMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbpostfiles(
                tbpostfileService.list(TbpostfileDto.ListReqDto.builder().tbpostId(selectResDto.getId()).build())
        );
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbpostlikeService.exist(TbpostlikeDto.CreateReqDto.builder().tbpostId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        return selectResDto;
    }

    @Override
    public List<TbpostDto.DetailResDto> list(TbpostDto.ListServDto param){
        return detailList(tbpostMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListServDto param){
        int[] returnSize = param.init(tbpostMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbpostMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListServDto param){
        param.init();
        return detailList(tbpostMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbpostDto.DetailResDto> detailList(List<TbpostDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbpostDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}

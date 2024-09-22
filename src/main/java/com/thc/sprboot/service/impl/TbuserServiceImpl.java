package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbemail;
import com.thc.sprboot.domain.Tbrefreshtoken;
import com.thc.sprboot.domain.Tbuser;
import com.thc.sprboot.domain.RoleType;
import com.thc.sprboot.domain.TbuserRoleType;
import com.thc.sprboot.dto.*;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.exception.NoMatchingDataException;
import com.thc.sprboot.mapper.TbuserMapper;
import com.thc.sprboot.repository.*;
import com.thc.sprboot.security.AuthService;
import com.thc.sprboot.security.ExternalProperties;
import com.thc.sprboot.service.TbuserService;
import com.thc.sprboot.util.SendEmail;
import com.thc.sprboot.util.NowDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbuserRepository tbuserRepository;
    private final TbuserMapper tbuserMapper;
    private final SendEmail sendEmail;
    private final TbemailRepository tbemailRepository;
    private final TbrefreshtokenRepository tbrefreshtokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleTypeRepository roleTypeRepository;
    private final TbuserRoleTypeRepository tbuserRoleTypeRepository;
    private final AuthService authService;
    private final ExternalProperties externalProperties;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            , TbuserMapper tbuserMapper
            , SendEmail sendEmail
            , TbemailRepository tbemailRepository
            , TbrefreshtokenRepository tbrefreshtokenRepository
            , BCryptPasswordEncoder bCryptPasswordEncoder
            , RoleTypeRepository roleTypeRepository
            , TbuserRoleTypeRepository tbuserRoleTypeRepository
            , AuthService authService
            , ExternalProperties externalProperties
    ) {
        this.tbuserRepository = tbuserRepository;
        this.tbuserMapper = tbuserMapper;
        this.sendEmail = sendEmail;
        this.tbemailRepository = tbemailRepository;
        this.tbrefreshtokenRepository = tbrefreshtokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleTypeRepository = roleTypeRepository;
        this.tbuserRoleTypeRepository = tbuserRoleTypeRepository;
        this.authService = authService;
        this.externalProperties = externalProperties;
    }

    public TbuserDto.SnsLoginResDto getToken(String id){
        TbuserDto.SnsLoginResDto resDto = TbuserDto.SnsLoginResDto.builder().build();
        String refreshToken = authService.createRefreshToken(id);
        String accessToken = authService.issueAccessToken(refreshToken);
        resDto.setRefreshToken(externalProperties.getTokenPrefix() + refreshToken);
        resDto.setAccessToken(externalProperties.getTokenPrefix() + accessToken);
        return resDto;
    }

    @Override
    public TbuserDto.SnsLoginResDto sns(TbuserDto.CreateServDto params){
        Tbuser tbuser = tbuserRepository.findByUsername(params.getUsername());
        if(tbuser == null){
            TbuserDto.CreateResDto createResDto = create(params);
            return getToken(createResDto.getId());
        }
        return getToken(tbuser.getId());
    }
    @Override
    public TbuserDto.CreateResDto logout(DefaultDto.DetailReqDto param){
        Tbrefreshtoken tbrefreshtoken = tbrefreshtokenRepository.findByTbuserId(param.getId());
        if(tbrefreshtoken == null){ throw new RuntimeException("no data"); }
        tbrefreshtokenRepository.delete(tbrefreshtoken);
        return TbuserDto.CreateResDto.builder().id("logout").build();
    }
    @Override
    public TbuserDto.CreateResDto confirm(TbuserDto.ConfirmReqDto param){
        Tbemail tbemail = tbemailRepository.findByUsernameAndNumber(param.getUsername(), param.getNumber());
        if(tbemail == null){
            return TbuserDto.CreateResDto.builder().id("not matched").build();
        } else {
            String now = new NowDate().getNow();
            String due = tbemail.getDue();

            //현재시간이랑, 늦은 시간이랑 정렬을 해본다.
            String[] arrayTemp = {now, due};
            Arrays.sort(arrayTemp);
            System.out.println(now + "//" + due + "//  => " + arrayTemp[0]);

            if(now.equals(arrayTemp[1])){
                //늦었을때!!!
                return TbuserDto.CreateResDto.builder().id("expired").build();
            }
            //이제 완료 처리 된 것 저장!!
            tbemail.setProcess("done");
            tbemailRepository.save(tbemail);
            //tbemailRepository.delete(tbemail);
            return TbuserDto.CreateResDto.builder().id("ok").build();
        }
    }
    //@Transactional
    @Override
    public TbuserDto.CreateResDto email(TbuserDto.UidReqDto param){
        Tbuser tbuser = tbuserRepository.findByUsername(param.getUsername());
        if(tbuser == null){
            //이거는 중복 아니어서 가입 가능!
            //인증번호 만들기
            String number = "";
            for(int i=0;i<6;i++){
                int random_0to9 = (int) (Math.random() * 10);
                number += random_0to9 + "";
            }
            //이메일 보내기!!
            String due = new NowDate().getDue(180);
            Tbemail tbemail = tbemailRepository.findByUsername(param.getUsername());
            if(tbemail == null){
                tbemailRepository.save(TbemailDto.CreateReqDto.builder().username(param.getUsername()).number(number).due(due).build().toEntity());
            } else {
                tbemail.setNumber(number);
                tbemail.setDue(due);
                if("done".equals(tbemail.getProcess())){
                    tbemail.setProcess("yet");
                }
                tbemailRepository.save(tbemail);
            }
            logger.info("number : " + number);

            try{
                Map<String, Object> content = new HashMap<>();
                content.put("num", number);
                sendEmail.send(param.getUsername(), "이메일 인증", content, "send");
            } catch(Exception e){

            }
            return TbuserDto.CreateResDto.builder().id("ok").build();
        } else {
            //이거는 중복 가입 뷸가능!
            return TbuserDto.CreateResDto.builder().id("already").build();
        }
    }
    @Override
    public TbuserDto.CreateResDto id(TbuserDto.UidReqDto param){
        //금지된 단어를 사용하는 경우 가입 불가!
        String[] ids = {"admin", "user", "fxxx"};
        for(String id : ids){
            if((param.getUsername()).contains(id)){
                return TbuserDto.CreateResDto.builder().id("not").build();
            }
        }

        Tbuser tbuser = tbuserRepository.findByUsername(param.getUsername());
        if(tbuser == null){
            //이거는 중복 아니어서 가입 가능!
            return TbuserDto.CreateResDto.builder().id("ok").build();
        } else {
            //이거는 중복 가입 뷸가능!
            return TbuserDto.CreateResDto.builder().id("already").build();
        }
    }

    @Override
    public TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param){
        Tbemail tbemail = tbemailRepository.findByUsername(param.getUsername());
        if(tbemail == null || !"done".equals(tbemail.getProcess())){
            return TbuserDto.CreateResDto.builder().id("not qualified").build();
        } else {
            tbemailRepository.delete(tbemail);
        }
        TbuserDto.CreateServDto newParam = TbuserDto.CreateServDto.builder().username(param.getUsername()).password(param.getPassword()).route("direct").build();
        return create(newParam);
    }

    /**/

    @Override
    public TbuserDto.CreateResDto create(TbuserDto.CreateServDto param){
        //비번 암호화를 위한 코드
        param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
        //닉네임 등록
        param.setNick((UUID.randomUUID().toString().replace("-", "")).substring(0, 10));

        //사용자 등록 완료!
        Tbuser tbuser = tbuserRepository.save(param.toEntity());

        //권한은 그냥 ROLE_USER 로 강제 저장함!(임시코드)
        RoleType roleType = roleTypeRepository.findByTypeName("ROLE_USER");
        if(roleType == null){
            roleType = new RoleType();
            roleType.setId("user");
            roleType.setTypeName("ROLE_USER");
            roleTypeRepository.save(roleType);
        }
        TbuserRoleType tbuserRoleType = TbuserRoleType.of(tbuser, roleType);
        tbuserRoleTypeRepository.save(tbuserRoleType);
        //
        return tbuser.toCreateResDto();
    }

    @Override
    public TbuserDto.CreateResDto update(TbuserDto.UpdateServDto param){
        Tbuser tbuser = tbuserRepository.findById(param.getId()).orElseThrow(()->new NoMatchingDataException("no data"));
        if(!param.isAdmin() && !tbuser.getId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }
        if(param.getDeleted() != null){
            tbuser.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbuser.setProcess(param.getProcess());
        }
        if(param.getPassword() != null){
            tbuser.setPassword(param.getPassword());
        }
        tbuserRepository.save(tbuser);
        return tbuser.toCreateResDto();
    }
    public TbuserDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbuserDto.UpdateServDto newParam = TbuserDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbuserDto.UpdateServDto newParam = TbuserDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbuserDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbuserDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbuserDto.DetailResDto detail(DefaultDto.DetailServDto param){
        if("my".equals(param.getId())){
            param.setId(param.getReqTbuserId());
        }
        return get(param);
    }

    public TbuserDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbuserDto.DetailResDto selectResDto = tbuserMapper.detail(param);
        if(selectResDto == null){ throw new NoMatchingDataException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbuserDto.DetailResDto> list(TbuserDto.ListServDto param){
        return detailList(tbuserMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbuserDto.PagedListServDto param){
        int[] returnSize = param.init(tbuserMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbuserMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListServDto param){
        param.init();
        return detailList(tbuserMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbuserDto.DetailResDto> detailList(List<TbuserDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbuserDto.DetailResDto> newList = new ArrayList<>();
        for(TbuserDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}

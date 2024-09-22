package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbuserDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbuserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "0-1. 사용자 API 안내",
        description = "사용자 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbuser")
@RestController
public class TbuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbuser";

    private final TbuserService tbuserService;
    private final TbgrantService tbgrantService;
    public TbuserRestController(TbuserService tbuserService, TbgrantService tbgrantService) {
        this.tbuserService = tbuserService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "사용자 로그아웃(사용자만 접근 가능)",
            description = "사용자 로그아웃 컨트롤러 <br />"
                    + "@param TbuserDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/logout")
    public ResponseEntity<TbuserDto.CreateResDto> logout(@AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        DefaultDto.DetailServDto newParam = DefaultDto.DetailServDto.builder().id(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.logout(newParam));
    }
    @Operation(summary = "사용자 이메일 인증확인",
            description = "사용자 이메일 인증확인 컨트롤러 <br />"
                    + "@param TbuserDto.UidReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/confirm")
    public ResponseEntity<TbuserDto.CreateResDto> confirm(@Valid @RequestBody TbuserDto.ConfirmReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.confirm(param));
    }

    @Operation(summary = "사용자 이메일 인증요청",
            description = "사용자 이메일 인증요청 컨트롤러 <br />"
                    + "@param TbuserDto.UidReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/email")
    public ResponseEntity<TbuserDto.CreateResDto> email(@Valid @RequestBody TbuserDto.UidReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.email(param));
    }
    /*@Operation(summary = "사용자 ID 중복체크",
            description = "사용자 ID 중복체크 컨트롤러 <br />"
                    + "@param TbuserDto.UidReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/id")
    public ResponseEntity<TbuserDto.CreateResDto> id(@Valid @RequestBody TbuserDto.UidReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.id(param));
    }*/
    @Operation(summary = "사용자 가입",
            description = "사용자 가입 컨트롤러 <br />"
                    + "@param TbuserDto.SignupReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<TbuserDto.CreateResDto> signup(@Valid @RequestBody TbuserDto.SignupReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.signup(param));
    }

    /**/

    @Operation(summary = "사용자 생성",
            description = "사용자 생성 컨트롤러 <br />"
                    + "@param TbuserDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbuserDto.CreateResDto> create(@Valid @RequestBody TbuserDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbuserDto.CreateServDto newParam = (TbuserDto.CreateServDto) TbuserDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbuserService.create(newParam));
    }

    @Operation(summary = "사용자 수정",
            description = "사용자 수정 컨트롤러 <br />"
                    + "@param TbuserDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbuserDto.CreateResDto> update(@Valid @RequestBody TbuserDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbuserDto.UpdateServDto newParam = (TbuserDto.UpdateServDto) TbuserDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.update(newParam));
    }

    @Operation(summary = "사용자 삭제",
            description = "사용자 삭제 컨트롤러 <br />"
                    + "@param TbuserDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbuserDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.delete(newParam));
    }
    @Operation(summary = "사용자 여러개 삭제",
            description = "사용자 여러개 삭제 컨트롤러 <br />"
                    + "@param TbuserDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbuserDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.deletes(newParam));
    }

    @Operation(summary = "사용자 상세 조회",
            description = "사용자 상세 조회 컨트롤러 <br />"
                    + "@param TbuserDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbuserDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.detail(newParam));
    }
    @Operation(summary = "사용자 목록 전체 조회",
            description = "사용자 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbuserDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbuserDto.DetailResDto>> list(@Valid TbuserDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbuserDto.ListServDto newParam = (TbuserDto.ListServDto) TbuserDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.list(newParam));
    }

    @Operation(summary = "사용자 목록 페이지 조회",
            description = "사용자 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbuserDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbuserDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbuserDto.PagedListServDto newParam = (TbuserDto.PagedListServDto) TbuserDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.pagedList(newParam));
    }
    @Operation(summary = "사용자 목록 스크롤 조회",
            description = "사용자 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbuserDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbuserDto.DetailResDto>> mlist(@Valid TbuserDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbuserDto.ScrollListServDto newParam = (TbuserDto.ScrollListServDto) TbuserDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbuserService.scrollList(newParam));
    }

}

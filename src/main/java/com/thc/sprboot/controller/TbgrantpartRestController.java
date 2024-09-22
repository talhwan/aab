package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbgrantpartDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbgrantpartService;
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


@Tag(name = "0-0_1. 접속권한 상세 API 안내",
        description = "접속권한 상세 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgrantpart")
@RestController
public class TbgrantpartRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbgrant";

    private final TbgrantpartService tbgrantpartService;
    private final TbgrantService tbgrantService;
    public TbgrantpartRestController(
            TbgrantpartService tbgrantpartService
            , TbgrantService tbgrantService) {
        this.tbgrantpartService = tbgrantpartService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "접속권한 상세 토글",
            description = "접속권한 상세 토글 컨트롤러 <br />"
                    + "@param TbgrantpartDto.ToggleReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantpartDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("/toggle")
    public ResponseEntity<TbgrantpartDto.CreateResDto> toggle(@Valid @RequestBody TbgrantpartDto.ToggleReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbgrantpartDto.ToggleServDto newParam = (TbgrantpartDto.ToggleServDto) TbgrantpartDto.ToggleServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantpartService.toggle(newParam));
    }

    /**/

    @Operation(summary = "접속권한 상세 생성",
            description = "접속권한 상세 생성 컨트롤러 <br />"
                    + "@param TbgrantpartDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantpartDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbgrantpartDto.CreateResDto> create(@Valid @RequestBody TbgrantpartDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbgrantpartDto.CreateServDto newParam = (TbgrantpartDto.CreateServDto) TbgrantpartDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantpartService.create(newParam));
    }

    @Operation(summary = "접속권한 상세 수정",
            description = "접속권한 상세 수정 컨트롤러 <br />"
                    + "@param TbgrantpartDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbgrantpartDto.CreateResDto> update(@Valid @RequestBody TbgrantpartDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbgrantpartDto.UpdateServDto newParam = (TbgrantpartDto.UpdateServDto) TbgrantpartDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.update(newParam));
    }

    @Operation(summary = "접속권한 상세 삭제",
            description = "접속권한 상세 삭제 컨트롤러 <br />"
                    + "@param TbgrantpartDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbgrantpartDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.delete(newParam));
    }
    @Operation(summary = "접속권한 상세 여러개 삭제",
            description = "접속권한 상세 여러개 삭제 컨트롤러 <br />"
                    + "@param TbgrantpartDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbgrantpartDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.deletes(newParam));
    }

    @Operation(summary = "접속권한 상세 상세 조회",
            description = "접속권한 상세 상세 조회 컨트롤러 <br />"
                    + "@param TbgrantpartDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<TbgrantpartDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.detail(newParam));
    }
    @Operation(summary = "접속권한 상세 목록 전체 조회",
            description = "접속권한 상세 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbgrantpartDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbgrantpartDto.DetailResDto>> list(@Valid TbgrantpartDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantpartDto.ListServDto newParam = (TbgrantpartDto.ListServDto) TbgrantpartDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.list(newParam));
    }

    @Operation(summary = "접속권한 상세 목록 페이지 조회",
            description = "접속권한 상세 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbgrantpartDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbgrantpartDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantpartDto.PagedListServDto newParam = (TbgrantpartDto.PagedListServDto) TbgrantpartDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.pagedList(newParam));
    }
    @Operation(summary = "접속권한 상세 목록 스크롤 조회",
            description = "접속권한 상세 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbgrantpartDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantpartDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mlist")
    public ResponseEntity<List<TbgrantpartDto.DetailResDto>> mlist(@Valid TbgrantpartDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantpartDto.ScrollListServDto newParam = (TbgrantpartDto.ScrollListServDto) TbgrantpartDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantpartService.scrollList(newParam));
    }

}

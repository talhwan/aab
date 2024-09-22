package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbgrantuserDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbgrantuserService;
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


@Tag(name = "0-0_2. 접속권한 사용자 API 안내",
        description = "접속권한 사용자 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgrantuser")
@RestController
public class TbgrantuserRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbgrant";

    private final TbgrantuserService tbgrantuserService;
    private final TbgrantService tbgrantService;
    public TbgrantuserRestController(TbgrantuserService tbgrantuserService, TbgrantService tbgrantService) {
        this.tbgrantuserService = tbgrantuserService;
        this.tbgrantService = tbgrantService;
    }

    /**/

    @Operation(summary = "접속권한 사용자 생성",
            description = "접속권한 사용자 생성 컨트롤러 <br />"
                    + "@param TbgrantuserDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbgrantuserDto.CreateResDto> create(@Valid @RequestBody TbgrantuserDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbgrantuserDto.CreateServDto newParam = (TbgrantuserDto.CreateServDto) TbgrantuserDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantuserService.create(newParam));
    }

    @Operation(summary = "접속권한 사용자 수정",
            description = "접속권한 사용자 수정 컨트롤러 <br />"
                    + "@param TbgrantuserDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbgrantuserDto.CreateResDto> update(@Valid @RequestBody TbgrantuserDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbgrantuserDto.UpdateServDto newParam = (TbgrantuserDto.UpdateServDto) TbgrantuserDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(true).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.update(newParam));
    }

    @Operation(summary = "접속권한 사용자 삭제",
            description = "접속권한 사용자 삭제 컨트롤러 <br />"
                    + "@param TbgrantuserDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbgrantuserDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(true).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.delete(newParam));
    }
    @Operation(summary = "접속권한 사용자 여러개 삭제",
            description = "접속권한 사용자 여러개 삭제 컨트롤러 <br />"
                    + "@param TbgrantuserDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbgrantuserDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(true).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.deletes(newParam));
    }

    @Operation(summary = "접속권한 사용자 상세 조회",
            description = "접속권한 사용자 상세 조회 컨트롤러 <br />"
                    + "@param TbgrantuserDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<TbgrantuserDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(true).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.detail(newParam));
    }
    @Operation(summary = "접속권한 사용자 목록 전체 조회",
            description = "접속권한 사용자 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbgrantuserDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbgrantuserDto.DetailResDto>> list(@Valid TbgrantuserDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantuserDto.ListServDto newParam = (TbgrantuserDto.ListServDto) TbgrantuserDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(true).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.list(newParam));
    }

    @Operation(summary = "접속권한 사용자 목록 페이지 조회",
            description = "접속권한 사용자 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbgrantuserDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbgrantuserDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantuserDto.PagedListServDto newParam = (TbgrantuserDto.PagedListServDto) TbgrantuserDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(true).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.pagedList(newParam));
    }
    @Operation(summary = "접속권한 사용자 목록 스크롤 조회",
            description = "접속권한 사용자 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbgrantuserDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantuserDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mlist")
    public ResponseEntity<List<TbgrantuserDto.DetailResDto>> mlist(@Valid TbgrantuserDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbgrantuserDto.ScrollListServDto newParam = (TbgrantuserDto.ScrollListServDto) TbgrantuserDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(true).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantuserService.scrollList(newParam));
    }

}

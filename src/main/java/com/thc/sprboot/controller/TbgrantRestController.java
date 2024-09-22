package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
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


@Tag(name = "0-0. 접속권한 API 안내",
        description = "접속권한 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbgrant")
@RestController
public class TbgrantRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbgrant";

    private final TbgrantService tbgrantService;
    public TbgrantRestController(TbgrantService tbgrantService) {
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "접속권한 생성",
            description = "접속권한 생성 컨트롤러 <br />"
                    + "@param TbgrantDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbgrantDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbgrantDto.CreateResDto> create(@Valid @RequestBody TbgrantDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbgrantDto.CreateServDto newParam = (TbgrantDto.CreateServDto) TbgrantDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbgrantService.create(newParam));
    }

    @Operation(summary = "접속권한 수정",
            description = "접속권한 수정 컨트롤러 <br />"
                    + "@param TbgrantDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbgrantDto.CreateResDto> update(@Valid @RequestBody TbgrantDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbgrantDto.UpdateServDto newParam = (TbgrantDto.UpdateServDto) TbgrantDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.update(newParam));
    }

    @Operation(summary = "접속권한 삭제",
            description = "접속권한 삭제 컨트롤러 <br />"
                    + "@param TbgrantDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbgrantDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.delete(newParam));
    }
    @Operation(summary = "접속권한 여러개 삭제",
            description = "접속권한 여러개 삭제 컨트롤러 <br />"
                    + "@param TbgrantDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbgrantDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.deletes(newParam));
    }

    @Operation(summary = "접속권한 상세 조회",
            description = "접속권한 상세 조회 컨트롤러 <br />"
                    + "@param TbgrantDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<TbgrantDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.detail(newParam));
    }
    @Operation(summary = "접속권한 목록 전체 조회",
            description = "접속권한 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbgrantDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbgrantDto.DetailResDto>> list(@Valid TbgrantDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        TbgrantDto.ListServDto newParam = (TbgrantDto.ListServDto) TbgrantDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.list(newParam));
    }

    @Operation(summary = "접속권한 목록 페이지 조회",
            description = "접속권한 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbgrantDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbgrantDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        TbgrantDto.PagedListServDto newParam = (TbgrantDto.PagedListServDto) TbgrantDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.pagedList(newParam));
    }
    @Operation(summary = "접속권한 목록 스크롤 조회",
            description = "접속권한 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbgrantDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbgrantDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mlist")
    public ResponseEntity<List<TbgrantDto.DetailResDto>> mlist(@Valid TbgrantDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        TbgrantDto.ScrollListServDto newParam = (TbgrantDto.ScrollListServDto) TbgrantDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbgrantService.scrollList(newParam));
    }

}

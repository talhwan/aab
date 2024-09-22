package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbpostDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbpostService;
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


@Tag(name = "1-1. 게시글 API 안내",
        description = "게시글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpost")
@RestController
public class TbpostRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbpost";

    private final TbpostService tbpostService;
    private final TbgrantService tbgrantService;
    public TbpostRestController(TbpostService tbpostService, TbgrantService tbgrantService) {
        this.tbpostService = tbpostService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "게시글 생성",
            description = "게시글 생성 컨트롤러 <br />"
                    + "@param TbpostDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbpostDto.CreateResDto> create(@Valid @RequestBody TbpostDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbpostDto.CreateServDto newParam = (TbpostDto.CreateServDto) TbpostDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostService.create(newParam));
    }

    @Operation(summary = "게시글 수정",
            description = "게시글 수정 컨트롤러 <br />"
                    + "@param TbpostDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbpostDto.CreateResDto> update(@Valid @RequestBody TbpostDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbpostDto.UpdateServDto newParam = (TbpostDto.UpdateServDto) TbpostDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.update(newParam));
    }

    @Operation(summary = "게시글 삭제",
            description = "게시글 삭제 컨트롤러 <br />"
                    + "@param TbpostDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbpostDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.delete(newParam));
    }
    @Operation(summary = "게시글 여러개 삭제",
            description = "게시글 여러개 삭제 컨트롤러 <br />"
                    + "@param TbpostDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbpostDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.deletes(newParam));
    }

    @Operation(summary = "게시글 상세 조회",
            description = "게시글 상세 조회 컨트롤러 <br />"
                    + "@param TbpostDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbpostDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.detail(newParam));
    }
    @Operation(summary = "게시글 목록 전체 조회",
            description = "게시글 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbpostDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbpostDto.DetailResDto>> list(@Valid TbpostDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbpostDto.ListServDto newParam = (TbpostDto.ListServDto) TbpostDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.list(newParam));
    }

    @Operation(summary = "게시글 목록 페이지 조회",
            description = "게시글 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbpostDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbpostDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbpostDto.PagedListServDto newParam = (TbpostDto.PagedListServDto) TbpostDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.pagedList(newParam));
    }
    @Operation(summary = "게시글 목록 스크롤 조회",
            description = "게시글 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbpostDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbpostDto.DetailResDto>> mlist(@Valid TbpostDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbpostDto.ScrollListServDto newParam = (TbpostDto.ScrollListServDto) TbpostDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.scrollList(newParam));
    }

}

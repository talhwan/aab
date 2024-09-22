package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbfaqDto;
import com.thc.sprboot.security.PrincipalDetails;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbfaqService;
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


@Tag(name = "1-2. FAQ API 안내",
        description = "FAQ 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfaq")
@RestController
public class TbfaqRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbfaq";

    private final TbfaqService tbfaqService;
    private final TbgrantService tbgrantService;
    public TbfaqRestController(TbfaqService tbfaqService, TbgrantService tbgrantService) {
        this.tbfaqService = tbfaqService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "FAQ 순서변경",
            description = "FAQ 순서변경 컨트롤러 (사용자만 접근 가능) <br />"
                    + "@param TbfaqDto.SequenceReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfaqDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/sequence")
    public ResponseEntity<TbfaqDto.CreateResDto> sequence(@RequestBody TbfaqDto.SequenceReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbfaqDto.SequenceServDto newParam = (TbfaqDto.SequenceServDto) TbfaqDto.SequenceServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfaqService.sequence(newParam));
    }

    /**/

    @Operation(summary = "FAQ 생성",
            description = "FAQ 생성 컨트롤러 <br />"
                    + "@param TbfaqDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfaqDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<TbfaqDto.CreateResDto> create(@Valid @RequestBody TbfaqDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbfaqDto.CreateServDto newParam = (TbfaqDto.CreateServDto) TbfaqDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbfaqService.create(newParam));
    }

    @Operation(summary = "FAQ 수정",
            description = "FAQ 수정 컨트롤러 <br />"
                    + "@param TbfaqDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbfaqDto.CreateResDto> update(@Valid @RequestBody TbfaqDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        TbfaqDto.UpdateServDto newParam = (TbfaqDto.UpdateServDto) TbfaqDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.update(newParam));
    }

    @Operation(summary = "FAQ 삭제",
            description = "FAQ 삭제 컨트롤러 <br />"
                    + "@param TbfaqDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbfaqDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "update", principalDetails.getTbuser().getId()));
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.delete(newParam));
    }
    @Operation(summary = "FAQ 여러개 삭제",
            description = "FAQ 여러개 삭제 컨트롤러 <br />"
                    + "@param TbfaqDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbfaqDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.deletes(newParam));
    }

    @Operation(summary = "FAQ 상세 조회",
            description = "FAQ 상세 조회 컨트롤러 <br />"
                    + "@param TbfaqDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbfaqDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.detail(newParam));
    }
    @Operation(summary = "FAQ 목록 전체 조회",
            description = "FAQ 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbfaqDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<List<TbfaqDto.DetailResDto>> list(@Valid TbfaqDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbfaqDto.ListServDto newParam = (TbfaqDto.ListServDto) TbfaqDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.list(newParam));
    }

    @Operation(summary = "FAQ 목록 페이지 조회",
            description = "FAQ 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbfaqDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbfaqDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbfaqDto.PagedListServDto newParam = (TbfaqDto.PagedListServDto) TbfaqDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.pagedList(newParam));
    }
    @Operation(summary = "FAQ 목록 스크롤 조회",
            description = "FAQ 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbfaqDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbfaqDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbfaqDto.DetailResDto>> mlist(@Valid TbfaqDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "read", principalDetails.getTbuser().getId()));
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbfaqDto.ScrollListServDto newParam = (TbfaqDto.ScrollListServDto) TbfaqDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(isAdmin).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbfaqService.scrollList(newParam));
    }

}

package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostcmtDto;
import com.thc.sprboot.service.TbpostcmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_2. 게시글 댓글 API 안내",
        description = "게시글 댓글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpostcmt")
@RestController
public class TbpostcmtRestController {

    private final TbpostcmtService tbpostcmtService;
    public TbpostcmtRestController(TbpostcmtService tbpostcmtService) {
        this.tbpostcmtService = tbpostcmtService;
    }


    /**/

    @Operation(summary = "게시글 댓글 생성",
            description = "게시글 댓글 생성 컨트롤러 <br />"
                    + "@param TbpostcmtDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbpostcmtDto.CreateResDto> create(@Valid @RequestBody TbpostcmtDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostcmtService.create(param));
    }

    @Operation(summary = "게시글 댓글 수정",
            description = "게시글 댓글 수정 컨트롤러 <br />"
                    + "@param TbpostcmtDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbpostcmtDto.CreateResDto> update(@Valid @RequestBody TbpostcmtDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.update(param));
    }
    
    @Operation(summary = "게시글 댓글 삭제",
            description = "게시글 댓글 삭제 컨트롤러 <br />"
                    + "@param TbpostcmtDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbpostcmtDto.CreateResDto> delete(@Valid @RequestBody TbpostcmtDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.delete(param));
    }

    @Operation(summary = "게시글 댓글 상세 조회",
            description = "게시글 댓글 상세 조회 컨트롤러 <br />"
                    + "@param TbpostcmtDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbpostcmtDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.detail(param));
    }
    @Operation(summary = "게시글 댓글 목록 전체 조회",
            description = "게시글 댓글 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbpostcmtDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbpostcmtDto.DetailResDto>> list(@Valid TbpostcmtDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.list(param));
    }

    @Operation(summary = "게시글 댓글 목록 페이지 조회",
            description = "게시글 댓글 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbpostcmtDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbpostcmtDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.pagedList(param));
    }
    @Operation(summary = "게시글 댓글 목록 스크롤 조회",
            description = "게시글 댓글 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbpostcmtDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbpostcmtDto.DetailResDto>> mlist(@Valid TbpostcmtDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostcmtService.scrollList(param));
    }

}

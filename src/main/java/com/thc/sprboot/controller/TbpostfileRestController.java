package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbpostfileDto;
import com.thc.sprboot.service.TbpostfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_1. 게시글 첨부파일 API 안내",
        description = "게시글 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpostfile")
@RestController
public class TbpostfileRestController {

    private final TbpostfileService tbpostfileService;
    public TbpostfileRestController(TbpostfileService tbpostfileService) {
        this.tbpostfileService = tbpostfileService;
    }


    /**/

    @Operation(summary = "게시글 첨부파일 생성",
            description = "게시글 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbpostfileDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbpostfileDto.CreateResDto> create(@Valid @RequestBody TbpostfileDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostfileService.create(param));
    }

    @Operation(summary = "게시글 첨부파일 수정",
            description = "게시글 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbpostfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbpostfileDto.CreateResDto> update(@Valid @RequestBody TbpostfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.update(param));
    }
    
    @Operation(summary = "게시글 첨부파일 삭제",
            description = "게시글 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbpostfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbpostfileDto.CreateResDto> delete(@Valid @RequestBody TbpostfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.delete(param));
    }

    @Operation(summary = "게시글 첨부파일 상세 조회",
            description = "게시글 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbpostfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbpostfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.detail(param));
    }
    @Operation(summary = "게시글 첨부파일 목록 전체 조회",
            description = "게시글 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbpostfileDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbpostfileDto.DetailResDto>> list(@Valid TbpostfileDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.list(param));
    }

    @Operation(summary = "게시글 첨부파일 목록 페이지 조회",
            description = "게시글 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbpostfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbpostfileDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.pagedList(param));
    }
    @Operation(summary = "게시글 첨부파일 목록 스크롤 조회",
            description = "게시글 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbpostfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbpostfileDto.DetailResDto>> mlist(@Valid TbpostfileDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostfileService.scrollList(param));
    }

}

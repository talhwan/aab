package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbgrantDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// 어드민의 접속 권한과 관련된 클래스
@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbgrant extends AuditingFields{
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = false, length=10000) @Lob private String content; // 본문

    protected Tbgrant(){}
    private Tbgrant(String title, String cate, String content) {
        this.title = title;
        this.cate = cate;
        this.content = content;
    }
    public static Tbgrant of(String title, String cate, String content){
        return new Tbgrant(title, cate, content);
    }

    public TbgrantDto.CreateResDto toCreateResDto() {
        return TbgrantDto.CreateResDto.builder().id(getId()).build();
    }
}

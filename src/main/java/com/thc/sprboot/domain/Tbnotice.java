package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbnoticeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbnotice extends AuditingFields {

    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문
    @Setter @Column(nullable = false) private String img;

    protected Tbnotice(){}
    private Tbnotice(String cate, String title, String content, String img) {
        this.cate = cate;
        this.title = title;
        this.content = content;
        this.img = img;
    }
    public static Tbnotice of(String cate, String title, String content, String img) {
        return new Tbnotice(cate, title, content, img);
    }

    public TbnoticeDto.CreateResDto toCreateResDto() {
        return TbnoticeDto.CreateResDto.builder().id(this.getId()).build();
    }
}

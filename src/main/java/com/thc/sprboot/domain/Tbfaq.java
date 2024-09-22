package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbfaqDto;
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
public class Tbfaq extends AuditingFields {

    @Setter @Column(nullable = false) private int sequence; // 순서
    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문
    @Setter @Column(nullable = false) private String img;

    protected Tbfaq(){}
    private Tbfaq(int sequence, String cate, String title, String content, String img) {
        this.sequence = sequence;
        this.cate = cate;
        this.title = title;
        this.content = content;
        this.img = img;
    }
    public static Tbfaq of(int sequence, String cate, String title, String content, String img) {
        return new Tbfaq(sequence, cate, title, content, img);
    }

    public TbfaqDto.CreateResDto toCreateResDto() {
        return TbfaqDto.CreateResDto.builder().id(this.getId()).build();
    }
}

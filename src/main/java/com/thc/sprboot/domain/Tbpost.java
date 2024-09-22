package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbpostDto;
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
public class Tbpost extends AuditingFields {

    @Setter @Column(nullable = false) private String tbuserId; //(fk이지만, 따로 설정은 안함.)
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문
    @Setter @Column(nullable = false) private Integer countread; // 조회수

    protected Tbpost(){}
    private Tbpost(String tbuserId, String title, String content, Integer countread) {
        this.tbuserId = tbuserId;
        this.title = title;
        this.content = content;
        this.countread = countread;
    }
    public static Tbpost of(String tbuserId, String title, String content) {
        return new Tbpost(tbuserId, title, content, 0);
    }

    public TbpostDto.CreateResDto toCreateResDto() {
        return TbpostDto.CreateResDto.builder().id(this.getId()).build();
    }
}

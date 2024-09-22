package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbpostcmtDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
public class Tbpostcmt extends AuditingFields {

    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false, length=400) private String content;

    protected Tbpostcmt(){}
    private Tbpostcmt(String tbpostId, String tbuserId, String content) {
        this.tbpostId = tbpostId;
        this.tbuserId = tbuserId;
        this.content = content;
    }
    public static Tbpostcmt of(String tbpostId, String tbuserId, String content) {
        return new Tbpostcmt(tbpostId, tbuserId, content);
    }

    public TbpostcmtDto.CreateResDto toCreateResDto() {
        return TbpostcmtDto.CreateResDto.builder().id(this.getId()).build();
    }
}

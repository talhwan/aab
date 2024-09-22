package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbpostfileDto;
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
public class Tbpostfile extends AuditingFields {

    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbpostfile(){}
    private Tbpostfile(String tbpostId, String type, String url) {
        this.tbpostId = tbpostId;
        this.type = type;
        this.url = url;
    }
    public static Tbpostfile of(String tbpostId, String type, String url) {
        return new Tbpostfile(tbpostId, type, url);
    }

    public TbpostfileDto.CreateResDto toCreateResDto() {
        return TbpostfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}

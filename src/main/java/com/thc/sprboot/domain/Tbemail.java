package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbemailDto;
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
public class Tbemail extends AuditingFields {

    @Setter @Column(nullable = false, unique = true) private String username;
    @Setter @Column(nullable = false) private String number;
    @Setter @Column(nullable = false) private String due;

    protected Tbemail(){}
    private Tbemail(String username, String number, String due) {
        this.username = username;
        this.number = number;
        this.due = due;
    }
    public static Tbemail of(String username, String number, String due) {
        return new Tbemail(username, number, due);
    }

    public TbemailDto.CreateResDto toCreateResDto() {
        return TbemailDto.CreateResDto.builder().id(this.getId()).build();
    }
}

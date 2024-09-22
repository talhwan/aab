package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbemail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class TbemailDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "number", example="")
        @NotNull
        @NotEmpty
        private String number;
        //이거는 실제로 고객이 보내는 정보가 아닙니다!
        @Schema(description = "due", example="")
        private String due;

        public Tbemail toEntity(){
            return Tbemail.of(username, number, due);
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }

}

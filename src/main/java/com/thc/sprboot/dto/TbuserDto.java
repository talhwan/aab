package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbuser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbuserDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SnsLoginResDto{
        private String refreshToken;
        private String accessToken;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccessReqDto{
        @Schema(description = "refreshToken", example="")
        @NotNull
        @NotEmpty
        private String refreshToken;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConfirmReqDto {
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "number", example="")
        @NotNull
        @NotEmpty
        private String number;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UidReqDto {
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginReqDto {
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupReqDto {
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        @Schema(description = "username", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "password", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String password;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateServDto extends CreateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;

        private String nick;
        private String route;

        public Tbuser toEntity(){
            return Tbuser.of(getUsername(), getPassword(), null, getNick(), null, null, null, null, getRoute());
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
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "password", example="")
        @Size(max=400)
        private String password;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateServDto extends UpdateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "username", example="")
        private String username;
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
        @Schema(description = "gender", example="")
        private String gender;
        @Schema(description = "content", example="")
        private String content;
        @Schema(description = "img", example="")
        private String img;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListServDto extends ListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListServDto extends DefaultDto.PagedListServDto{
        private String reqTbuserId;
        private boolean isAdmin;

        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "name", example="")
        private String name;
        @Schema(description = "nick", example="")
        private String nick;
        @Schema(description = "phone", example="")
        private String phone;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListServDto extends ScrollListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

}

package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbgrantpart;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbgrantpartDto {

    public static String[][] cates = {
            {"tbgrant","접근권한"}
            ,{"tbuser", "사용자"}

            ,{"tbgalone", "Home Alone"}

            /*,{"tbpost", "게시판"}
            ,{"tbmatch", "경기정보"}*/
    };

    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ToggleReqDto extends CreateReqDto {
        @Schema(description = "way", example="")
        @NotNull
        @NotEmpty
        private boolean way;
    }
    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ToggleServDto extends ToggleReqDto {
        private String reqTbuserId;
        private boolean isAdmin;
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        @Schema(description = "tbgrantId", example="")
        @NotNull
        @NotEmpty
        private String tbgrantId;
        @Schema(description = "cate", example="")
        @NotNull
        @NotEmpty
        private String cate;
        @Schema(description = "content", example="")
        @NotNull
        @NotEmpty
        private String content;

        public Tbgrantpart toEntity(){
            return Tbgrantpart.of(getTbgrantId(), getCate(), getContent());
        }
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
        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
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
        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
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
        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
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

        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbgrantId", example="")
        private String tbgrantId;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;
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

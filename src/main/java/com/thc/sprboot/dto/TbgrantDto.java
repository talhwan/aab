package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbgrant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

public class TbgrantDto {

    @Schema
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccessServDto {
        private String cate;
        private String content;
        private String tbuserId;
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        @Schema(description = "title", example="")
        @NotNull
        @NotEmpty
        private String title;
        @Schema(description = "cate", example="")
        @NotNull
        @NotEmpty
        private String cate;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
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

        public Tbgrant toEntity(){
            return Tbgrant.of(getTitle(), getCate(), getContent());
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
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        @Size(max=4000)
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
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "content", example="")
        private String content;


        private String[][] cates = TbgrantpartDto.cates;

        @Schema(description = "tbgrantparts", example="")
        private List<TbgrantpartDto.DetailResDto> tbgrantparts = new ArrayList<>();
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
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
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
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

        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "cate", example="")
        private String cate;
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

package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbfaq;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbfaqDto {

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SequenceReqDto extends DefaultDto.BaseDto {
        @Schema(description = "id", example="length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max=32)
        private String id;

        @Schema(description = "way", example="way")
        private String way;
    }
    @SuperBuilder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SequenceServDto extends SequenceReqDto {
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
        @Schema(description = "cate", example="카테고리")
        @NotNull
        @NotEmpty
        private String cate;
        @Schema(description = "title", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
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
    public static class CreateServDto extends CreateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;

        private Integer sequence;

        public Tbfaq toEntity(){
            return Tbfaq.of(getSequence(), getCate(), getTitle(), getContent(), getImg());
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
        @Schema(description = "sequence", example="")
        private Integer sequence;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
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
    public static class UpdateServDto extends UpdateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "sequence", example="")
        private Integer sequence;
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
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
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
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
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
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

        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
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

package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbpostfile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbpostfileDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        private String tbpostId;

        @Schema(description = "type", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String type;
        @Schema(description = "url", example="")
        @Size(max=400)
        private String url;

        public Tbpostfile toEntity(){
            return Tbpostfile.of(tbpostId, type, url);
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
        @Schema(description = "tbpostId", example="")
        private String tbpostId;

        @Schema(description = "type", example="")
        @Size(max=100)
        private String type;
        @Schema(description = "url", example="")
        @Size(max=400)
        private String url;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;

        @Schema(description = "type", example="")
        private String type;
        @Schema(description = "url", example="")
        private String url;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "type", example="")
        private String type;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "type", example="")
        private String type;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "type", example="")
        private String type;
    }

}

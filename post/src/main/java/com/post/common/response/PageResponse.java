package com.post.common.response;

import com.post.post.dto.PostDto;
import lombok.Data;
import java.util.List;

@Data
public class PageResponse {

    private List<PostDto> list; // PostDto 목록으로 고정
    private int totalCount;
    private int page;
    private int size;
    private int totalPages;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    public PageResponse(List<PostDto> list, int totalCount, PageRequest pageRequest) {
        this.list = list;
        this.totalCount = totalCount;
        this.page = pageRequest.getPage();
        this.size = pageRequest.getSize();

        this.totalPages = (int) Math.ceil((double) totalCount / size);
        if (this.totalPages == 0) {
            this.totalPages = 1; // 데이터가 없을 때 최소 1페이지로 설정
        }

        // 페이지네이션 시작/끝 번호 계산 로직
        this.startPage = ((page - 1) / 5) * 5 + 1;
        this.endPage = Math.min(startPage + 4, totalPages);
        this.prev = startPage > 1;
        this.next = endPage < totalPages;
    }
}
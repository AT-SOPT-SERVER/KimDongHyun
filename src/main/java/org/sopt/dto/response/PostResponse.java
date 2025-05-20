package org.sopt.dto.response;

import org.sopt.domain.Post;

public class PostResponse {
    private final Long postId;
    private final String title;
    private final String content;

    public PostResponse(Post post){
        this.postId = post.user().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}


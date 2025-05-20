package org.sopt.entity;

import jakarta.persistence.*;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.springframework.core.io.ClassPathResource;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 300)
    private String content;

    protected Comment(){}

    public Comment(Post post, User user, String content){
        validateContent(content);
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public void validateContent(String content){
        if(content.isBlank()){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요");
        }
        if(content.length()>300) {
            throw new IllegalArgumentException("댓글은 300자를 넘을 수 없습니다.");
        }
    }
    public void updateContent(String newContent){
        validateContent(newContent);
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}

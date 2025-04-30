package org.sopt.domain;

import jakarta.persistence.*;

import javax.swing.text.StringContent;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    public Post() {}

    public Post(String title) {
        validateTitle(title); // 제목 검증
        this.title = title;
    }

    public Post(String title, String content) {
        validateTitle(title); // 제목 검증
        validateContent(content);
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // 제목 검증 메서드
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다! 제목을 입력헤주세요^^");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자를 넘지 않게 해주세요. 제목이 너무 긴 경우에는 게시글 작성이 되지 않습니다.");
        }
    }

    // 내용 검증 메서드
    private void validateContent(String content){
        if(content.trim().isEmpty()){
            throw new IllegalArgumentException("내용은 필수입니다. 내용이 비어 있는 경우에는 게시글 작성이 되지 않습니다.");
        }
        if(content.length() > 1000){
            throw new IllegalArgumentException("내용은 1000자를 넘을 수 없어요!");
        }
    }

    // 게시글 제목 수정 메서드
    public void updateTitle(String updatedTitle) {
        validateTitle(updatedTitle); // 제목 검증
        this.title = updatedTitle;
    }

    // 게시글 내용 수정 메서드
    public void updateContent(String updatedContent){
        validateContent(updatedContent);
        this.content = updatedContent;
    }
}

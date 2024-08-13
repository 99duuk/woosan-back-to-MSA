package com.luckyvicky.woosan.domain.board.entity;

import com.luckyvicky.woosan.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "board",
        indexes = {
                @Index(name = "idx_category_name", columnList = "category_name"),
                @Index(name = "idx_is_deleted", columnList = "is_deleted")
        })
@Document(indexName = "board")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writerId", nullable = false)
    private Member writer;

    @Transient
    @Field(type = FieldType.Text, name = "nickname")
    private String nickname;

    @Column(nullable = false, length = 40)
    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Transient
    @Field(type = FieldType.Text, name = "korean_title")
    private String koreanTitle;

    @Transient
    @Field(type = FieldType.Text, name = "synonym_title")
    private String synonymTitle;


    @Column(nullable = false, length = 1960)
    @Field(type = FieldType.Text, name = "content")
    private String content;

    @Transient
    @Field(type = FieldType.Text, name = "korean_content")
    private String koreanContent;

    @Transient
    @Field(type = FieldType.Text, name = "synomym_content")
    private String synonymContent;

    @UpdateTimestamp
    @Column(nullable = false)
    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "update_time")
    private LocalDateTime updateTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Field(type = FieldType.Date, format = DateFormat.date_time, name = "reg_date")
    private LocalDateTime regDate;

    @ColumnDefault("0")
    @Field(type = FieldType.Integer, name = "views")
    private int views;

    @ColumnDefault("0")
    @Min(0)
    @Field(type = FieldType.Integer, name = "likes_count")
    private int likesCount; // 추천 수

    @ColumnDefault("false")
    @Field(type = FieldType.Boolean, name = "is_deleted")
    private boolean isDeleted;

    @Column(nullable = false, length = 255)
    @Field(type = FieldType.Text, name = "category_name")
    private String categoryName;

    @ColumnDefault("0")
    @Field(type = FieldType.Integer, name = "reply_count")
    private int replyCount; // 댓글 수




    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

//    public void addViewCount() {
//        this.views++;
//    }



//
//    // 추천수 변경
//    public void changeLikesCount(int likesCount){
//        if(this.likesCount + likesCount < 0) {
//            throw new LikeException(ErrorCode.LIKES_COUNT_NEGATIVE);
//        }
//        this.likesCount += likesCount;
//    }
//
//    public void changeReplyCount(int replyCount) {
//        this.replyCount += replyCount;
//    }


}

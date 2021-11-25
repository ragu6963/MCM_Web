package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeneziaRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int veneziaId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "score")
    private int score;
}

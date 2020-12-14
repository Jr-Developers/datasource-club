package io.github.jr.developers.club.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "MEMBER_NAME")
    private String memberName;
}

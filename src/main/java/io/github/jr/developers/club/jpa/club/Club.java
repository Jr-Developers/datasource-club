package io.github.jr.developers.club.jpa.club;

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
@Table(name = "CLUB")
@NoArgsConstructor
public class Club {
    @Id
    @Column(name = "CLUB_NAME")
    private String clubName;
}

package com.jesusfc.demo.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "video_clubs")
@Entity
public class VideoClubEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 3)
    private String code;

    @Column(name = "name", nullable = false)
    @Size(max = 50)
    private String name;

    @Column(name = "description")
    @Size(max = 150)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_video_clubs",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntities;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoClubEntity that = (VideoClubEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public VideoClubEntity clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (VideoClubEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

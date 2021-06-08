package com.technologies.xelo.model.entities;

import com.technologies.xelo.enums.Gender;
import com.technologies.xelo.enums.IdentificationType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@ToString
@Entity
@Table(schema = "data_capture", name = "identification", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class IdentificationEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(nullable = false, name = "birthdate", length = 50)
    private String birthdate;

    @Basic
    @Column(name = "identification", columnDefinition="TEXT", unique = true)
    private String identification;

    @Basic
    @Column(name = "passport", columnDefinition="TEXT")
    private String passport;


    @Basic
    @Column(nullable = false, name = "citizenship", columnDefinition="TEXT")
    private String citizenship;

    @Column(nullable = false, name = "type", length = 50)
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "party_id", nullable = false)
    private PartyEntity party;

    @Override
    public String toString() {
        return "IdentificationEntity{" +
                "id=" + id +
                ", birthdate='" + birthdate + '\'' +
                ", identification='" + identification + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", identificationType=" + identificationType +
                '}';
    }
}
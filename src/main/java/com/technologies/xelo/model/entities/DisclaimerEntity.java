package com.technologies.xelo.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:27
 */
@Entity
@Table(schema = "data_capture", name = "disclaimer", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class DisclaimerEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(nullable = false, name = "disclaimerstatus", length = 50)
    private String disclaimerstatus;

    @Override
    public String toString() {
        return "DisclaimerEntity{" +
                "id=" + id +
                ", disclaimerstatus='" + disclaimerstatus + '\'' +
                ", party=" + party.getId() +
                '}';
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "party_id", nullable = false)
    private PartyEntity party;
}

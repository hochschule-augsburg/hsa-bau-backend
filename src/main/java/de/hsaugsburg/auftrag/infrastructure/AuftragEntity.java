package de.hsaugsburg.auftrag.infrastructure;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Auftrag")
public class AuftragEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "UUID", unique = true, nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BAUVORHABEN")
    private String bauvorhaben;

    @Column(name = "KUNDE")
    private String kunde;

    @Column(name = "MONTEUR")
    private String monteur;

    @Column(name = "Status")
    private String status;
}

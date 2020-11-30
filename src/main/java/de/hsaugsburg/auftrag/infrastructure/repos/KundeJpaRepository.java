package de.hsaugsburg.auftrag.infrastructure.repos;

import de.hsaugsburg.kunde.infrastructure.KundeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeJpaRepository extends JpaRepository<KundeEntity, String> {
}

package de.hsaugsburg.kunde.infrastructure.repos;

import de.hsaugsburg.kunde.infrastructure.KundeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundenJpaRepository extends JpaRepository<KundeEntity, String> {
}

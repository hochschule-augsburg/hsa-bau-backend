package de.hsaugsburg.auftrag.infrastructure.repos;

import de.hsaugsburg.auftrag.infrastructure.AuftragEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuftragJpaRepository extends JpaRepository<AuftragEntity, String> {
}

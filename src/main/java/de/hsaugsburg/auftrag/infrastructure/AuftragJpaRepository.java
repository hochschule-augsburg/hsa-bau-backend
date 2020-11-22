package de.hsaugsburg.auftrag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuftragJpaRepository extends JpaRepository<AuftragEntity, String> {
}

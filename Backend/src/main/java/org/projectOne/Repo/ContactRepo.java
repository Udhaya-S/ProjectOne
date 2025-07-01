package org.projectOne.Repo;

import org.projectOne.Entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contacts,Long> {
    List<Contacts> findByUsers_Username(String username);
}

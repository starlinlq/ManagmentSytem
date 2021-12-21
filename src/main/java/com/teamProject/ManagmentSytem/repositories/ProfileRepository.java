package com.teamProject.ManagmentSytem.repositories;

import com.teamProject.ManagmentSytem.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

}

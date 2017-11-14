package fr.emse.majeureinfo.webserviceproject.dao;

import fr.emse.majeureinfo.webserviceproject.model.Noise;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoiseDao extends JpaRepository<Noise, Long> {

}

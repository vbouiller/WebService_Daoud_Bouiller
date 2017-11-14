package fr.emse.majeureinfo.webserviceproject.dao;



import fr.emse.majeureinfo.webserviceproject.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightDao  extends JpaRepository<Light, Long>, LightDaoCustom {
}


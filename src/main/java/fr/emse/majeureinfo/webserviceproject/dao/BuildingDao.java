package fr.emse.majeureinfo.webserviceproject.dao;



import fr.emse.majeureinfo.webserviceproject.model.Building;
import fr.emse.majeureinfo.webserviceproject.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom {
}


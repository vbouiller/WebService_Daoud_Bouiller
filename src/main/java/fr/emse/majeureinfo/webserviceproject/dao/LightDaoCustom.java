package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import java.util.List;

public interface LightDaoCustom {

    public List<Light> findOnLights();
    public List<Light> findLightsByStatus(Status st);

}



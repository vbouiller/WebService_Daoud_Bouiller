package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class LightDaoImpl implements LightDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        return query.setParameter("value", Status.ON)
                .getResultList();
    }
    @Override
    public List<Light> findLightsByStatus(Status st) {
        String jpql = "select lt from Light lt where lt.status = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        return query.setParameter("value", st)
                .getResultList();
    }

    @Override
    public void turnAllLights(Status st) {
        Status revers= Status.ON;
        if (st.equals(Status.ON))revers=Status.OFF;
        String jpql = "update lt from Light lt set lt.status=:rev where lt.status = :value";
        TypedQuery<Light> query = em.createQuery(jpql, Light.class);
        query.setParameter("value", st).setParameter("rev",revers)
                .getResultList();
    }


}


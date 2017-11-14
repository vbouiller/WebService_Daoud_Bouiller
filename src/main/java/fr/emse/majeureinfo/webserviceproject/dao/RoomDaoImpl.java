package fr.emse.majeureinfo.webserviceproject.dao;

import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomDaoImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findRoomsWithOnLights() {
        String jpql = "select rm from Room rm JOIN rm.light lt where lt.status = :value";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.setParameter("value", Status.ON)
                .getResultList();
    }
}


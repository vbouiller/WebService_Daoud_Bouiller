package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> countRooms() {
        return null;
    }

    @Override
    public List<Room> countRoomsWithLightOn() {
        String jpql = "select rm from Building";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.getResultList();
    }

    @Override
    public List<Room> countRoomsWithRingerOn() {
        return null;
    }

    @Override
    public List<Room> findRoomsByLightStatus(Status status) {
        return null;
    }

    @Override
    public List<Room> findRoomsByNoiseStatus(Status status) {
        return null;
    }


}


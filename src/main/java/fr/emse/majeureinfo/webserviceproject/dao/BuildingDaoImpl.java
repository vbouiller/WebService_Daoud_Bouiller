package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Building;
import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<Building> countRoomsWithLightOn() {
        String jpql = "select b,count (rm) from Building b,IN(  b.rooms)as rm JOIN rm.light lt where lt.status = :value group by b";
        Query query = em.createQuery(jpql);
        return query.setParameter("value", Status.ON).getResultList();
    }

    @Override
    public List<Building> countRoomsWithRingerOn() {

        String jpql = "select b,count (rm) from Building b,IN(  b.rooms)as rm JOIN rm.noise n where n.status = :value group by b";
        Query query = em.createQuery(jpql);
        return query.setParameter("value", Status.ON).getResultList();
        //return findRoomsByNoiseStatus(Status.ON,bid).size();
    }

    @Override
    public List<Room> findRoomsByLightStatus(Status status, Long BuildingId) {

        String jpql = "select rm from Building b,IN(  b.rooms)as rm JOIN rm.light lt where lt.status = :value and b.id= :Bid";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.setParameter("value", status).setParameter("Bid",BuildingId)
                .getResultList();

    }

    @Override
    public List<Room> findRoomsByNoiseStatus(Status status, Long BuildingId) {
        String jpql = "select rm from Building b,IN(  b.rooms)as rm JOIN rm.noise n where n.status = :value and b.id= :Bid";
        TypedQuery<Room> query = em.createQuery(jpql, Room.class);
        return query.setParameter("value", status).setParameter("Bid",BuildingId)
                .getResultList();

    }


}


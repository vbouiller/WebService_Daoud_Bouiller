package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Room;

import java.util.List;

public interface BuildingDaoCustom {

    public List<Room> countRooms();
    public List<Room> countRoomsWithLightOn();
    public List<Room> countRoomsWithRingerOn();


}



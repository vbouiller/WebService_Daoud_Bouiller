package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Noise;
import fr.emse.majeureinfo.webserviceproject.model.Room;

public class RoomDto {

    private final Long id;
    private final Light light;
    private final Noise noise;

    public RoomDto(Room room){
        this.id=room.getId();
        this.light=room.getLight();
        this.noise=room.getNoise();
    }

    public Long getId() {
        return id;
    }

    public LightDto getLight() {
        return new LightDto(light);
    }

    public NoiseDto getNoise() {
        return new NoiseDto(noise);
    }
}

package fr.emse.majeureinfo.webserviceproject.dao;


import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import fr.emse.majeureinfo.webserviceproject.model.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/test.properties")
public class RoomDaoCustomTest {

    @Autowired
    private RoomDao roomDao;


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    protected static final DbSetupTracker TRACKER = new DbSetupTracker();

    private static final Operation DELETE_ALL = DeleteAll.from("ROOM", "LIGHT", "NOISE");

    protected void dbSetup(Operation operation) {
        DbSetup setup = new DbSetup(new DataSourceDestination(dataSource),
                Operations.sequenceOf(DELETE_ALL, operation));
        TRACKER.launchIfNecessary(setup);
    }

    @Before
    public void prepare() {
        Operation light =
                Insert.into("LIGHT")
                        .withDefaultValue("status", Status.ON)
                        .columns("id", "level")
                        .values(1L, 22)
                        .build();

        Operation noise =
                Insert.into("NOISE")
                        .withDefaultValue("status", Status.ON)
                        .columns("id", "level")
                        .values(1L, 22)
                        .build();

        Operation room =
                Insert.into("ROOM")
                        .columns("id", "light_id", "noise_id")
                        .values(1L, 1L, 1L)
                        .build();

        dbSetup(Operations.sequenceOf(light,noise,room));
    }

    @Test
    public void findRoomsWithOnLights() {
        TRACKER.skipNextLaunch();
        assertThat(roomDao.findRoomsWithOnLights()).hasSize(1);
    }
    @Test
    public void findRoomsByLightStatus() {
        TRACKER.skipNextLaunch();
        assertThat(roomDao.findRoomsByLightStatus(Status.OFF)).hasSize(1);
    }
    @Test
    public void findRoomsByRingerStatus() {
        TRACKER.skipNextLaunch();
        assertThat(roomDao.findRoomsByRingerStatus(Status.OFF)).hasSize(1);
    }


}


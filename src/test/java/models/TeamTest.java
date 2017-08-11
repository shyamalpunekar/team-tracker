package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ssaxe1 on 8/11/17.
 */
public class TeamTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void Team_instantiatesCorrectly_true() {
        Team testTeam = new Team("IT Crowd");
        assertEquals(true, testTeam instanceof Team);
    }

    @Test
    public void getTeamName_teamInstantiatesWithName_ITCrowd() {
        Team testTeam = new Team("IT Crowd");
        assertEquals("IT Crowd", testTeam.getmTeamname());
    }

}
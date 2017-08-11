package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Guest on 8/10/17.
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
        Team testTestTeam = new Team("IT Crowd");
        assertEquals("IT Crowd", testTestTeam.getMeaning());
    }

    @Test
    public void all_returnsAllInstancesOfTeam_true() {
        Team firstTestTeam = new Team("IT Crowd");
        Team secondTestTeam = new Team("The Guild");
        assertEquals(false, Team.getAll().contains(firstTestTeam));
        assertEquals(false, Team.getAll().contains(secondTestTeam));
    }

    @Test
    public void clear_emptiesAllTeamsFromList_0() {
        Team testTestTeam = new Team("IT Crowd");
        Team.clearAllBlogs();
        assertEquals(Team.getAll().size(), 0);
    }

    @Test
    public void getId_teamsInstantiateWithAnId_1() {
        Team.clearAllBlogs();
        Team testTestTeam = new Team("IT Crowd");
        assertEquals(1, testTestTeam.getId());
    }

    @Test
    public void find_returnsTeamWithSameId_secondTeam() {
        Team.clearAllBlogs();
        Team firstTestTeam = new Team("IT Crowd");
        Team secondTestTeam = new Team("The Guild");
        assertEquals(Team.findById(secondTestTeam.getId()), secondTestTeam);
    }

    @Test
    public void getMembers_initiallyReturnsEmptyList_ArrayList() {
        Team.clearAllBlogs();
        Team testTestTeam = new Team("IT Crowd");
        //assertEquals(0, testTestTeam.getMeaning().size());
    }

    @Test
    public void addMember_addsMemberToList_true() {
        Team testTestTeam = new Team("IT Crowd");
        TestMember newTestMember = new TestMember("Kattie");
        testTestTeam.addMember(newTestMember);
        //assertTrue(testTestTeam.getMembers().contains(newTestMember));
    }

}
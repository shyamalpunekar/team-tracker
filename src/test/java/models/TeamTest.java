package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by spunek on 8/11/17.
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
        assertEquals("IT Crowd", testTeam.getMeaning());
    }

    @Test
    public void all_returnsAllInstancesOfTeam_true() {
        Team firstTeam = new Team("IT Crowd");
        Team secondTeam = new Team("The Guild");
        assertEquals(false, Team.getAll().contains(firstTeam));
        assertEquals(false, Team.getAll().contains(secondTeam));
    }

    @Test
    public void clear_emptiesAllTeamsFromList_0() {
        Team testTeam = new Team("IT Crowd");
        Team.clearAllBlogs();
        assertEquals(Team.getAll().size(), 0);
    }

    @Test
    public void getId_teamsInstantiateWithAnId_1() {
        Team.clearAllBlogs();
        Team testTeam = new Team("IT Crowd");
        assertEquals(1, testTeam.getId());
    }

    @Test
    public void find_returnsTeamWithSameId_secondTeam() {
        Team.clearAllBlogs();
        Team firstTeam = new Team("IT Crowd");
        Team secondTeam = new Team("The Guild");
        assertEquals(Team.findById(secondTeam.getId()), secondTeam);
    }

    @Test
    public void getMembers_initiallyReturnsEmptyList_ArrayList() {
        Team.clearAllBlogs();
        Team testTeam = new Team("IT Crowd");
        //assertEquals(0, testTeam.getMeaning().size());
    }

    @Test
    public void addMember_addsMemberToList_true() {
        Team testTeam = new Team("IT Crowd");
        Member newMember = new Member("Kattie");
        testTeam.addMember(newMember);
        //assertTrue(testTeam.getMembers().contains(newMember));
    }

}
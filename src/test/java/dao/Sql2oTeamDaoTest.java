package dao;

import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by spunek on 8/18/17.
 */
public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection conn;
    private Sql2oMemberDao memberDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Team setupNewTeam() { return new Team("ROCK");}
    public Team setupNewTeamTwo() { return new Team("IT-RAW");}

    @Test
    public void addingTeamSetsId() throws Exception {
        Team team = setupNewTeam();
        int originalCuisineId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalCuisineId, team.getId()); //how does this work?
    }

    @Test
    public void existingTeamCanBeFoundById() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void addedTeamsAreReturnedFromGetAll() throws Exception {
        Team cuisine = setupNewTeam();
        teamDao.add(cuisine);
        assertEquals(1, teamDao.getAll().size());
    }




}
package dao;

import models.Member;
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
public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Member setupNewMember() { return new Member("Rick",  1);}
    public Member setupNewMemberTwo() { return new Member("Marty", 2);}


    @Test
    public void addingMemberSetsId() throws Exception {
        Member member = setupNewMember();
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void existingMemberCanBeFoundById() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void addedMembersAreReturnedFromgetAll() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void updateChangesMemberContent() throws Exception {
        String initialMemberName = "Jenny";
        Member member = new Member(initialMemberName, 1);
        memberDao.add(member);

        memberDao.update(member.getId(), "test", 1);
        Member updatedMember = memberDao.findById(member.getId());
        assertNotEquals(initialMemberName, updatedMember.getMemberName());
    }

    @Test
    public void deleteByIdDeletesCorrectMember() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        memberDao.deleteById(member.getId());
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Member member = setupNewMember();
        Member memberTwo = setupNewMemberTwo();
        memberDao.add(member);
        memberDao.add(memberTwo);
        int daoSize = memberDao.getAll().size();
        memberDao.clearAllMembers();
        assertTrue(daoSize > 0 && daoSize > memberDao.getAll().size());
    }

    @Test
    public void memberIdIsReturnedCorrectly() throws Exception {
        Member member = setupNewMember();
        int originalMemberId = member.getMemberId();
        memberDao.add(member);
        assertEquals(originalMemberId, memberDao.findById(member.getId()).getMemberId());
    }

}




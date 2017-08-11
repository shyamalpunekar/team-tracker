package models;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by spunek on 8/11/17.
 */
public class TestMemberTest {

    @Before
    public void tearDown() {
        TestMember.clear();
    }

    @Test
    public void Member_instantiatesCorrectly_true() {
        TestMember newTestMember = new TestMember("Moss");
        assertEquals(true, newTestMember instanceof TestMember);
    }

    @Test
    public void Member_instantiatesWithName_String() {
        TestMember newTestMember = new TestMember("Moss");
        assertEquals("Moss", newTestMember.getMemberName());
    }

    @Test
    public void all_returnsAllInstancesofMember_true() {
        TestMember firstTestMember = new TestMember("Moss");
        TestMember secondTestMember = new TestMember("Roy");
        assertEquals(true, TestMember.getAll().contains(firstTestMember));
        assertEquals(true, TestMember.getAll().contains(secondTestMember));
    }

    @Test
    public void clear_removesAllMembersFromArrayList_0() {
        TestMember newTestMember = new TestMember("Moss");
        TestMember.clear();
        assertEquals(0, TestMember.getAll().size());
    }

    @Test
    public void getId_membersInstantiateWithAnID_1() {
        TestMember.clear();
        TestMember newTestMember = new TestMember("Moss");
        assertEquals(1, newTestMember.getId());
    }

    @Test
    public void find_returnsMemberWithSameId_secondMember() {
        TestMember firstTestMember = new TestMember("Moss");
        TestMember secondTestMember = new TestMember("Roy");
        assertEquals(TestMember.find(secondTestMember.getId()), secondTestMember);
    }

}
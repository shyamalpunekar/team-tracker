package models;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

    /**
     * Created by spunek on 8/11/17.
     */
    public class DefinitionTest {

        @Before
        public void tearDown() {
            TestMember.clear();
        }

        @Test
        public void Member_instantiatesCorrectly_true() {
            Definition newTestMember = new Definition("Moss");
            assertEquals(true, newTestMember instanceof Definition);
        }

        @Test
        public void Member_instantiatesWithName_String() {
            Definition newTestMember = new Definition("Moss");
            assertEquals("Moss", newTestMember.getDescription());
        }

        @Test
        public void all_returnsAllInstancesofMember_true() {
            Definition firstTestMember = new Definition("Moss");
            Definition secondTestMember = new Definition("Roy");
            assertEquals(true, TestMember.getAll().contains(firstTestMember));
            assertEquals(true, TestMember.getAll().contains(secondTestMember));
        }

        @Test
        public void clear_removesAllMembersFromArrayList_0() {
            Definition newTestMember = new Definition("Moss");
            Definition.clearDefinition();
            assertEquals(0, TestMember.getAll().size());
        }

        @Test
        public void getId_membersInstantiateWithAnID_1() {
            Definition.clearDefinition();
            Definition newTestMember = new Definition("Moss");
            assertEquals(1, newTestMember.getId());
        }

        @Test
        public void find_returnsMemberWithSameId_secondMember() {
            Definition firstTestMember = new Definition("Moss");
            Definition secondTestMember = new Definition("Roy");
            assertEquals(Definition.find(secondTestMember.getId()), secondTestMember);
        }

    }


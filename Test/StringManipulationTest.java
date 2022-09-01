import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StringManipulationTest {

    private StringManipulationInterface manipulatedstring;

    @BeforeEach
    public void setUp() {
        manipulatedstring = new StringManipulation();
    }

    @AfterEach
    public void tearDown() {
        manipulatedstring = null;
    }

    @Test
    public void testCount1() {
        manipulatedstring.setString("This is my string");
        int length = manipulatedstring.count();
        assertEquals(4, length);
    }

    @Test
    public void testCount2() {
        //test with empty string
        manipulatedstring.setString("");
        int length = manipulatedstring.count();
        assertEquals(0, length);
    }

    @Test
    public void testCount3() {
        //test with null string
        int length = manipulatedstring.count();
        assertEquals(0, length);
    }

    @Test
    public void testCount4() {
        //test with multiple spaces after each other
        manipulatedstring.setString("This  has  extra  spaces");
        int length = manipulatedstring.count();
        assertEquals(4, length);
    }

    @Test
    public void testRemoveNthCharacter1() {
        manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        String expected = "I' bttr uts0e 16tsinths trn6 rgh?";
        String output = manipulatedstring.removeNthCharacter(3, false);

        assertEquals(expected, output);
    }

    @Test
    public void testRemoveNthCharacter2() {
        manipulatedstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("I'  b tt r  ut s0 e  16 ts in th s  tr n6  r gh ?", manipulatedstring.removeNthCharacter(3, true));
    }

    @Test
    public void testRemoveNthCharacter3() {
        //test null
        assertEquals("", manipulatedstring.removeNthCharacter(1, false));
    }

    @Test
    public void testRemoveNthCharacter4() {
        //test throw illegal argument
        manipulatedstring.setString("abcd");
        assertThrows(IllegalArgumentException.class, () -> {
            manipulatedstring.removeNthCharacter(0, false);
        });
    }

    @Test
    public void testRemoveNthCharacter5() {
        //test throw index out of bounds
        manipulatedstring.setString("abcd");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            manipulatedstring.removeNthCharacter(8, false);
        });
    }

    @Test
    public void testRemoveNthCharacter6() {
        //test that removes last letter and puts space
        //abcdef n 2
        manipulatedstring.setString("abcedf");
        assertEquals("a c d ", manipulatedstring.removeNthCharacter(2, true));

    }

    @Test
    public void testRemoveNthCharacter7() {
        //test that removes last letter without space
        manipulatedstring.setString("abcedf");
        assertEquals("acd", manipulatedstring.removeNthCharacter(2, false));
    }

    @Test
    public void testGetSubStrings1() {
        //Given
        manipulatedstring.setString("This is my string");
        String[] sStings = manipulatedstring.getSubStrings(3, 4);

        assertEquals(sStings[0], "my");
        assertEquals(sStings[1], "string");
    }

    @Test
    public void testGeSubStrings2() {
        //test with multiple spaces
        manipulatedstring.setString("This  is  my  string");
        String[] sStings = manipulatedstring.getSubStrings(3, 4);

        assertEquals(sStings[0], "my");
        assertEquals(sStings[1], "string");
    }

    @Test
    public void testGeSubStrings3() {
        //test when start word == end word
        manipulatedstring.setString("This  is  my  string");
        String[] sStings = manipulatedstring.getSubStrings(3, 3);
        assertEquals(sStings[0], "my");
    }

    @Test
    public void testGeSubStrings4() {
        //test startword is 0
        manipulatedstring.setString("This is my string");
        assertThrows(IllegalArgumentException.class, () -> {
            manipulatedstring.getSubStrings(0, 2);
        });
    }

    @Test
    public void testGeSubStrings5() {
        //test end word 0
        manipulatedstring.setString("This is my string");
        assertThrows(IllegalArgumentException.class, () -> {
            manipulatedstring.getSubStrings(1, 0);
        });
    }

    @Test
    public void testGeSubStrings6() {
        //test startword greater than endword
        manipulatedstring.setString("This is my string");
        assertThrows(IllegalArgumentException.class, () -> {
            manipulatedstring.getSubStrings(3, 2);
        });
    }

    @Test
    public void testRestoreString1() {
        //Given
        manipulatedstring.setString("art");
        int[] array;
        array = new int[]{1, 0, 2};
        String restoreString = manipulatedstring.restoreString(array);
        assertEquals(restoreString, "rat");
    }

    @Test
    public void testRestoreString2() {
        //test wrong number of indices
        manipulatedstring.setString("art");
        int[] array;
        array = new int[]{1, 0};
        assertThrows(IllegalArgumentException.class, () -> {
            manipulatedstring.restoreString(array);
        });

    }

    @Test
    public void testRestoreString3() {
        //test index value below 0
        manipulatedstring.setString("art");
        int[] array;
        array = new int[]{-1, 0, 2};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            manipulatedstring.restoreString(array);
        });


    }

    @Test
    public void testRestoreString4() {
        //test index value too large
        manipulatedstring.setString("art");
        int[] array;
        array = new int[]{1, 0, 5};
        assertThrows(IndexOutOfBoundsException.class, () -> {
            manipulatedstring.restoreString(array);
        });

    }

    @Test
    public void testRestoreString5() {
        //test empty
        manipulatedstring.setString("");
        int[] array;
        array = new int[]{};
        String restoreString = manipulatedstring.restoreString(array);
        assertEquals(restoreString, "");

    }

}

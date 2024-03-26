package montecarlochess.bitboards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class BitboardTest {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getFile1() {
        Bitboard bb = new Bitboard();
        long state = 0x80; // State of a piece in rank 1 file 1
        bb.state = state;
        int file = bb.getFile();
        assertEquals(1,file);
    }

    @Test
    public void getFile2() {
        Bitboard bb = new Bitboard();
        long state = 0x40; // State of a piece in rank 1 file 2
        bb.state = state;
        int file = bb.getFile();
        assertEquals(2,file);
    }

    @Test
    public void getFile3() {
        Bitboard bb = new Bitboard();
        long state = 0x20; // State of a piece in rank 1 file 3
        bb.state = state;
        int file = bb.getFile();
        assertEquals(3,file);
    }

    @Test
    public void getFile4() {
        Bitboard bb = new Bitboard();
        long state = 0x10;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(4,file);
    }

    @Test
    public void getFile5() {
        Bitboard bb = new Bitboard();
        long state = 0x08;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(5,file);
    }


    @Test
    public void getFile6() {
        Bitboard bb = new Bitboard();
        long state = 0x04;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(6,file);
    }

    @Test
    public void getFile7() {
        Bitboard bb = new Bitboard();
        long state = 0x02;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(7,file);
    }

    @Test
    public void getFile8() {
        Bitboard bb = new Bitboard();
        long state = 0x01;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(8,file);
    }

    @Test
    public void getFile3Rank2() {
        Bitboard bb = new Bitboard();
        long state = 0x2000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(3, file);
    }

    @Test
    public void getFile4Rank3() {
        Bitboard bb = new Bitboard();
        long state = 0x100000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(4, file);
    }

    
    @Test
    public void getFile5Rank4() {
        Bitboard bb = new Bitboard();
        long state = 0x08000000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(5, file);
    }
}

package montecarlochess.bitboards;

import montecarlochess.Chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;


public class BitboardTest {

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getFile1() {
        Bitboard bb = new Bitboard();
        long state = 0b10000000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(1,file);
    }

    @Test
    public void getFile2() {
        Bitboard bb = new Bitboard();
        long state = 0b01000000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(2,file);
    }
    @Test
    public void getFile3() {
        Bitboard bb = new Bitboard();
        long state = 0b00100000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(3,file);
    }
    @Test
    public void getFile4() {
        Bitboard bb = new Bitboard();
        long state = 0b00010000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(4,file);
    }
    @Test
    public void getFile5() {
        Bitboard bb = new Bitboard();
        long state = 0b00001000;
        bb.state = state;
        int file = bb.getFile();
        assertEquals(5,file);
    }
}

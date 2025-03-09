package e2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class LogicTest {

    private LogicsImpl logics;
    private Knight knight;
    private Pawn pawn;
    private final static int SIZE = 5;

    @BeforeEach
    void init(){
        knight = new Knight(SIZE, 0 ,0);
        pawn = new Pawn(SIZE, 1,2);
        logics = new LogicsImpl(SIZE, knight, pawn);
    }

    @Test
    void hasKnight(){
        assertTrue(logics.hasKnight(knight.getPos().getX(), knight.getPos().getY()));
    }

    @Test
    void hasPawn(){
        assertTrue(logics.hasPawn(pawn.getPos().getX(), pawn.getPos().getY()));
    }

    @Test
    void tryToHit(){
        assertTrue(logics.hit(pawn.getPos().getX(),pawn.getPos().getY()));
    }

    @Test
    void illegalMove(){
        Pair<Integer,Integer> illegalMove = new Pair<>(SIZE, SIZE);
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(illegalMove.getX(), illegalMove.getY()));
    }

    @Test
    void emptyCells(){
        int emptyCells = 0;
        int expectedEmptyCells = (SIZE * SIZE) - 2;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(!logics.hasKnight(i,j) && !logics.hasPawn(i,j)){
                    emptyCells++;
                }
            }
        }
        assertEquals(expectedEmptyCells, emptyCells);
    }
}
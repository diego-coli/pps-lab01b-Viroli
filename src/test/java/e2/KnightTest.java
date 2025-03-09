package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

public class KnightTest {

    private Knight knight;
    private Knight randomKnight;
    private Set<Pair<Integer,Integer>> notEmptyCells;
    private final static int SIZE = 5;

    @BeforeEach
    void init(){
        notEmptyCells = new HashSet<>();
        knight = new Knight(SIZE, 0, 0);   //genera un Knight nella prima casella vuota
    }

    @Test
    void checkMoves(){
        Pair<Integer,Integer> validMove = new Pair<>(1,2);
        Pair<Integer,Integer> invalidMove = new Pair<>(2,2);
        assertAll(
                () -> assertTrue(knight.checkMove(validMove.getX(), validMove.getY())),
                () -> assertThrows(IllegalArgumentException.class, () -> knight.move(invalidMove.getX(), invalidMove.getY()))
        );
    }

    @Test
    void tryToMove(){
        Pair<Integer,Integer> validMove = new Pair<>(1,2);
        knight.move(validMove.getX(), validMove.getY());
        assertAll(
                () -> assertEquals(knight.getPos().getX(), validMove.getX()),
                () -> assertEquals(knight.getPos().getY(), validMove.getY())
        );
    }

    @Test
    void tryToGetPos(){
        Pair<Integer,Integer> expectedPos = new Pair<>(0,0);
        assertEquals(expectedPos, knight.getPos());
    }

    @Test
    void checkRandomKnight(){
        notEmptyCells = new HashSet<>();
        randomKnight = new Knight(SIZE, notEmptyCells);
        boolean generated = false;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if(randomKnight.notEmptyCell(i,j)){
                    generated = true;
                }
            }
        }
        assertTrue(generated);
    }
}

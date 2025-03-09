package e2;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private LogicsImpl logics;
  private LogicsImpl logicsToCheckHit;
  private Pair<Integer, Integer> knightPos;
  private Pair<Integer,Integer> pawnPos;
  private final static int SIZE = 5;

  @BeforeEach
  void init(){          //initialize a new logic implementation and get initial Knight and Pawn positions
    this.logics = new LogicsImpl(SIZE);
    getPos();
  }

  void getPos() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (logics.hasKnight(i, j)) {
          knightPos = new Pair<>(i, j);
        }
        if (logics.hasPawn(i, j)) {
          pawnPos = new Pair<>(i, j);
        }
        if (knightPos != null && pawnPos != null) {
          return;
        }
      }
    }
  }

  @Test
  void differentInitialPositions(){
    assertFalse(logics.hasKnight(pawnPos.getX(), pawnPos.getY()));
  }

  @Test
  void notHitPawn(){
    Pair<Integer,Integer> knightPos = new Pair<>(0,0);
    Pair<Integer,Integer> pawnPos = new Pair<>(4,4);
    Pair<Integer,Integer> move = new Pair<>(1,2);
    logicsToCheckHit = new LogicsImpl(SIZE, knightPos.getX(), knightPos.getY(), pawnPos.getX(), pawnPos.getY());
    assertFalse(logicsToCheckHit.hit(move.getX(), move.getY()));
  }

  @Test
  void checkInvalidMove(){
    Pair<Integer,Integer> invalidMove = new Pair<>(SIZE, SIZE);
    assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(invalidMove.getX(), invalidMove.getY()));
  }

  @Test
  void hitPawn(){
    Pair<Integer,Integer> knightPos = new Pair<>(0,0);
    Pair<Integer,Integer> pawnPos = new Pair<>(1,2);
    logicsToCheckHit = new LogicsImpl(SIZE, knightPos.getX(), knightPos.getY(), pawnPos.getX(), pawnPos.getY());
    assertTrue(logicsToCheckHit.hit(pawnPos.getX(), pawnPos.getY()));
  }
}

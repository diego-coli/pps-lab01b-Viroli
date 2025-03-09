package e2;

import java.util.Set;

public interface GenericPiece {

    Pair<Integer,Integer> getPos();

    boolean notEmptyCell(int row, int col);
}

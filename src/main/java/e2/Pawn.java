package e2;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends GenericPieceImpl{

    public Pawn(int size, Set<Pair<Integer,Integer>> notEmptyCells){
        super(size, notEmptyCells);
    }

    public Pawn(int size, int row, int col) {  //per Test
        super(size, new HashSet<>());
        this.pos = new Pair<>(row, col);
    }
}

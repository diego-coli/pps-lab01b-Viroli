package e2;

import java.util.Random;
import java.util.Set;

public class GenericPieceImpl implements GenericPiece {

    protected Pair<Integer, Integer> pos;
    private final Random random = new Random();

    public GenericPieceImpl(int size, Set<Pair<Integer,Integer>> notEmptyCells) {
        do {
            this.pos = new Pair<>(random.nextInt(size), random.nextInt(size));
        } while (notEmptyCells.contains(pos));
        notEmptyCells.add(this.pos);
    }

    @Override
    public Pair<Integer,Integer> getPos(){
        return this.pos;
    }

    @Override
    public boolean notEmptyCell(int row, int col){
        Pair<Integer,Integer> tmpPos = new Pair<>(row, col);
        return this.pos.equals(tmpPos);
    }
}

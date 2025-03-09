package e2;

import java.util.HashSet;
import java.util.Set;

public class Knight extends GenericPieceImpl{

    public Knight(int size, Set<Pair<Integer,Integer>> notEmptyCells){
        super(size, notEmptyCells);
    }

    public Knight(int size, int row, int col) {  //per Test
        super(size, new HashSet<>());
        this.pos = new Pair<>(row, col);
    }

    public boolean checkMove(int row, int col){
        int x = row - this.getPos().getX();
        int y = col - this.getPos().getY();

        if(x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3){
            return true;
        } else{
            return false;
        }
    }

    public void move(int row, int col){
        if(checkMove(row, col)){
            this.pos = new Pair<>(row, col);
        }
        else{
            throw new IllegalArgumentException("Cannot move this piece!");
        }
    }
}

package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(int size){
    	this.size = size;
        this.pawn = this.randomEmptyPosition();			//genera posizioni iniziali casuali per Pawn e Knight
        this.knight = this.randomEmptyPosition();	
    }

	public LogicsImpl(int size, int rowK, int colK, int rowP, int colP){ //costruttore aggiuntivo per passare i Test
		this.size = size;
		this.knight = new Pair<>(rowK, colK);
		this.pawn = new Pair<>(rowP, colP);
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {							//prova a muovere Knight
																	//riceve come parametri x e y della casella cliccata
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();		//lancia eccezione se coordinate sforano la dimensione massima
		}

		int x = row-this.knight.getX();			//setta la coordinata x che Knight dovrebbe raggiungere dopo il click su button
		int y = col-this.knight.getY();			//setta la coordinata y che Knight dovrebbe raggiungere dopo il click su button
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {	//verifica che Knight compia un movimento ad 'L'
			this.knight = new Pair<>(row,col);				//se ok, consente movimento e gli fornisce nuove coordinate
			return this.pawn.equals(this.knight);			//ritorna vero se Knight e Pawn coincidono (Pawn mangiato)
		}
		return false;										//se movimento non consentito, ritorna falso
	}

	@Override
	//ritorna vero se la casella ha Knight
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	//ritorna vero se la casella ha Pawn
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}

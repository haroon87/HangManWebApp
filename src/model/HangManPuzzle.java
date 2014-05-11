package model;

/*
 * @author Haroon Ashraf
 * 
 * This is main model class. It stores all the puzzle pieces and misses information. The puzzle pieces are 
 * activated one by one according to the miss number
 * */
public class HangManPuzzle {

	private String puzzleBlocks[];
	private int misses;

	public String[] getPuzzleBlocks() {
		return puzzleBlocks;
	}

	public void setPuzzleBlocks(String[] puzzleBlocks) {
		this.puzzleBlocks = puzzleBlocks;
	}

	public HangManPuzzle() {
		puzzleBlocks = new String[11];
		this.puzzleBlocks[0] = "";
		this.puzzleBlocks[1] = "";
		this.puzzleBlocks[2] = "";
		this.puzzleBlocks[3] = "";
		this.puzzleBlocks[4] = "";
		this.puzzleBlocks[5] = "";
		this.puzzleBlocks[6] = "";
		this.puzzleBlocks[7] = "";
		this.puzzleBlocks[8] = "";
		this.puzzleBlocks[9] = "";
		this.puzzleBlocks[10] = "";

	}

	public void gameMisses()

	{

		if (this.misses == 1) {
			this.puzzleBlocks[10] = "---------------------|";
		}

		else if (this.misses == 2) {

			this.puzzleBlocks[1] = "                     | ";
			this.puzzleBlocks[2] = "                     | ";
			this.puzzleBlocks[3] = "                     | ";
			this.puzzleBlocks[4] = "                     | ";
			this.puzzleBlocks[5] = "                     | ";
			this.puzzleBlocks[6] = "                     | ";
			this.puzzleBlocks[7] = "                     | ";
			this.puzzleBlocks[8] = "                     | ";
			this.puzzleBlocks[9] = "                     | ";
		} else if (this.misses == 3) {
			this.puzzleBlocks[0] = "    __________________ ";
		} else if (this.misses == 4) {
			this.puzzleBlocks[1] = "     |               | ";
			this.puzzleBlocks[2] = "     |               | ";
		} else if (this.misses == 5) {
			this.puzzleBlocks[3] = "     O               | ";
		} else if (this.misses == 6) {
			this.puzzleBlocks[3] = "    \\O               | ";
		} else if (this.misses == 7) {
			this.puzzleBlocks[3] = "    \\O/              | ";
		} else if (this.misses == 8) {
			this.puzzleBlocks[4] = "     |               | ";
		} else if (this.misses == 9) {
			this.puzzleBlocks[5] = "      \\              | ";
		} else if (this.misses == 10) {
			this.puzzleBlocks[5] = "    / \\              | ";
		}
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

}

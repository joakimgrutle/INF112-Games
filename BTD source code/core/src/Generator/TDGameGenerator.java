package Generator;


import logicClasses.TDGame;

public class TDGameGenerator {
	public static TDGame randTDGame() {
		TDGame game = new TDGame();
        int lives           = IntGenerator.randValues(0,100000);
        int moneyAmount     = IntGenerator.randValues(0,100000);
        int waveCounter     = IntGenerator.randValues(0,100000);
        game.setPlayerLives(lives);
        game.changeMoneyAmount(moneyAmount-game.getMoneyAmount());
        game.setCurrentWaveIndex(waveCounter);
	    return game;
	
}
}

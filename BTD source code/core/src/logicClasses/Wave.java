package logicClasses;

import java.util.ArrayList;

import interfaces.IWave;

public class Wave implements IWave {

	private int waveNum;
	private int totalMobs;
	private int currentMobs = 0;
	private int defeatedMobs = 0;
	protected ArrayList<Mob> listOfWaveMobs;

	public Wave(int waveNum) {
		this.waveNum = waveNum;
		this.defeatedMobs = 0;
		Mob mob = Mob.createMob(TDGame.getInstance().getLevel());
		if (mob.getName().equals("boss")) {
			totalMobs = 1;
		} else {
			this.totalMobs = (waveNum * 1) + 10;
		}
		this.listOfWaveMobs = new ArrayList<>();
	}
	
	public Wave() {
		this.listOfWaveMobs = new ArrayList<>();
	}

	@Override
	public int getNumberOfMobs() {
		return totalMobs;
	}
	@Override
	public int getWaveNum() {
		return this.getWaveNum();
	}

	@Override
	public void addMob(Mob mob) {
		this.listOfWaveMobs.add(mob);
		currentMobs++;
	}

	@Override
	public boolean isFinished() {
		return currentMobs == 0 && defeatedMobs == totalMobs;
	}

	@Override
	public ArrayList<Mob> getListOfWaveMobs() {
		return listOfWaveMobs;
	}
	
	@Override
	public void update(float dt) {
		if (!listOfWaveMobs.isEmpty()) {
			for (int i = listOfWaveMobs.size() - 1; i >= 0; i--) {
				if (!listOfWaveMobs.get(i).isActive()) {
					listOfWaveMobs.remove(i);
					currentMobs--;
					defeatedMobs++;
				}
			}
		}
	}

}

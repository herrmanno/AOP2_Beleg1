package com.github.herrmanno;

/**
 * A player in the 'Risk' game
 * 
 * @author oliverherrmann
 *
 */
final class Player {
	
	private static final int LINE_WIDTH = 40;
	
	private final String name;
	private int stones;

	/**
	 * Creates a new player
	 * @param name the player's name
	 * @param stones the player's initial number of stones
	 * 
	 * @throws if name if null or empty
	 * @throws if stone count is zero or negative
	 */
	Player(String name, int stones) {
		if (null == name || name.isEmpty()) {
			throw new IllegalArgumentException("Name must not be null or empty");
		}
		
		if (stones <= 0) {
			throw new IllegalArgumentException("Stones must be greater zero");
		}
		
		this.name = name;
		this.stones = stones;
	}
	
	/**
	 * @return the player's name
	 */
	String getName() {
		return name;
	}
	
	/**
	 * @return the player's current amount of stones
	 */
	int getStones() {
		return stones;
	}
	
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(name + " (" + stones + ")");
		sb.append(System.lineSeparator());
		sb.append("-".repeat(LINE_WIDTH));
		sb.append(System.lineSeparator());
		for(int i = 0; i < stones / LINE_WIDTH; i++) {
			for (int j = 0; j < LINE_WIDTH; j++) {
				sb.append("\u2592");
				sb.append(" ");
			}
			sb.append(System.lineSeparator());
		}
		for (int i = 0; i < stones % LINE_WIDTH; i++) {
			sb.append("\u2592");
			sb.append(" ");
		}
		
		return sb.toString();
	}


	/**
	 * Decreases the player's amount of stones by one
	 */
	void decreaseStones() {
		this.stones--;
		
	}
	
}

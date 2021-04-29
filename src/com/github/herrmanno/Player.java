package com.github.herrmanno;

final class Player {
	
	private static final int LINE_WIDTH = 40;
	
	private final String name;
	private int stones;

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
	
	String getName() {
		return name;
	}
	
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


	void decreaseStones() {
		this.stones--;
		
	}
	
}

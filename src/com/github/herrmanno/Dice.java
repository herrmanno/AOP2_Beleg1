package com.github.herrmanno;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;


final class Dice {
	
	private final Random random;

	public Dice(long seed) {
		random = new Random(seed);
	}
	
	int[] rollDices(int numDices) {
		return Stream.generate(this::rollDice)
				.limit(numDices)				
				.sorted(Collections.reverseOrder())
				.mapToInt(i -> i)
				.toArray();	
	}
	
	int rollDice() {
		return random.nextInt(6) + 1;
	}

}

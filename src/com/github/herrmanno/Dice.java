package com.github.herrmanno;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Utility class for simulating multiple dice rolls
 * 
 * @author oliverherrmann
 */
final class Dice {
	
	private final Random random;

	/**
	 * Creates a new 'Dice' utility class
	 * @param seed the seed to initialize the random generator with
	 */
	public Dice(long seed) {
		random = new Random(seed);
	}
	
	/**
	 * Performs 'n' dice rolls
	 * 
	 * @param numDices the number of dice rolls to perform
	 * @return an array of n integers in [1,6], sorted descending
	 */
	int[] rollDices(int numDices) {
		return Stream.generate(this::rollDice)
				.limit(numDices)				
				.sorted(Collections.reverseOrder())
				.mapToInt(i -> i)
				.toArray();	
	}
	
	/**
	 * Performs a single dice roll
	 * @return an integer in [1,6]
	 */
	int rollDice() {
		return random.nextInt(6) + 1;
	}

}

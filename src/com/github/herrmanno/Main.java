package com.github.herrmanno;

import java.util.Optional;
import java.util.Random;

/**
 * Haupklasse für Beleg 1 im Kurs "Anwendungsorientierte Programmierung II (C680)" der HTWK Leipzig, SoSe 2021 
 *
 * @author oliverherrmann
 *
 */
public class Main {

	public static void main(String[] args) {
		final Random random = new Random(System.currentTimeMillis());
		
		int s1 = getArg(args, 0).orElse(10 + random.nextInt(11));
		int s2 = getArg(args, 1).orElse(10 + random.nextInt(11));
		
		new RiskGame(s1, s2).start();
	}
	
	/**
	 * Reads optional arguments as positive integers from cli args
	 * @param args the string of program parameters
	 * @param n the index of the parameter to parse
	 * @return an optional of the parsed parameter, if any
	 */
	private static Optional<Integer> getArg(String[] args, int n) {
		if (args.length < n + 1) {
			return Optional.empty();
		} else {
			try {
				int i = Integer.parseInt(args[n]);
				if (i <= 0) {
					throw new IllegalArgumentException("Initial stones count must be greater zero");
				} else {
					return Optional.of(i);
				}
			} catch (Exception e) {
				return Optional.empty();
			}
		}
	}
	

}

package com.github.herrmanno;

import java.util.Optional;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());
		
		int s1 = getArg(args, 0).orElse(10 + random.nextInt(11));
		int s2 = getArg(args, 1).orElse(10 + random.nextInt(11));
		
		new RiskGame(s1, s2).start();
	}
	
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

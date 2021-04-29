package com.github.herrmanno;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Simulation of a single turn in a 'Risk' game
 * 
 * A single turn may consist of one ore multiple 'attacks', which include roll dices of both players leading
 * to decreasing the number of stones of one or both players.
 * 
 * @author oliverherrmann
 *
 */
final class RiskGame {

	private final Player p1;
	private final Player p2;
	
	private final Scanner sc = new Scanner(System.in);
	private final Dice dice = new Dice(Calendar.getInstance().getTimeInMillis());   
	
	/**
	 * Creates a new risk game turn
	 * @param s1 the initial amount of stones of player 1
	 * @param s2 the initial amount of stones of player 2
	 */
	RiskGame(int s1, int s2) {
		p1 = new Player("Player 1", s1);
		p2 = new Player("Player 2", s2);
	}
	
	/**
	 * Starts the turn.
	 * 
	 * The user may choose with how many stones to attack and, if possible, whether to perform another attack move.
	 * The turn finishes if the user chooses to abort the attack or one player runs out of stones. 
	 */
	void start() {
		run(1);
	}
	
	/**
	 * Performs one attack
	 * @param round the current number of attack (in q sequence of attacks)
	 */
	private void run(int round) {
		if (p1.getStones() <= 0) {
			System.out.println(p2.getName() + " won");
		} else if (p2.getStones() <= 0) {
			System.out.println(p1.getName() + " won");
		} else {		
			System.out.println("Round " + round);
			System.out.println("=".repeat(40));
			System.out.println(p1.toString());
			System.out.println();
			System.out.println(p2.toString());
			
			System.out.println(System.lineSeparator());
		
			Optional<Integer> attackCount = getAttackCount(p1.getStones());
			if (attackCount.isPresent()) {
				attack(attackCount.get());				
				System.out.println();				
				System.out.println();
				run(++round);
			} else {
				System.out.println("Stopped attack");
			}
		}
	}
	
	/**
	 * Decreases one or both players stones based on the result of some dice rolls
	 * @param a the numbr of stones to attack with (e. g. the number of dices the attacker uses)
	 */
	private void attack(int a) {
		int[] dices_1 = dice.rollDices(a);
		int[] dices_2 = dice.rollDices(Math.min(a, p2.getStones()));		
		
		int l1 = 0, l2 = 0;
		for (
				int i = 0;
				i < dices_1.length && i < dices_2.length && p1.getStones() > 0 && p2.getStones() > 0;
				i++
		) {
			if (dices_1[i] > dices_2[i]) {
				p2.decreaseStones();
				l2++;
			} else {
				p1.decreaseStones();
				l1++;
			}
		}
		
		System.out.printf("%s rolled: %s and lost %d stones%s",
				p1.getName(),
				Arrays.stream(dices_1).mapToObj(Integer::toString).collect(Collectors.joining(",", "", "")),				
				l1,
				System.lineSeparator());
		System.out.printf("%s rolled: %s and lost %d stones%s",
				p2.getName(),
				Arrays.stream(dices_2).mapToObj(Integer::toString).collect(Collectors.joining(",", "", "")),
				l2,
				System.lineSeparator());
	}
	
	private Optional<Integer> getAttackCount(int maxStones) {
		final int max = Math.min(maxStones, 3);
		final StringBuilder sb = new StringBuilder();
		sb.append("Type ");
		for (int i = max; i > 0; i--) {
			sb.append("[" + i + "]: attack w/ " + i + " stones\t");
		}
		sb.append("[q]: stop attack");
		System.out.println(sb.toString());
		String a = sc.next();
		switch (a.strip()) {
			case "1": return Optional.of(1);
			case "2": return 2 <= max ? Optional.of(2) : getAttackCount(maxStones);
			case "3": return 3 <= max ? Optional.of(3) : getAttackCount(maxStones);
			case "q":
			case "Q": return Optional.empty();
			default: return getAttackCount(maxStones);
		}					
	}
}

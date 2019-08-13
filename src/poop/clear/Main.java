package poop.clear;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("File:");
		try (var in = new Scanner(System.in)) {
			var clear = new PoopClear(in.nextLine());  
			clear.findPoop();
			System.out.println("Fix?(Y or N)");
			if ("Y".equalsIgnoreCase(in.nextLine())) {
				clear.fixPoop();
			}
		} catch (Throwable e) {
			System.out.println(e);
		}
	}

}

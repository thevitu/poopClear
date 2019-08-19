package poop.clear;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Choose 1 to fix for-eachs 2 to find logic:");
		try (var in = new Scanner(System.in)) {
			var choosed = in.nextLine();
			switch (choosed) {
				case "1":
					fixPoop();
					break;
				case "2":
					findLogic();
					break;
			}
		} catch (Throwable e) {
			System.out.println(e);
		}
	}

	private static void fixPoop() {
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

	private static void findLogic() {
		System.out.println("File:");
		try (var in = new Scanner(System.in)) {
			var clear = new PoopClear(in.nextLine());
			clear.findLogic();
		} catch (Throwable e) {
			System.out.println(e);
		}
	}

}

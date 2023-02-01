package com.javacake341545.java.test;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==program starts==");
		
		Scanner sc = new Scanner(System.in);
		
		
		while(true) { // 무한대 실행
			System.out.println("input ) ");
			String cmd = sc.nextLine(); // string 타입의 명령어 // line : 입력된 모든 문자가 입력됨.
//		String cmd = sc.next(); 띄어쓰기 못씀.

			System.out.println("command : " + cmd);
			
			if(cmd.equals("system exit")) {
				break;
			}
		}
		
		
		System.out.println("==program ends==");
		
		sc.close();
	}

}

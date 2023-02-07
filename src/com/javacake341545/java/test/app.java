package com.javacake341545.java.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class app {
	public static List<Article> articles;
	
	
	List<User> users = new ArrayList<>();
	

	static {
		articles = new ArrayList<>();
	}
	
	static int lastArticleId = 0;

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호    /      제목     /     조회    ");
				String tempTitle = null;
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 4) {
						tempTitle = article.title.substring(0, 4);
						System.out.printf("%4d	/    %6s    /   %4d\n", article.id, tempTitle + "...", article.hit);
						continue;
					}

					System.out.printf("%4d	/    %6s    /   %4d\n", article.id, article.title, article.hit);
				}
			} else if (command.equals("article write")) {
				int id = articles.size() + 1;
				System.out.printf("제목 : ");
				String regDate = Util.getNowDateStr();
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성 되었습니다\n", id);
			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				foundArticle.increaseHit();
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String updateDate = Util.getNowDateStr();

				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.updateDate = updateDate;

				System.out.printf("%d번 게시물을 수정했습니다\n", id);

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물을 삭제했습니다\n", id);

			} else if (command.equals("member join")) {
				while (true) {
					int user_id = users.size() + 1;
					System.out.printf("loginID : ");
					String regDate = Util.getNowDateStr();
					String loginID = sc.nextLine();
					System.out.printf("password : ");
					String password = sc.nextLine();
					if (password.length() < 8){
						System.out.println("비밀번호가 너무 짧습니다.");
						continue;
					}
					else {
						System.out.printf("rewrite_password : ");
						String rewrite_password = sc.nextLine();
						if (password.equals(rewrite_password) == false) {
							System.out.println("비밀번호가 일치하지 않습니다.");
							continue;
						}
						else if (password.equals(rewrite_password) == true) { 
							System.out.printf("name : ");
							String name = sc.nextLine();
	
							User user = new User(loginID, password, regDate, regDate, name);
							users.add(user);
	
							System.out.println(user_id);
							
							continue;
						}
					}
				}
				
			} else if (command.equals("member list")) {
				if (users.size() == 0) {
					System.out.println("가입자가 없습니다");
					continue;
				}
				System.out.println("   id    /     loginID     /     join date    ");
				for (int i = users.size() - 1; i >= 0; i--) {
					User user = users.get(i);
					System.out.printf("%3d	/    %6s  /   %4s\n", user.user_id, user.loginID, user.regDate);
				}
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}
	
	public int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Article getArticleById(int id) {
		// 1
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			if (article.id == id) {
//				return article;
//			}
//		}

		// 2
//		for (Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}
//		}
		// 3
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public static void makeTestData() { // 메인클래스에서 메소드 작동할려면 static 필요

		System.out.println("테스트 데이터 생성");
		Article article = new Article(1, Util.getNowDateStr(), Util.getNowDateStr(), "asdaaa", "asd");
		articles.add(article);
		lastArticleId += 1;
		Article article2 = new Article(2, Util.getNowDateStr(), Util.getNowDateStr(), "asaaad2", "asd2");
		articles.add(article2);
		lastArticleId += 1;
		Article article3 = new Article(3, Util.getNowDateStr(), Util.getNowDateStr(), "asaaad3", "asd3");
		articles.add(article3);
		lastArticleId += 1;

	}
}

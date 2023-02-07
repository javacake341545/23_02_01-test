package com.javacake341545.java.test;

class Article {
	int id;
	String regDate;
	String updateDate = "";
	String title;
	String body;
	int hit;

	Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = 0;
	}

	void increaseHit() {
		this.hit++;
		;
	}
}

package com.vo;

public class BookVO {
	// 검색 조건에 사용할 변수 선언
	// 사용자가 JComboBox에서 선택한 문자열을 담을 변수
	private String search = null;// 제목|저자|내용
	// 사용자가 입력한 키워드를 담을 변수
	private String keyword = null;// 사용자가 입력한 값
	private int result = 0;
	private String command = null;
	private String isbn = null;
	private String isbn2 = null;
	private String bname = null;
	private String author = null;
	private String publisher = null;
	private String pdate = null;
	private String price = null;// int
	private String stock = null;// int

	public BookVO() {

	}
	
	// select시, insert시 isbn,bname,author,publisher,pdate,price,stock
	public BookVO(String isbn, String bname, String author, String publisher, String pdate, String price,
			String stock) {
		this.isbn = isbn;
		this.bname = bname;
		this.author = author;
		this.publisher = publisher;
		this.pdate = pdate;
		this.price = price;
		this.stock = stock;
	}

	// 업데이트시
	public BookVO(String isbn, String isbn2, String bname, String author, String publisher, String pdate, String price,
			String stock) {
		this.isbn = isbn;
		this.isbn2 = isbn2;
		this.bname = bname;
		this.author = author;
		this.publisher = publisher;
		this.pdate = pdate;
		this.price = price;
		this.stock = stock;
	}
	//회원에서 대여버튼눌렀을때 불러오는 테이블 생성자  
	public BookVO(String isbn, String bname, String publisher, String stock) {
		this.isbn=isbn;
		this.bname =bname;
		this.publisher = publisher;
		this.stock = stock;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getIsbn2() {
		return isbn2;
	}

	public void setIsbn2(String isbn2) {
		this.isbn2 = isbn2;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}

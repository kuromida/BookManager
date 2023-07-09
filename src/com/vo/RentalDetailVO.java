package com.vo;

public class RentalDetailVO {
	private int  	rentno      =0;    //대출번호     (fk)        
    private int  	detailid    =0;   // 디테일아이디              
    private String  rental_now  =null;// 대출현황
    private String  isbn        =null;  //도서  isbn(fk)
  //"대출번호", "회원번호" ,"회원이름","아이디","ISBN", "도서명", "대출일자","반납예정", "반납현황"

    public RentalDetailVO(){
    	
    }
	public RentalDetailVO(int rentno, int detailid,String rental_now,String isbn){
		this.rentno = rentno;
		this.detailid = detailid;
		this.rental_now = rental_now;
		this.isbn = isbn;
		
	}



	public int getRentno() {
		return rentno;
	}



	public void setRentno(int rentno) {
		this.rentno = rentno;
	}



	public int getDetailid() {
		return detailid;
	}



	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}



	public String getRental_now() {
		return rental_now;
	}



	public void setRental_now(String rental_now) {
		this.rental_now = rental_now;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}

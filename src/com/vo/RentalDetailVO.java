package com.vo;

public class RentalDetailVO {
	private int  	rentno      =0;    //�����ȣ     (fk)        
    private int  	detailid    =0;   // �����Ͼ��̵�              
    private String  rental_now  =null;// ������Ȳ
    private String  isbn        =null;  //����  isbn(fk)
  //"�����ȣ", "ȸ����ȣ" ,"ȸ���̸�","���̵�","ISBN", "������", "��������","�ݳ�����", "�ݳ���Ȳ"

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

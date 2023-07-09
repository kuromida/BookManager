package com.project;

import java.util.List;

import com.vo.BookVO;

public class MgrBookLogic {
	
	MgrBookDao mBookDao = new MgrBookDao();
	public List<BookVO> getBookList(){
		System.out.println("MgrBookLogic-getBookListȣ�⼺��");
		List<BookVO> bookList = null;
		bookList = mBookDao.getBookList();
		return bookList;
	}
	public List<BookVO> getBookList02(String isbn){
		System.out.println("MgrBookLogic-getBookList02ȣ�⼺��");
		List<BookVO> bookList = null;
		bookList = mBookDao.getBookList02(isbn);
		return bookList;
	}
	public List<BookVO> getbookSearch(BookVO pbVO){//Search �˻����
		System.out.println("MgrBookLogic-getbookSearchȣ�⼺��");
		List<BookVO> bookList = null;
		System.out.println("BookLogic"+pbVO.getSearch());
		System.out.println("BookLogic"+pbVO.getKeyword());
		bookList = mBookDao.getbookSearch(pbVO);
		return bookList;
	}
	public int bookUpdate(String bookUpd[]) { //������Ʈ
		System.out.println("MgrBookLogic-bookUpdateȣ�⼺��");
		int result =0;
		result =mBookDao.bookUpdate(bookUpd);
		return result;
	}
	
	public int bookInsert(String bookIns[]) {//insert
		System.out.println("MgrBookLogic-bookInsertȣ�⼺��");
		int result =0;
		result =mBookDao.bookInsert(bookIns);
		return result;
	}
	
	 public int bookDelete(String isbn) {//delete
			
		 System.out.println("MgrBookLogic - BookDeleteȣ�� ����");
		 int result =0;
		 result = mBookDao.bookDelete(isbn);
		 return result;
	 }

}


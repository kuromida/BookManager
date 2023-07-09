package com.project;

import java.util.List;

import com.vo.BookVO;

public class MgrBookLogic {
	
	MgrBookDao mBookDao = new MgrBookDao();
	public List<BookVO> getBookList(){
		System.out.println("MgrBookLogic-getBookList호출성공");
		List<BookVO> bookList = null;
		bookList = mBookDao.getBookList();
		return bookList;
	}
	public List<BookVO> getBookList02(String isbn){
		System.out.println("MgrBookLogic-getBookList02호출성공");
		List<BookVO> bookList = null;
		bookList = mBookDao.getBookList02(isbn);
		return bookList;
	}
	public List<BookVO> getbookSearch(BookVO pbVO){//Search 검색기능
		System.out.println("MgrBookLogic-getbookSearch호출성공");
		List<BookVO> bookList = null;
		System.out.println("BookLogic"+pbVO.getSearch());
		System.out.println("BookLogic"+pbVO.getKeyword());
		bookList = mBookDao.getbookSearch(pbVO);
		return bookList;
	}
	public int bookUpdate(String bookUpd[]) { //업데이트
		System.out.println("MgrBookLogic-bookUpdate호출성공");
		int result =0;
		result =mBookDao.bookUpdate(bookUpd);
		return result;
	}
	
	public int bookInsert(String bookIns[]) {//insert
		System.out.println("MgrBookLogic-bookInsert호출성공");
		int result =0;
		result =mBookDao.bookInsert(bookIns);
		return result;
	}
	
	 public int bookDelete(String isbn) {//delete
			
		 System.out.println("MgrBookLogic - BookDelete호출 성공");
		 int result =0;
		 result = mBookDao.bookDelete(isbn);
		 return result;
	 }

}


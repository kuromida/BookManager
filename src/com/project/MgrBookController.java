package com.project;

import java.util.List;

import com.vo.BookVO;

public class MgrBookController {
	public final String _DET = "detail";
	public final String _INS = "insert";
	public final String _UPD ="update";
	public final String _DEL = "delete";
	public final String _ALL = "select";
	
	MgrBookLogic mbookLogic = new MgrBookLogic();
	
	public String[] getBookData(BookVO pbvo) {//BookVO占쏙옙 占쏙옙占쏙옙占� 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 
		
		String[] bookInfo =new String[7];
		int i=0;
		bookInfo[i++]=pbvo.getIsbn();
		bookInfo[i++]=pbvo.getBname();
		bookInfo[i++]=pbvo.getAuthor();
		bookInfo[i++]=pbvo.getPublisher();
		bookInfo[i++]=pbvo.getPdate();
		bookInfo[i++]=pbvo.getPrice();
		bookInfo[i++]=pbvo.getStock();
		return bookInfo;
	}
	
	public BookVO send(BookVO pbVO) {
		BookVO rbVO = new BookVO();
		
		if(_INS.equals(pbVO.getCommand())) {//占쌉뤄옙占쏙옙
			int result =0;
			String bookInfo [] = getBookData(pbVO);
			result = mbookLogic.bookInsert(bookInfo);
			rbVO.setResult(result);
			
		}
		else if(_UPD.equals(pbVO.getCommand())) {//占쏙옙占쏙옙占쌀띰옙 
			int result =0;
			String[] bookInfo =new String[8];
			int i=0;
			bookInfo[i++]=pbVO.getIsbn();
			bookInfo[i++]=pbVO.getIsbn2();
			bookInfo[i++]=pbVO.getBname();
			bookInfo[i++]=pbVO.getAuthor();
			bookInfo[i++]=pbVO.getPublisher();
			bookInfo[i++]=pbVO.getPdate();
			bookInfo[i++]=pbVO.getPrice();
			bookInfo[i++]=pbVO.getStock();
			
			result =mbookLogic.bookUpdate(bookInfo);
			rbVO.setResult(result);
		}else if(_DEL.equals(pbVO.getCommand())) {//占쏙옙占쏙옙占쏙옙
			int result =0;
			result = mbookLogic.bookDelete(pbVO.getIsbn());
			rbVO.setResult(result);
			
		}
		return rbVO;
		
	}
	
	/*
	 * 검색기능 처리구현
	 */
	public List<BookVO> sendSearch(BookVO pbVO) {
		System.out.println("BookController sendSearch 호출성공");
		List<BookVO> bookList = null;
		System.out.println("BookController"+pbVO.getSearch());
		System.out.println("BookController"+pbVO.getKeyword());
		 bookList= mbookLogic.getbookSearch(pbVO);
		return bookList;
	}


	public List<BookVO> sendALL02(String isbn) {
		System.out.println("BookController sendALL02호출성공");
		List<BookVO> bookList = null;
		 bookList= mbookLogic.getBookList02(isbn);
		return bookList;
	}
	
	/*
	 * 占쏙옙체 占쏙옙회 처占쏙옙 占쏙옙占쏙옙
	 */
	public List<BookVO> sendALL() {
		System.out.println("BookController sendALL호출성공");
		List<BookVO> bookList = null;
		 bookList= mbookLogic.getBookList();
		return bookList;
	}

	public BookVO send02(BookVO pbvo) {
		
		
		return null;
	}


}

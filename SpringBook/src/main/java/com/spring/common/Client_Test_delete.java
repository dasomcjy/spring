package com.spring.common;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.board.BoardDTO;
import com.spring.board.BoardService;

public class Client_Test_delete {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// ������ �����̳ʷ� ���� Bean �� ȣ�� : BoardService : �������̽�
		BoardService boardService =(BoardService) factory.getBean("boardService");
		
		// DTO ��ü�� �����Ŀ� Setter �������� DTO �� �ʵ��� ���� �Է�
		BoardDTO boardDTO = new BoardDTO();
		
		//deleteBoard() �޼ҵ� �׽�Ʈ 
		
		//DTO�� seq �÷��� ���� �Ҵ��� deleteBoard() �޼ҵ� ȣ��
		boardDTO.setSeq(2);
		
		//deleteBoard(boardDTO)
		boardService.deleteBoard(boardDTO);
		

	}

}
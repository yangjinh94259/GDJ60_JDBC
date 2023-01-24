package com.iu.main.locations;

import java.util.ArrayList;
import java.util.Scanner;

public class LocationController {
	private Scanner sc;
	private LocationDAO locationDAO;
	private LocationView locationView;
	private LocationInput locationInput;
	
	public LocationController() {
		this.sc = new Scanner(System.in);
		this.locationDAO = new LocationDAO();
		this.locationView = new LocationView();
		this.locationInput = new LocationInput();
	}
	
	public void start() throws Exception{
		boolean check = true;
		LocationDTO locationDTO = null;
		ArrayList<LocationDTO> ar = null;
		
		while(check) {
			System.out.println("1. 지역 리스트");
			System.out.println("2. 지역 상세 정보");
			System.out.println("3. 지역 검색");
			System.out.println("4. 지역 추가");
			System.out.println("5. 지역 삭제");
			System.out.println("6. 지역 수정");
			System.out.println("7. 종료");
			int select = sc.nextInt();
			
			switch(select) {
			case 1:
				ar = locationDAO.getList();
				locationView.view(ar);
				break;
			case 2:
				System.out.println("지역 번호 입력");
				select = sc.nextInt();
				locationDTO = locationDAO.getDetail(select);
				
				if(locationDTO != null) {
					locationView.view(locationDTO);
				}
				else {
					locationView.view("DATA 없음");
				}
				break;
			case 3:
				System.out.println("검색할 주소 입력");
				String add = sc.next();
				ar = locationDAO.getFind(add);
				break;
			case 4:
				locationDTO = locationInput.setData();
				select = locationDAO.setData(locationDTO);
				
				if(select > 0) {
					locationView.view("추가 성공");
				}
				else {
					locationView.view("추가 실패");
				}
				break;
			case 5:
				locationDTO = locationInput.deleteData();
				select = locationDAO.deleteData(locationDTO);
				String msg = "삭제 실패";
				
				if(select > 0) {
					msg = "삭제 성공";
				}
				locationView.view(msg);
				break;
			case 6:
				locationDTO = locationInput.updateData();
				select = locationDAO.updateData(locationDTO);
				
				if(select > 0) {
					locationView.view("수정 성공");
				}
				else {
					System.out.println("수정 실패");
				}
				break;
			default:
				check = false;
			}
		}
	}
	
	
}

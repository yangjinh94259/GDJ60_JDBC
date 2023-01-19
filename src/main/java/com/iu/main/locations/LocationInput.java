package com.iu.main.locations;

import java.util.Scanner;

public class LocationInput {
	private Scanner sc;
	
	public LocationInput() {
		sc = new Scanner(System.in);
	}
	
	//추가
	public LocationDTO setData() {
		LocationDTO locationDTO = new LocationDTO();
		System.out.println("지역번호 입력");
		locationDTO.setLocation_id(sc.nextInt());
		System.out.println("우편번호 입력");
		locationDTO.setPostal_code(sc.next());
		System.out.println("도시 입력");
		locationDTO.setCity(sc.next());
		
		return locationDTO;
	}
	
	//삭제
	public LocationDTO deleteData() {
		LocationDTO locationDTO = new LocationDTO();
		System.out.println("삭제할 지역 번호 입력");
		locationDTO.setLocation_id(sc.nextInt());
		return locationDTO;
	}
	
}

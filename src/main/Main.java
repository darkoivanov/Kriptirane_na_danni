package main;

import java.util.InputMismatchException;//za try catch greshkite pri vyvejdane nanekorektni danni
import java.util.Scanner;
import database.Sql;
import helper.MD5;

public class Main {

	public static void main(String[] args) {
		System.out.println("Izberi Opciq ot dolu");
		System.out.println("1 - API Nivo na kriptirane");
		System.out.println("0 - Restartirai proekta i bazadata danni");
		System.out.print("Izbor: ");
		
		int option = 0;
		Scanner scanner = new Scanner(System.in);//syzdava nov Skener za da moje da chete dannite 
		
		try {//opitva da chete sledvashtoto chislo ot konzolata ako ,vyznikne greshka se pokazva syobshtenie
			option = scanner.nextInt();//chete sledvashto INT chislo
		} catch (InputMismatchException e) {// ako e vaveden greshen vhod kato cifra vmesto bukva
			System.out.println("molq Vyvedi validni danni");// pishi na kozolata
		}
		
		switch (option) {//switch za menu podoben na if else.....
			case 1:
				System.out.println("Izberi operaciqta");
				System.out.println("1 - Vyvedi  danni za kriptirane");
				System.out.println("2 - Proveri dali sashtestuvat dannite");
				System.out.print("Izbor: ");
				int operation = 0;
				
				try {
					operation = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("molq Vyvedi validni danni");
				}
				
				switch (operation) {
					case 1:
						System.out.print("Vyvedi novi danni: ");
						String input = "";
						while(scanner.hasNextLine()) { // chete dokato ne e prazen reda = kraqt
							input = scanner.nextLine();
							
							if(!input.isEmpty()) {
								break;
							}
						}
						
						String data = null;
						try {
							data = MD5.Encrypt(input);//izpolzva MD5 za kriptiraneto s API
						} catch (Exception e) {
							System.out.println("Dannite ne mogat da bydat kriptirani zaradi problem.");
						}
						
						Sql sql = new Sql();//za bazata danni 
						boolean IS_INSERT = sql.InsertEncryptedData(data);//proverqva dali sa vyvedeni dannite uspeshno
						if(IS_INSERT) {
							System.out.println("Dannite sa vyvedeni uspeshno");
						}else {
							System.out.println("GRESHKA pri vyvejdane na dannite");
						}
						break;
				
				  
			default:
				System.out.println("Molq vavedete validen izbor ");
		}
		}
	}
	}

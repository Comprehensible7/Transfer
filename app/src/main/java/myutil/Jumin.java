package myutil;

import java.util.Calendar;

public class Jumin /* extends Object */ {

	private String jumin_no;
	String [] tti_array = {"원숭이","닭","개","돼지","쥐","소","호랑이","토끼","용","뱀","말","양"};


	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}

	//주민번호
	//            01234567890123  <= index
	//jumin_no = "901212-1234567";


	/*
	 주민번호 성별
	             내국인     외국인
	             남  여     남  여
	  1900       1   2      5   6
	  2000       3   4      7   8

	 */


	//출생년도
	public int getYear() {
		//  substring(시작index, 시작+갯수);
		String str_year = jumin_no.substring(0,0+2);//"90"
		//String -> int로 변환: Integer.parseInt("90")->90
		int year = Integer.parseInt(str_year);//"90"=> 90

		//성별정보 추출
		char gender = jumin_no.charAt(7);// '1'
		if(gender=='1' || gender=='2' || gender=='5' || gender=='6')
			year = year + 1900;
		else
			year = year + 2000;

		return year;
	}


	//나이 구하기
	public int getAge() {

		// 나이 = 현재년도 - 출생년도 + 1
		// 올해년도 구해서 2019대신 넣어야된다
		Calendar c = Calendar.getInstance();
		//          현재년도
		int age= c.get(Calendar.YEAR) - this.getYear() + 1;

		return age;
	}



	public String getTti() {

		//띠구하기 :  출생년도%12 => 0~11 <= tti_index
		// 4  5  6  7  8  9  10 11 0  1  2  3 <=출생년도%12
		// 자 축 인 묘 진 사 오 미 신 유 술 해
		// 쥐 소 범 토 용 뱀 말 양 원 닭 개 돼
		int tti_index = this.getYear()%12;

		return tti_array[tti_index];
	}

	//주민번호
	//            01234567890123  <= index
	//jumin_no = "901212-1234567";
	public String getLocal() {

		String str_local = jumin_no.substring(8, 8+2);
		//String->int
		int local_code = Integer.parseInt(str_local);
		if(local_code>=0 && local_code<=8) return "서울";
		else if(local_code>=9 && local_code<=12) return "부산";
		else if(local_code>=13 && local_code<=15) return "인천";
		else if(local_code>=16 && local_code<=25) return "경기도";
		else if(local_code>=26 && local_code<=34) return "강원도";
		else if(local_code>=35 && local_code<=39) return "충청북도";
		else if(local_code==40) return "대전";
		else if(local_code>=41 && local_code<=47) return "충청남도";
		else if(local_code==44 || local_code==49) return "세종시";
		else if(local_code>=48 && local_code<=54) return "전라북도";
		else if(local_code>=55 && local_code<=56) return "광주";
		else if(local_code>=57 && local_code<=64) return "전라남도";
		else if(local_code>=70 && local_code<=75
				|| local_code>=77 && local_code<=81
		) return "경상북도";
		else if(local_code>=82 && local_code<=84
				|| local_code>=86 && local_code<=92
		) return "경상북도";
		else if(local_code==85) return "울산";
		else if(local_code>=93 && local_code<=95) return "경상북도";

		return "기타";
	}

	//태어난 계절
	public String getSeason() {
		String str_month = jumin_no.substring(2, 2+2);
		int    month = Integer.parseInt(str_month);//"12"->12

		switch(month/3)
		{
			case 1: return "봄";   // 3  4  5
			case 2: return "여름"; // 6  7  8
			case 3: return "가을"; // 9 10 11
		}

		return "겨울";
	}

	//성별
	public String getGender() {

		char gender = jumin_no.charAt(7);
		if(gender=='1' || gender=='3' || gender=='5' || gender=='7')
			return "남자";

		return "여자";
	}



	//60갑자(10간12지)
	public String getGanji() {
		//                0 1 2 3 4 5 6 7 8 9      <=gan_index
		String gan_str = "경신임계갑을병정무기";
		//                0 1 2 3 4 5 6 7 8 9 1011 <=ji_index
		String ji_str  = "신유술해자축인묘진사오미";

		int gan_index = this.getYear()%10;//0~9
		int ji_index  = this.getYear()%12;//0~11

		char gan = gan_str.charAt(gan_index);
		char ji  = ji_str.charAt(ji_index);

		return String.format("%c%c년", gan,ji);
	}

	//주민번호 유효성
	public boolean isValid() {
		// 01234567890123
		// 801212-1234560
		// 2345670892345

		int []  check = {2,3,4,5,6,7,0,8,9,2,3,4,5};

		//문자열=>숫자 : Integer.parseInt("1")
		//문자  =>숫자 : '1' - '0'
		//               49  - 48 => 1
		//               '0' : 48  '1' : 49  '2' : 50
		int sum = 0 ;

		for(int i=0;i<=12;i++) {
			sum = sum + (jumin_no.charAt(i)-'0')*check[i];
		}
		//계산결과값
		int result_no = (11-(sum%11))%10;

		//주민번호 마지막 숫자
		int last_no = jumin_no.charAt(13) - '0';

		//System.out.println(result_no);

		return (result_no==last_no);
	}

}





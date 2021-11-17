package quiz;

import country.CountryDAO;
import country.CountryDTO;

import java.util.Random;

public class LocationQuizQuery implements QuizQuery{

    CountryDAO dao = CountryDAO.getInstance();

    @Override
    public String getQuestion(int id) {
        // TODO Auto-generated method stub
        CountryDTO country = dao.getCountryByID(id);
        return country.getName() + "의 위치는?";
    }

    @Override
    public String[] getItems(int id) {
        // TODO Auto-generated method stub
        String[] items = new String[5];

        //정답은  items[0]에 넣는다.
        CountryDTO country = dao.getCountryByID(id);
        items[0] = country.getLocation();

        //나머지 4개 보기에 대한 처리 (랜덤한 보기를 출력)
        Random random = new Random();
        int randomId[] = new int[4];

        for(int i =0; i<4; i++) {
            //총 row(국가) 수
            randomId[i] = random.nextInt(226)+1;

            //중복 제거
            for(int j=0; j<i; j++) {
                if(randomId[i] == randomId[j]|| randomId[i]==id) {
                    i--;
                }
            }
            //해당 id의 위치를 item에 담아서 전달
            country = dao.getCountryByID(randomId[i]);
            items[i+1] = country.getLocation();
        }

        return items;

    }

}

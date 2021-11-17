package data;

import java.util.ArrayList;

import country.CountryDAO;
import quiz.QuizDAO;

public class OdapCountryTableData extends CountryTableModel {

    public OdapCountryTableData(String memberId) {
        super(memberId);
    }

    @Override
    public void getData() {
        list = new ArrayList<>();
        QuizDAO quizDao = QuizDAO.getInstance();
        int incorrectQuiz[] = new int[10];
        incorrectQuiz = quizDao.getIncorrectQuiz(memberId);
        CountryDAO countryDao = CountryDAO.getInstance();
        for (int i = 0; i < 10; i++) {
            if (incorrectQuiz[i] != 0) {
                list.add(countryDao.getCountryByID(incorrectQuiz[i]));
            }
        }
    }

}

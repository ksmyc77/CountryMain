package quiz;

public class QuizDTO {
    private String member_id;
    private int quiz1;		//퀴즈1
    private int quiz2;		//퀴즈2
    private int quiz3;		//퀴즈3
    private int quiz4;		//퀴즈4
    private int quiz5;		//퀴즈5
    private int quiz6;		//퀴즈6
    private int quiz7;		//퀴즈7
    private int quiz8;		//퀴즈8
    private int quiz9;		//퀴즈9
    private int quiz10;		//퀴즈10



    public QuizDTO(String membe_id, int quiz1, int quiz2, int quiz3, int quiz4, int quiz5, int quiz6, int quiz7, int quiz8,
                   int quiz9, int quiz10) {
        super();
        this.member_id = membe_id;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.quiz5 = quiz5;
        this.quiz6 = quiz6;
        this.quiz7 = quiz7;
        this.quiz8 = quiz8;
        this.quiz9 = quiz9;
        this.quiz10 = quiz10;
    }

    public String getMember_id() {
        return member_id;
    }
    public void setMember_id(String membe_id) {
        this.member_id = membe_id;
    }
    public int getQuiz1() {
        return quiz1;
    }
    public void setQuiz1(int quiz1) {
        this.quiz1 = quiz1;
    }
    public int getQuiz2() {
        return quiz2;
    }
    public void setQuiz2(int quiz2) {
        this.quiz2 = quiz2;
    }
    public int getQuiz3() {
        return quiz3;
    }
    public void setQuiz3(int quiz3) {
        this.quiz3 = quiz3;
    }
    public int getQuiz4() {
        return quiz4;
    }
    public void setQuiz4(int quiz4) {
        this.quiz4 = quiz4;
    }
    public int getQuiz5() {
        return quiz5;
    }
    public void setQuiz5(int quiz5) {
        this.quiz5 = quiz5;
    }
    public int getQuiz6() {
        return quiz6;
    }
    public void setQuiz6(int quiz6) {
        this.quiz6 = quiz6;
    }
    public int getQuiz7() {
        return quiz7;
    }
    public void setQuiz7(int quiz7) {
        this.quiz7 = quiz7;
    }
    public int getQuiz8() {
        return quiz8;
    }
    public void setQuiz8(int quiz8) {
        this.quiz8 = quiz8;
    }
    public int getQuiz9() {
        return quiz9;
    }
    public void setQuiz9(int quiz9) {
        this.quiz9 = quiz9;
    }
    public int getQuiz10() {
        return quiz10;
    }
    public void setQuiz10(int quiz10) {
        this.quiz10 = quiz10;
    }



}

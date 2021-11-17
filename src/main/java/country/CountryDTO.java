package country;

public class CountryDTO {
    private int id;
    private String name;		//국가이름
    private String code;		//국가코드
    private String capital;     //수도
    private String religion;	//종교
    private String mainCity;	//주요도시
    private String weather;		//기후
    private String location;	//위치
    private String race;		//인종
    private String media;		//언론
    private String area;			//면적
    private String areaSource; 	//면적 출처
    private String areaExplain; //면적 설명
    private String language;	//언어
    private int baseYear;		//기준년도

    public CountryDTO(int id, String name, String code, String capital, String weather, String location,
                      String mainCity, String religion, String race, String media, String area, String areaSource, String areaExplain, String language, int baseYear) {

        this.id = id;
        this.name = name;
        this.code = code;
        this.capital = capital;
        this.religion = religion;
        this.mainCity = mainCity;
        this.weather = weather;
        this.location = location;
        this.race = race;
        this.media = media;
        this.area = area;
        this.areaSource = areaSource;
        this.areaExplain = areaExplain;
        this.language = language;
        this.baseYear = baseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMainCity() {
        return mainCity;
    }

    public void setMainCity(String mainCity) {
        this.mainCity = mainCity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaSource() {
        return areaSource;
    }

    public void setAreaSource(String areaSource) {
        this.areaSource = areaSource;
    }

    public String getAreaExplain() {
        return areaExplain;
    }

    public void setAreaExplain(String areaExplain) {
        this.areaExplain = areaExplain;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getBaseYear() {
        return baseYear;
    }

    public void setBaseYear(int baseYear) {
        this.baseYear = baseYear;
    }
}

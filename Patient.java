public class Patient {

    private  String name;
    private  int age;
    private  long time;
    private  String gender;
    private  String Complaint;
    private  String AlertLevel;
    private  String HeartRate;
    private  String BloodPresh;
    private  String RespRate;
    private  String temp;
    private  String oxysat;
    private  String pein;
    private  String medeykayshuns;
    private  int triageLevel = 0;
    private  String Doc;




    public Patient(String name, int age , String gender, String Complaint, String AlertLevel, String HeartRate, String BloodPresh, String RespRate, String temp, String oxysat, String pein, String medeykayshuns) {
        this.name = name;

        this.age= age;
        this.gender = gender;
        this.Complaint = Complaint;
        this.AlertLevel = AlertLevel;
        this.HeartRate = HeartRate;
        this.BloodPresh = BloodPresh;
        this.RespRate = RespRate;
        this.temp = temp;
        this.oxysat = oxysat;
        this.pein = pein;
        this.medeykayshuns = medeykayshuns;


    }

    public String getDoc() {
        return Doc;
    }

    public void setDoc(String doc) {
        Doc = doc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setComplaint(String complaint) {
        Complaint = complaint;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAlertLevel(String alertLevel) {
        AlertLevel = alertLevel;
    }

    public void setHeartRate(String heartRate) {
        HeartRate = heartRate;
    }

    public void setBloodPresh(String bloodPresh) {
        BloodPresh = bloodPresh;
    }

    public void setRespRate(String respRate) {
        RespRate = respRate;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setOxysat(String oxysat) {
        this.oxysat = oxysat;
    }

    public void setPein(String pein) {
        this.pein = pein;
    }

    public void setMedeykayshuns(String medeykayshuns) {
        this.medeykayshuns = medeykayshuns;
    }

    public int getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(int triageLevel) {
        this.triageLevel = triageLevel;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getComplaint() {
        return Complaint;
    }

    public String getAlertLevel() {
        return AlertLevel;
    }

    public String getHeartRate() {
        return HeartRate;
    }

    public String getBloodPresh() {
        return BloodPresh;
    }

    public String getRespRate() {
        return RespRate;
    }

    public String getTemp() {
        return temp;
    }

    public String getOxysat() {
        return oxysat;
    }

    public String getPein() {
        return pein;
    }

    public String getMedeykayshuns() {
        return medeykayshuns;
    }





 public String toString() {
        return name +"\n" + age + ", " + gender + ", " + Complaint + "\n" + triageLevel + "\n" + Doc + "\n" + time + "\n" + HeartRate + "\n" + BloodPresh + "\n" + RespRate + "\n" + temp + "\n" + oxysat + "\n" + medeykayshuns + "\n";

    }

}

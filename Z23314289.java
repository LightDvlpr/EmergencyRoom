import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Z23314289 {


    public static void main(String[] args) {



        TextFileInput input = new TextFileInput(args[0]);
        ArrayList<Patient> list = new ArrayList<Patient>();
        String line = input.readLine();


        while(line != null){

            String[] parts = line.split(",");

            Patient temp = new Patient(parts[0].trim(), Integer.parseInt(parts[1].trim()), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(), parts[8].trim(), parts[9].trim(), parts[10].trim(), parts[11].trim());


            if(parts[11].contains("-")){
                list.add(temp);
                line = input.readLine();

            }
            else{
                String[] rest = Arrays.copyOfRange(parts,11,parts.length);

                String temp2 = "";

                for (String aRest : rest) {
                    temp2 = aRest + ", " + temp2;

                }

               Patient temp3 = new Patient(parts[0].trim(), Integer.parseInt(parts[1].trim()), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(), parts[8].trim(), parts[9].trim(), parts[10].trim(), temp2.substring(0, temp2.length()-2).trim());

               list.add(temp3);
               line = input.readLine();
            }

        }



        Priority(list, args);






    }



   private static void Priority(ArrayList<Patient> list, String[] args){

       ArrayList<Patient> TriageLevel1 = new ArrayList<>();
       ArrayList<Patient> TriageLevel2 = new ArrayList<>();
       ArrayList<Patient> TriageLevel3 = new ArrayList<>();




       int startTime = (int) System.nanoTime();

       for (int i = 0; i < list.size(); i++) {



           Patient temp = list.get(i);

           Patient T1 = new Patient(temp.getName(), temp.getAge(), temp.getGender(),temp.getComplaint(), temp.getAlertLevel(), temp.getHeartRate(),temp.getBloodPresh(),temp.getRespRate(),temp.getTemp(),temp.getOxysat(),temp.getPein(),temp.getMedeykayshuns());

           Integer Systolic = 0;
           Integer Diastolic = 0;


           ArrayList<String> prescrip = new ArrayList<String>();


           String[] Blood = temp.getBloodPresh().split("/");

           Systolic = Integer.parseInt(Blood[0]);
           Diastolic = Integer.parseInt(Blood[1]);


           if (((Integer.parseInt(temp.getHeartRate()) < 30) || (Integer.parseInt(temp.getHeartRate()) > 150)) || (Double.parseDouble(temp.getTemp()) > 105) || (Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 90) || (Integer.parseInt(temp.getRespRate()) < 6) || (temp.getAlertLevel().contains("U")) || ((Systolic < 90) || (Diastolic < 60))) {


               T1 = new Patient(temp.getName(), temp.getAge(), temp.getGender(), temp.getComplaint(), temp.getAlertLevel(), temp.getHeartRate(), temp.getBloodPresh(), temp.getRespRate(), temp.getTemp(), temp.getOxysat(), temp.getPein(), temp.getMedeykayshuns());

               T1.setTriageLevel(1);




               if(temp.getMedeykayshuns().contains("-")){

                       T1.setDoc("N/A");

               }


               else if (!(temp.getMedeykayshuns().contains("-"))) {

                   String[] Medicine = temp.getMedeykayshuns().split(", ");

                   prescrip.addAll(Arrays.asList(Medicine));


                   File file = new File(args[1]);
                   File file2 = new File(args[2]);
                   File file3 = new File(args[3]);

                   int doc = 0;
                   try {
                       Scanner scanner = new Scanner(file);

                       //now read the file line by line...
                       int lineNum = 0;
                       while (scanner.hasNextLine()) {
                           String line = scanner.nextLine();
                           lineNum++;

                           for (String aPrescrip : prescrip) {

                               if (line.contains(aPrescrip)) {
                                   doc++;

                               }
                           }

                       }
                   } catch (FileNotFoundException e) {
                       //handle this
                   }
                   if (doc > 0) {
                       T1.setDoc("Cardiologist");
                   } else if (doc == 0) {
                       try {
                           Scanner scanner = new Scanner(file3);

                           //now read the file line by line...
                           int lineNum = 0;
                           while (scanner.hasNextLine()) {
                               String line = scanner.nextLine();
                               lineNum++;

                               for (String aPrescrip : prescrip) {

                                   if (line.contains(aPrescrip)) {
                                       doc++;

                                   }
                               }

                           }
                       } catch (FileNotFoundException e) {
                           //handle this
                       }
                       if (doc > 0) {
                           T1.setDoc("Neurologist");
                       } else if (doc == 0) {

                           try {
                               Scanner scanner = new Scanner(file2);

                               //now read the file line by line...

                               int lineNum = 0;
                               while (scanner.hasNextLine()) {
                                   String line = scanner.nextLine();
                                   lineNum++;

                                   for (String aPrescrip : prescrip) {

                                       if (line.contains(aPrescrip)) {
                                           doc++;

                                       }
                                   }

                               }
                           } catch (FileNotFoundException e) {
                               //handle this
                           }
                           if (doc > 0) {
                               T1.setDoc("Oncologist");
                           }
                       }



                   }


               }




               T1.setDoc(T1.getDoc());
               if(Integer.parseInt(T1.getHeartRate()) < 60){
                   T1.setHeartRate((T1.getHeartRate() + " Bradycardia"));
               } else if(Integer.parseInt(T1.getHeartRate()) > 100){
                   T1.setHeartRate(T1.getHeartRate() + " Tachycardia");
               } else{
                   T1.setHeartRate(T1.getHeartRate() + " Normal");
               }

               if((Systolic > 140) || (Diastolic >90)){
                   T1.setBloodPresh(T1.getBloodPresh() + " Hypertension");
               } else if((Systolic < 90) || (Diastolic <60)){
                   T1.setBloodPresh((T1.getBloodPresh() + " Hypotension"));
               } else {
                   T1.setBloodPresh(T1.getBloodPresh() + " Normal");
               }

               if(Integer.parseInt(T1.getRespRate()) < 16){
                   T1.setRespRate(T1.getRespRate() + " Bradypnea");
               } else if(Integer.parseInt(T1.getRespRate()) > 20){
                   T1.setRespRate((T1.getRespRate() + " Tachypnea"));
               } else{
                   T1.setRespRate((T1.getRespRate() + " Normal"));
               }

               if(Double.parseDouble(T1.getTemp()) > 99){
                   T1.setTemp(T1.getTemp() + " Yes");
               } else if((Double.parseDouble(T1.getTemp()) <99) && (Double.parseDouble(T1.getTemp()) > 95)){
                   T1.setTemp(T1.getTemp() + " No");
               }


               if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 95)){
                   T1.setOxysat(T1.getOxysat() + " Low");
               } else if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) > 95) && (Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 100)){
                   T1.setOxysat((T1.getOxysat() + " Normal"));
               }


               T1.setMedeykayshuns(T1.getMedeykayshuns());


               int endTime = (int) System.nanoTime();

               int duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.

               T1.setTime(duration);




               TriageLevel1.add(T1);



           }


            else if (Integer.parseInt(temp.getHeartRate()) < 60 || Integer.parseInt(temp.getHeartRate()) > 100 || Integer.parseInt(temp.getOxysat().replace("%", "").trim()) > 90 && Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 95 || Integer.parseInt(temp.getRespRate()) < 16 || Integer.parseInt(temp.getRespRate()) > 20 || temp.getAlertLevel().contains("V") || temp.getAlertLevel().contains("P") || Systolic > 140 || Diastolic > 90){



               T1 = new Patient(temp.getName(), temp.getAge(), temp.getGender(), temp.getComplaint(), temp.getAlertLevel(), temp.getHeartRate(), temp.getBloodPresh(), temp.getRespRate(), temp.getTemp(), temp.getOxysat(), temp.getPein(), temp.getMedeykayshuns());

               T1.setTriageLevel(2);



               if(temp.getMedeykayshuns().contains("-")){

                   T1.setDoc("N/A");

               }


               else if (!(temp.getMedeykayshuns().contains("-"))) {

                   String[] Medicine = temp.getMedeykayshuns().split(", ");

                   prescrip.addAll(Arrays.asList(Medicine));


                   File file = new File(args[1]);
                   File file2 = new File(args[2]);
                   File file3 = new File(args[3]);

                   int doc = 0;
                   try {
                       Scanner scanner = new Scanner(file);

                       //now read the file line by line...
                       int lineNum = 0;
                       while (scanner.hasNextLine()) {
                           String line = scanner.nextLine();
                           lineNum++;

                           for (String aPrescrip : prescrip) {

                               if (line.contains(aPrescrip)) {
                                   doc++;

                               }
                           }

                       }
                   } catch (FileNotFoundException e) {
                       //handle this
                   }
                   if (doc > 0) {
                       T1.setDoc("Cardiologist");
                   } else if (doc == 0) {
                       try {
                           Scanner scanner = new Scanner(file3);

                           //now read the file line by line...
                           int lineNum = 0;
                           while (scanner.hasNextLine()) {
                               String line = scanner.nextLine();
                               lineNum++;

                               for (String aPrescrip : prescrip) {

                                   if (line.contains(aPrescrip)) {
                                       doc++;

                                   }
                               }

                           }
                       } catch (FileNotFoundException e) {
                           //handle this
                       }
                       if (doc > 0) {
                           T1.setDoc("Neurologist");
                       } else if (doc == 0) {

                           try {
                               Scanner scanner = new Scanner(file2);

                               //now read the file line by line...

                               int lineNum = 0;
                               while (scanner.hasNextLine()) {
                                   String line = scanner.nextLine();
                                   lineNum++;

                                   for (String aPrescrip : prescrip) {

                                       if (line.contains(aPrescrip)) {
                                           doc++;

                                       }
                                   }

                               }
                           } catch (FileNotFoundException e) {
                               //handle this
                           }
                           if (doc > 0) {
                               T1.setDoc("Oncologist");
                           }
                       }



                   }


               }


               if(Integer.parseInt(T1.getHeartRate()) < 60){
                   T1.setHeartRate((T1.getHeartRate() + " Bradycardia"));
               } else if(Integer.parseInt(T1.getHeartRate()) > 100){
                   T1.setHeartRate(T1.getHeartRate() + " Tachycardia");
               } else{
                   T1.setHeartRate(T1.getHeartRate() + " Normal");
               }

               if((Systolic > 140) || (Diastolic >90)){
                   T1.setBloodPresh(T1.getBloodPresh() + " Hypertension");
               } else if((Systolic < 90) || (Diastolic <60)){
                   T1.setBloodPresh((T1.getBloodPresh() + " Hypotension"));
               } else {
                   T1.setBloodPresh(T1.getBloodPresh() + " Normal");
               }

               if(Integer.parseInt(T1.getRespRate()) < 16){
                   T1.setRespRate(T1.getRespRate() + " Bradypnea");
               } else if(Integer.parseInt(T1.getRespRate()) > 20){
                   T1.setRespRate((T1.getRespRate() + " Tachypnea"));
               } else{
                   T1.setRespRate((T1.getRespRate() + " Normal"));
               }

               if(Double.parseDouble(T1.getTemp()) > 99){
                   T1.setTemp(T1.getTemp() + " Yes");
               } else if((Double.parseDouble(T1.getTemp()) <99) && (Double.parseDouble(T1.getTemp()) > 95)){
                   T1.setTemp(T1.getTemp() + " No");
               }


               if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 95)){
                   T1.setOxysat(T1.getOxysat() + " Low");
               } else if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) > 95) && (Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 100)){
                   T1.setOxysat((T1.getOxysat() + " Normal"));
               }

               T1.setMedeykayshuns(T1.getMedeykayshuns());



               int endTime = (int) System.nanoTime();

               int duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.


               T1.setTime(duration);









               TriageLevel2.add(T1);


           } else if ((temp.getAlertLevel().contains("A")) || ((Double.parseDouble(temp.getTemp()) > 97) && (Double.parseDouble(temp.getTemp()) < 99)) || ((Integer.parseInt(temp.getHeartRate()) > 50) && (Integer.parseInt(temp.getHeartRate()) < 110)) || (((Systolic > 90) || (Systolic < 120)) && ((Diastolic > 60) || (Diastolic < 80))) || ((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) > 95) && (Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 100)) || ((Integer.parseInt(temp.getRespRate()) > 16) && (Integer.parseInt(temp.getRespRate()) < 20))) {


               T1 = new Patient(temp.getName(), temp.getAge(), temp.getGender(), temp.getComplaint(), temp.getAlertLevel(), temp.getHeartRate(), temp.getBloodPresh(), temp.getRespRate(), temp.getTemp(), temp.getOxysat(), temp.getPein(), temp.getMedeykayshuns());

               T1.setTriageLevel(3);


               if(temp.getMedeykayshuns().contains("-")){

                   T1.setDoc("N/A");

               }


               else if (!(temp.getMedeykayshuns().contains("-"))) {

                   String[] Medicine = temp.getMedeykayshuns().split(", ");

                   prescrip.addAll(Arrays.asList(Medicine));


                   File file = new File(args[1]);
                   File file2 = new File(args[2]);
                   File file3 = new File(args[3]);

                   int doc = 0;
                   try {
                       Scanner scanner = new Scanner(file);

                       //now read the file line by line...
                       int lineNum = 0;
                       while (scanner.hasNextLine()) {
                           String line = scanner.nextLine();
                           lineNum++;

                           for (String aPrescrip : prescrip) {

                               if (line.contains(aPrescrip)) {
                                   doc++;

                               }
                           }

                       }
                   } catch (FileNotFoundException e) {
                       //handle this
                   }
                   if (doc > 0) {
                       T1.setDoc("Cardiologist");
                   } else if (doc == 0) {
                       try {
                           Scanner scanner = new Scanner(file3);

                           //now read the file line by line...
                           int lineNum = 0;
                           while (scanner.hasNextLine()) {
                               String line = scanner.nextLine();
                               lineNum++;

                               for (String aPrescrip : prescrip) {

                                   if (line.contains(aPrescrip)) {
                                       doc++;

                                   }
                               }

                           }
                       } catch (FileNotFoundException e) {
                           //handle this
                       }
                       if (doc > 0) {
                           T1.setDoc("Neurologist");
                       } else if (doc == 0) {

                           try {
                               Scanner scanner = new Scanner(file2);

                               //now read the file line by line...

                               int lineNum = 0;
                               while (scanner.hasNextLine()) {
                                   String line = scanner.nextLine();
                                   lineNum++;

                                   for (String aPrescrip : prescrip) {

                                       if (line.contains(aPrescrip)) {
                                           doc++;

                                       }
                                   }

                               }
                           } catch (FileNotFoundException e) {
                               //handle this
                           }
                           if (doc > 0) {
                               T1.setDoc("Oncologist");
                           }
                       }



                   }


               }

               if(Integer.parseInt(T1.getHeartRate()) < 60){
                   T1.setHeartRate((T1.getHeartRate() + " Bradycardia"));
               } else if(Integer.parseInt(T1.getHeartRate()) > 100){
                   T1.setHeartRate(T1.getHeartRate() + " Tachycardia");
               } else{
                   T1.setHeartRate(T1.getHeartRate() + " Normal");
               }

               if((Systolic > 140) || (Diastolic >90)){
                   T1.setBloodPresh(T1.getBloodPresh() + " Hypertension");
               } else if((Systolic < 90) || (Diastolic <60)){
                   T1.setBloodPresh((T1.getBloodPresh() + " Hypotension"));
               } else {
                   T1.setBloodPresh(T1.getBloodPresh() + " Normal");
               }

               if(Integer.parseInt(T1.getRespRate()) < 16){
                   T1.setRespRate(T1.getRespRate() + " Bradypnea");
               } else if(Integer.parseInt(T1.getRespRate()) > 20){
                   T1.setRespRate((T1.getRespRate() + " Tachypnea"));
               } else{
                   T1.setRespRate((T1.getRespRate() + " Normal"));
               }

               if(Double.parseDouble(T1.getTemp()) > 99){
                   T1.setTemp(T1.getTemp() + " Yes");
               } else if((Double.parseDouble(T1.getTemp()) <99) && (Double.parseDouble(T1.getTemp()) > 95)){
                   T1.setTemp(T1.getTemp() + " No");
               }


               if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 95)){
                   T1.setOxysat(T1.getOxysat() + " Low");
               } else if((Integer.parseInt(temp.getOxysat().replace("%", "").trim()) > 95) && (Integer.parseInt(temp.getOxysat().replace("%", "").trim()) < 100)){
                   T1.setOxysat((T1.getOxysat() + " Normal"));
               }

               int endTime = (int) System.nanoTime();

               int duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.

               T1.setTime(duration);




               TriageLevel3.add(T1);


           }




       }











       List<File> l = new LinkedList<File>();
       //find how many patients and create files for them
       int num = list.size();
       for(int i = 0;i<num;i++){
           String c = Integer.toString(i+1);
           String out = c + ".txt";
           File file = new File(out);
           // creates the file
           try {
               file.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
           l.add(file);
       }

       ArrayList<Patient> complete = new ArrayList<Patient>();
       complete.addAll(TriageLevel1);
       complete.addAll(TriageLevel2);
       complete.addAll(TriageLevel3);
       int s = 0;
       FileWriter writer;
       for(File f: l){
           try{
           writer = new FileWriter(f);
           Patient p = complete.get(s);
           writer.write(p.toString());
           s++;
           writer.flush();
           writer.close();}
           catch(IOException e ){
               System.out.println(e);
           }
       }

       System.out.println("Files have been printed out");
   }



}
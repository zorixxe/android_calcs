package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    public static ArrayList<DataItem> getSampleData(ArrayList<Double> values, ArrayList<String> discs) {
        ArrayList<DataItem> dataItems = new ArrayList<>();
      //  String[] names = { "Helsingfors", "Esbo", "Tammerfors", "Vanda", "Uleåborg", "Åbo", "Jyväskylä", "Kuopio", "Lahtis", "Björneborg", "Kouvola", "Joensuu", "Villmanstrand", "Tavastehus", "Vasa", "Seinäjoki", "Rovaniemi", "S:t Michel", "Salo", "Kotka", "Borgå", "Karleby", "Hyvinge", "Lojo", "Träskända", "Raumo", "Kervo", "Kajana", "S:t Karins", "Nokia", "Ylöjärvi", "Kangasala", "Nyslott", "Riihimäki", "Raseborg", "Imatra", "Reso", "Brahestad", "Sastamala", "Torneå", "Idensalmi", "Valkeakoski", "Kurikka", "Kemi", "Varkaus", "Jämsä", "Fredrikshamn", "Nådendal", "Jakobstad", "Heinola", "Äänekoski", "Pieksämäki", "Forssa", "Ackas", "Orimattila", "Loimaa", "Nystad", "Ylivieska", "Kauhava", "Kuusamo", "Pargas", "Lovisa", "Lappo", "Kauhajoki", "Ulvsby", "Kankaanpää", "Kalajoki", "Mariehamn", "Alavo", "Pemar", "Lieksa", "Grankulla", "Nivala", "Kides", "Vittis", "Mänttä-Vilppula", "Närpes", "Keuru", "Nurmes", "Alajärvi", "Saarijärvi", "Orivesi", "Högfors", "Somero", "Letala", "Hangö", "Kuhmo", "Kiuruvesi", "Pudasjärvi", "Nykarleby", "Kemijärvi", "Oulainen", "Kumo", "Suonenjoki", "Ikalis", "Haapajärvi", "Harjavalta", "Haapavesi", "Outokumpu", "Virdois", "Kristinestad", "Parkano", "Viitasaari", "Etseri", "Kannus", "Pyhäjärvi", "Kaskö" };
        //double[] values = { 658457, 297132, 244223, 239206, 209551, 195137, 144473, 121543, 120027, 83482, 80454, 77261, 72634, 67971, 67615, 64736, 64180, 52122, 51400, 51241, 51149, 47909, 46880, 45988, 45226, 38959, 37232, 36493, 35497, 34884, 33533, 32622, 32547, 28521, 27484, 25655, 24810, 24260, 23998, 21333, 20958, 20695, 20197, 19982, 19973, 19767, 19702, 19579, 19097, 18344, 18318, 17253, 16573, 16467, 15808, 15628, 15463, 15357, 15312, 15165, 15086, 14643, 14203, 12890, 12669, 12662, 12412, 11742, 11197, 11041, 10543, 10396, 10396, 9877, 9870, 9563, 9562, 9443, 9423, 9311, 9117, 8978, 8717, 8563, 8456, 7979, 7928, 7759, 7702, 7497, 7105, 7102, 6951, 6891, 6877, 6802, 6785, 6613, 6506, 6465, 6380, 6286, 6070, 5484, 5390, 4964, 1289 };

        for (int i=0; i < discs.size(); i++) {
            dataItems.add(new DataItem(discs.get(i), values.get(i)));
        }

        return dataItems;
    }


    public static ArrayList<Double> sortValues(ArrayList<Double> values) {

        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        return sorted;
    }

    public static double calcMedian(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int middle = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {

            return (sorted.get(middle - 1) + sorted.get(middle)) / 2.0;
        } else {

            return sorted.get(middle);
        }
    }

    public static double calcMean(ArrayList<Double> values) {
        double total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += values.get(i);
        }

        return total / values.size();

    }

    public static double calcSDV(ArrayList<Double> values){

        double mean = calcMean(values);
        double sumOfDiff = 0;
        for (int i = 0; i < values.size(); i++){
          sumOfDiff += Math.pow(values.get(i)-mean, 2);
        }

        double variance = sumOfDiff / values.size();


        return Math.sqrt(variance);
    }

    public static double calcMax(ArrayList<Double> values){
        double big = 0;

        for (int i = 0; i < values.size(); i++){
            if (values.get(i)>big){
                big= values.get(i);
            }
        }

        return big;
    }

    public static double calcMin(ArrayList<Double> values){

        double small = values.get(0);

        for (int i = 0; i < values.size(); i++){
            if (values.get(i)<small){
                small = values.get(i);
            }
        }

        return small;
    }

    public static double calcAVG(ArrayList<Double> values){
        double sum = 0;
        double avg;

        for (int i = 0; i < values.size(); i++){
            sum += values.get(i);
        }
        avg = sum/ values.size();

        return avg;
    }

    public static double calcMode(ArrayList<Double> values) {
        double maxVal = 0;
        double maxCount = 0;

        for (int i = 0; i < values.size(); i++) {
            double val = values.get(i);
            int count = 1;

            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(j).equals(val)) {
                    count++;
                }
            }

            if (count > maxCount && count > 1) {
                maxCount = count;
                maxVal = val;
            }
        }

        if (maxCount == 1) {
            return 0;
        }

        return maxVal;
    }


    public static double calcLQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        if (sorted.size() < 4) {
            return Double.NaN;
        } else {
            int n = sorted.size();
            int lqIndex = (int) Math.floor(n / 4.0);
            double lq = sorted.get(lqIndex);
            if (n % 4 == 0) {
                lq = (lq + sorted.get(lqIndex - 1)) / 2.0;
            }
            return lq;
        }
    }

    public static double calcHQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        if (sorted.size() < 4) {
            return Double.NaN;
        } else {
            int n = sorted.size();
            int hqIndex = (int) Math.ceil(3.0 * n / 4.0);
            double hq = sorted.get(hqIndex);
            if (n % 4 == 0) {
                hq = (hq + sorted.get(hqIndex - 1)) / 2.0;
            }
            return hq;
        }
    }



    public static double calcQR(double lq, double hq){

        double qr = hq -lq;

        return qr;
    }

}




/*

old calculations that did not return exact value,.

    public static double calcLQ(ArrayList<Double> values){

        ArrayList<Double> sorted = sortValues(values);
        int mid = sorted.size()/2;
        ArrayList<Double> low = new ArrayList<>();

        for (int i = 0; i < mid; i++) {

            low.add(sorted.get(i));
        }
        int lq = low.size()/2;



        return sorted.get(lq);
    }

    public static double calcHQ(ArrayList<Double> values){


        ArrayList<Double> sorted = sortValues(values);
        int mid = sorted.size()/2;
        ArrayList<Double> high = new ArrayList<>();

        for (int i = mid; i < sorted.size(); i++) {
            high.add(sorted.get(i));
        }

        int hq = high.size()/2;



        return high.get(hq);
    }

        public static double calcMedian(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int middle = sorted.size()/2;
        return sorted.get(middle);
    }

    cheers!
 */

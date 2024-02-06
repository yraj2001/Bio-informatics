import java.io.*;
import java.sql.ResultSet;
import java.util.*;

public class Main {
    



    public static int gaps(String sars){
        int gap=0;
        for (int i1 = 0; i1 < sars.length(); i1++) {
            if (sars.charAt(i1) == '-') {

                gap++;

                // Calculate gap length

                int gapLength = 1;

                while (i1 + gapLength < sars.length() && sars.charAt(i1 + gapLength) == '-') {

                    gapLength++;

                }

               // gapFrequency.put(gapLength, gapFrequency.getOrDefault(gapLength, 0) + 1);

                i1 += gapLength - 1;

            }
        }
        return gap;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String filePath = "C://Users//saite//OneDrive//Documents//epsilon.sars.sing.maf";
        List<String> filepaths=new ArrayList<>();
        //alpha, epsilon, eta, theta, kappa, iota, zeta, mu, lambda, beta, gamma,
        //delta, and omicron.

        filepaths.add("C://Users//saite//OneDrive//Documents//alpha.sars.sing.maf");
        filepaths.add(filePath);
        filepaths.add("C://Users//saite//OneDrive//Documents//eta.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//theta.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//kappa.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//iota.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//zeta.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//mu.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//lambda.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//beta.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//gamma.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//delta.sars.sing.maf");
        filepaths.add("C://Users//saite//OneDrive//Documents//omicron.sars.sing.maf");
        List<Integer> matches=new ArrayList<>();
        List<Integer> mismatches=new ArrayList<>();
        List<Integer> totalGapsArr=new ArrayList<>();
        List<Double> gapRateArr=new ArrayList<>();
        List<Double> SubRate=new ArrayList<>();

        Map<Integer, Integer> gapFrequency = new HashMap<>();
        int gapCountSars = 0;
        int gapCountCov2 = 0;

        try {
            // Create a FileInputStream to open the file
            for(int j=0;j<filepaths.size();j++) {
                 gapCountSars = 0;
                 gapCountCov2 = 0;
                FileInputStream fileInputStream = new FileInputStream(new File(filepaths.get(j)));
                // Create a BufferedReader to read the file efficiently
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                System.out.println("file name: "+ filepaths.get(j));

                String line;
                String sars2Gene = "";
                String cov2gene = "";
                while ((line = bufferedReader.readLine()) != null) {
//                    if (line.contains("sars2") && !line.contains("#")) {
//                        sars2Gene = line;
//                        System.out.println(line);
//                    }
                    if (line.startsWith("s ") && !line.contains("#")) {
                        if(cov2gene.length()==0) {
                            cov2gene = line;
                            //System.out.println(line);
                        }
                        else{
                            sars2Gene=line;
                            //System.out.println(line);
                        }
                    }

                }

                String[] sars2geneArr = sars2Gene.split(" ");
                String[] cov2geneArr = cov2gene.split(" ");
                String sars = sars2geneArr[sars2geneArr.length-1];
                String cov2 = cov2geneArr[cov2geneArr.length-1];
                int sgeneStart=21562;
                int sgeneEnd=25384;
//                NTD	   40	 909
//                RBD	  955	1623
//                Cleavage 2056	2091
//                FP	 2362	2418
//                IFP	 2671	2718
//                HR1	 2824	2970
//                HR2	 3487	3606
//                TM	 3640	3702
//                CT	 3700	3819
int ntdStart=40,ntdEnd=910;
int rbdStart=955,rbdend=1624;
int cleavage=2056,cleavageEnd=2092;
int FPStart=2362,FPEnd=2419;
int IFPStart=2671,IFPEnd=2719;
int HR1Start=2824,HR1End=2971;
int HR2Start=3487,HR2End=3606;
int TMStart=3640,TMEnd=3703;
int CTStart=3700,CTEnd=3820;
                sars=sars.substring(sgeneStart,sgeneEnd);
                cov2=cov2.substring(sgeneStart,sgeneEnd);

                //System.out.println(sars);
                //System.out.println(cov2);
                //S 21562 25383
                int len = sars.length();
//                if (sars.length() != cov2.length()) {
//                    if (sars.length() < cov2.length()) {
//                        len = sars.length();
//                    } else {
//                        len = cov2.length();
//                    }
//                } else {
//                    len = sars.length();
//                }
                int substitutions = 0;

                    for (int i = 0; i < len; i++) {
                        if (sars.charAt(i) != '-' && cov2.charAt(i) != '-' && sars.charAt(i) != cov2.charAt(i)) {
                            substitutions++;
                        }
                    }

                //System.out.println(substitutions);


//                for (int i = 0; i < cov2.length(); i++) {
//                    if (cov2.charAt(i) == '-') {
//                        gapCountCov2++;
//                    }
//                }

                gapCountSars=gaps(sars);
                gapCountCov2=gaps(cov2);

                System.out.println("Total num of substitutions between sars and cov2 variant: " + substitutions);
                System.out.println("Total num of matches between sars and cov2 variant: " + (len-substitutions));
                System.out.println("Total num of gaps in sars: " + gapCountSars);
                System.out.println("Total num of gaps in cov2 variant: " + gapCountCov2);
                System.out.println("Total num of gaps : " + (gapCountCov2 + gapCountSars));
                double substitutionRate = (double) substitutions / (len);
                int totalGaps=gapCountSars+gapCountCov2;
                double gapRate = (double) totalGaps / ((len-substitutions) + totalGaps + substitutions);
                System.out.println("Gap Rate: "+gapRate);
                System.out.println("substitution rate: "+substitutionRate);
                System.out.println("-------------------------------------------------");
                matches.add(len-substitutions);
                mismatches.add(substitutions);
                totalGapsArr.add(totalGaps);
                gapRateArr.add(gapRate);
                SubRate.add(substitutionRate);
                // Close the BufferedReader and FileInputStream when done
                bufferedReader.close();
                fileInputStream.close();
            }
            System.out.println(matches);
            System.out.println(mismatches);
            System.out.println(totalGapsArr);
            System.out.println(gapRateArr);
            System.out.println(SubRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
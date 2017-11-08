

import com.sun.javafx.fxml.expression.KeyPath;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lizhaofu on 2017/5/24.
 */
public class PaperRead  {
    public List<Paper> DataRead(String inputPath) {
        String lineCurrent = null;
        List<Paper> paperList = new ArrayList<>();
        File file = new File(inputPath);
        File[] tempList = file.listFiles();
        //file.listFiles();
        for (int j = 0; j < tempList.length; j++) {
            if (!tempList[j].exists()) {
                System.out.println("file " + file + " is not existed, exit");
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tempList[j]), "UTF-8"));
                lineCurrent = br.readLine();
                Paper paper = new Paper();
                Boolean authorFlag = false;
                Boolean titleFlag = false;
                while (lineCurrent != null) {
                    String[] wordFlag = lineCurrent.split(" ");
                    if (wordFlag.length == 0) {
                        lineCurrent = br.readLine();
                        continue;
                    }
                    if (wordFlag[0].equals("PT")) {
                        paper = new Paper();
                    }
                    paper.setContextString(paper.getContextString()+lineCurrent+"\r\n");
                    if (wordFlag[0].equals("TI")) {
                        paper.setPaperTitle(lineCurrent.substring(2));
                        titleFlag = true;
                    } else if (titleFlag && wordFlag[0].equals("")) {
                        paper.setPaperTitle(paper.getPaperTitle() + lineCurrent.substring(2));
                    } else if (titleFlag && !wordFlag[0].equals("")) {
                        titleFlag = false;
                    }
                    if (wordFlag[0].equals("AB")) {
                        paper.setPaperAbstracts(lineCurrent.substring(3));
                        authorFlag = false;
                    }else if (titleFlag && wordFlag[0].equals("")) {
                        paper.setPaperTitle(paper.getPaperTitle() + lineCurrent.substring(2));
                    } else if (titleFlag && !wordFlag[0].equals("")) {
                        titleFlag = false;
                    }
                    if (wordFlag[0].equals("PY")) {
                        paper.setPaperYear(Integer.parseInt(lineCurrent.substring(3)));
                    }
                    if (wordFlag[0].equals("ER"))
                        paperList.add(paper);
                    lineCurrent = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return paperList;
    }

    private static List KeyRead(String filepath){
        List<String> keyList = new ArrayList<>();
        File file = new File(filepath);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String lineCurrent = br.readLine();
            while (lineCurrent != null){
                keyList.add(lineCurrent);
                lineCurrent = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;
    }

    public static void PaperWrite(List<Paper> paperList, String filePath) {
        String keyPath = "data/key";
        String fileName = "data/filename";
//        List<String> keyList = new ArrayList<>(Arrays.asList("modularization","generalization","combination"));
        List<String> keyList = KeyRead(keyPath);
        List<String> fileNameList = KeyRead(fileName);
        int startYear = 2000;
        int middleYear = 2010;

        for (int k = 0; k < keyList.size(); k++) {
            String[] keyString = keyList.get(k).split(",");
            int c = k + 1;
            File file = new File(filePath+c+fileNameList.get(k)+"-"+middleYear+".txt");
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                for (int i = 0; i < paperList.size(); i++) {
                    if (paperList.get(i).getPaperYear() < middleYear&&paperList.get(i).getPaperYear() > startYear){
                        for (int j = 0; j < keyString.length; j++) {
                            if (paperList.get(i).getPaperTitle().toLowerCase().contains(keyString[j].trim().toLowerCase())||
                                    paperList.get(i).getPaperAbstracts().toLowerCase().contains(keyString[j].trim().toLowerCase())){
                                bw.write(paperList.get(i).getContextString());
                                bw.newLine();
                            }
                        }
                    }
                }
//                bw.flush();
//                bw.close();
//                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

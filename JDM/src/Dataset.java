import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.*;

public class Dataset
{
    private int percent = 0;
    private int features = 0;
    private int dataCount = 0;
    private int sizeOfTrainingSet = 0;
    private int sizeOfTestingSet = 0;

    private String fileName = null;

    private ArrayList<ArrayList<String>> trainingSet;
    private ArrayList<ArrayList<String>> testingdSet;
    private ArrayList<String> titleOfFeatures;

    Dataset(String dName, int dPercent)
    {
        fileName = dName;
        percent = dPercent;
        trainingSet = new ArrayList<ArrayList<String>>();
        testingdSet = new ArrayList<ArrayList<String>>();
        titleOfFeatures = new ArrayList<String> ();
    }

    public int getSizeOfDataset()
    {
        return dataCount;
    }

    public int getSizeOfFeatures()
    {
        return features;
    }

    public ArrayList<String> getTitleOfFeatures()
    {
        return titleOfFeatures;
    }

    public void displayTrainingSet()
    {
        for(int i=0; i<features; i++)
        {
            System.out.println(trainingSet.get(i).toString());
        }
    }

    public void displayTestingSet()
    {
        for(int i=0; i<features; i++)
        {
            System.out.println(testingdSet.get(i).toString());
        }
    }

    public void readDataset() throws IOException
    {
        ArrayList<ArrayList<String>> dataSet = new ArrayList<ArrayList<String>>();
        int positionOfPeriod = fileName.lastIndexOf('.');
        String fileExtention = null;

        if(positionOfPeriod > 0)
        {
            fileExtention = fileName.substring(positionOfPeriod + 1);
        }

        if (fileExtention.equals("xls"))
        {
            readDatasetFromXls(dataSet);
        }
        else if(fileExtention.equals("txt"))
        {
            readDatasetBySplit(dataSet);
        }
        else
        {
            System.out.println("Unknown Type");
            System.exit (1);
        }

        // Separate dataset into training & testing dataset
        sizeOfTrainingSet = percent * dataCount / 100;
        sizeOfTestingSet = dataCount - sizeOfTrainingSet;

        for(int i=0; i<features; i++)
        {
            titleOfFeatures.add(dataSet.get (0).get (i));
        }

        for(int j=1; j<sizeOfTrainingSet; j++)
        {
            trainingSet.add(dataSet.get (j));
        }

        for(int j=sizeOfTrainingSet; j<dataCount; j++)
        {
            testingdSet.add (dataSet.get (j));
        }

        sizeOfTrainingSet = sizeOfTrainingSet - 1;

        // Transpose dataset
        testingdSet = transpose (testingdSet);
        trainingSet = transpose (trainingSet);
    }

    public void readDatasetBySplit(ArrayList<ArrayList<String>> dataSet) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        int countData = 0;

        while(scanner.hasNextLine())
        {
            ArrayList<String> rowList = new ArrayList<String>();
            StringTokenizer currentToken = new StringTokenizer(scanner.nextLine(), "\t");
            int countFeatures = 0;
            countData++;

            while(currentToken.hasMoreElements())
            {
                countFeatures++;
                String temp = currentToken.nextToken();
                rowList.add(temp);
            }
            features = countFeatures;
            dataSet.add(rowList);
        }
        dataCount = countData;
    }

    public void readDatasetFromXls(ArrayList<ArrayList<String>> dataSet) throws IOException
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(poifsFileSystem);

            HSSFSheet hssfSheet = myWorkBook.getSheetAt(0);
            Iterator<?> rowIter = hssfSheet.rowIterator();

            int countData = 0;

            while (rowIter.hasNext())
            {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<?> cellIter = myRow.cellIterator();
                ArrayList<String> rowList = new ArrayList<String>();
                int countFeatures = 0;
                countData++;

                while (cellIter.hasNext())
                {
                    countFeatures++;
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    rowList.add(myCell.toString());
                }
                features = countFeatures;
                dataSet.add(rowList);
            }
            dataCount = countData;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> transpose(ArrayList<ArrayList<String>> table)
    {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        final int N = table.get(0).size();

        for (int i = 0; i < N; i++)
        {
            ArrayList<String> col = new ArrayList<String>();

            for (ArrayList<String> row : table)
            {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }
}
import java.io.IOException;

public class JDM
{
    public static void main(String[] args) throws IOException
    {
//        Dataset mDataset = new Dataset("datasets/STULONG-Death-data.xls", 70);
//        Dataset mDataset = new Dataset("datasets/Info.txt", 70);
        Dataset mDataset = new Dataset("datasets/customer_card.txt", 70);

        mDataset.readDataset();
    }
}
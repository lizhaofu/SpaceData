/**
 * Created by lizhaofu on 2017/4/24.
 */
public class Paper {
    private String paperTitle;
    private String paperAuthor;
    private String paperAbstracts;
    private String paperSourcePublication;

    public String getPaperSourcePublication() {
        return paperSourcePublication;
    }

    public void setPaperSourcePublication(String paperSourcePublication) {
        this.paperSourcePublication = paperSourcePublication;
    }

    private int paperCitedAmount;

    public String getContextString() {
        return contextString;
    }

    public void setContextString(String contextString) {
        this.contextString = contextString;
    }

    private int paperYear;
    private String contextString;

   public Paper(){
       paperTitle = "";
       paperAuthor = "";
       paperAbstracts = "";
       contextString = "";
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAuthor() {
        return paperAuthor;
    }

    public void setPaperAuthor(String paperAuthor) {
        this.paperAuthor = paperAuthor;
    }

    public String getPaperAbstracts() {
        return paperAbstracts;
    }

    public void setPaperAbstracts(String paperAbstracts) {
        this.paperAbstracts = paperAbstracts;
    }

    public int getPaperCitedAmount() {
        return paperCitedAmount;
    }

    public void setPaperCitedAmount(int paperCitedAmount) {
        this.paperCitedAmount = paperCitedAmount;
    }

    public int getPaperYear() {
        return paperYear;
    }

    public void setPaperYear(int paperYear) {
        this.paperYear = paperYear;
    }
}

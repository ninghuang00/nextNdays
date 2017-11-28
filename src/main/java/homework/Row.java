package homework;

/**
 * Created by huangning on 2017/11/3.
 */
class Row{
    MyDate inputDate;
    int n;
    MyDate outputDate;

    public Row() {

    }

    public Row(MyDate inputDate, int n, MyDate outputDate) {
        this.inputDate = inputDate;
        this.n = n;
        this.outputDate = outputDate;
    }

    public MyDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(MyDate inputDate) {
        this.inputDate = inputDate;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public MyDate getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(MyDate outputDate) {
        this.outputDate = outputDate;
    }
}

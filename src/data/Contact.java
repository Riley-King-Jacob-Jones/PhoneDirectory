package src.data;

public class Contact {
    private final String name;
    private final int number;

    public Contact(String name, int number){
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return String.format("%s | %d",this.name, this.number);
    }

    public String toStringFormatted(){
        return String.format("%10s | %10s",this.name, this.formatNumber());
    }

    public String formatNumber(){
        String formattedNumber="";
        String number = String.valueOf(this.number);
        //  1-234-5678
        if (number.length()==8){
            formattedNumber = (String.format("%s-%s-%s",number.substring(0,1),number.substring(1,4),number.substring(4,8)));
        //  234-5678
        } else if (number.length()==7) {
            formattedNumber = (String.format("%s-%s",number.substring(0,3),number.substring(3,7)));
        }
        return formattedNumber;
    }
}

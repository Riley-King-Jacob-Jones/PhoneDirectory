package src.data;

public class Contact {
    private final String name, number;

    public Contact(String name, String number){
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return String.format("%s | %s",this.name, this.number);
    }

    public String toStringFormatted(){
        return String.format("%10s | %10s",this.name, this.formatNumber());
    }

    public String formatNumber(){
        String formattedNumber="";
        //  1-234-5678
        if (this.number.length()==10){
            formattedNumber = (String.format("%s-%s-%s",this.number.substring(0,3),this.number.substring(3,6),this.number.substring(6,10)));
        //  234-5678
        } else if (this.number.length()==7) {
            formattedNumber = (String.format("%s-%s",this.number.substring(0,3),this.number.substring(3,7)));
        }
        return formattedNumber;
    }
}

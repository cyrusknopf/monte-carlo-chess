public class Bitboard{
    private long layout;
    private boolean colour;
    private char piece;
    String row;

    public Bitboard(boolean colour, char piece){
        this.colour = colour;
        this.piece = piece;

        int diff = Character.getNumericValue(piece)-65;
        if(diff<=2){
            for(int i=0;i<4;i++){
                if(i==diff){
                    row+="1";
                }
                else{
                    row+="0";
                }
            }
            String temp = this.reverse(row);
            row+=temp;
            temp=row;
            row = "00000000000000000000000000000000000000000000000000000000" + row;
            if(colour==false){
                row = temp+"00000000000000000000000000000000000000000000000000000000";
            }
            this.layout=Long.parseLong(row);
        }
        else if(diff==3||diff==4){
            for(int i=0;i<8;i++){
                if(i==diff){
                    row+="1";
                }
                else{
                    row+="0";
                }
            }
            String temp = row;
            row = "000000000000000000000000000000000000000000000000"+row+"00000000";
            if(colour==false){
                row = "00000000"+temp+"000000000000000000000000000000000000000000000000";
            }
            this.layout=Long.parseLong(row);
        }
        else{
            if(colour=true){
                layout=0b0000000000000000000000000000000000000000000000001111111100000000L;
            }
            else{
                layout=0b0000000011111111000000000000000000000000000000000000000000000000L;
            }
        }
    }
    public String reverse(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }
    public long getLayout(){
        return this.layout;
    }
   // @Override
   // public String toString(){
        //int length = this.getLayout().toStr
    //}
    public static void main(String[] args){
        
        
    }
}
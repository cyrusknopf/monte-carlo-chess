public class Bitboard{
    private long layout;
    private boolean colour;
    private char piece;
    private String row = "";

    public Bitboard(boolean colour, char piece){
        this.colour = colour;
        this.piece = piece;

        int diff = Character.getNumericValue(piece)-10;
        System.out.println(diff);
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
            if(colour==false){
                row = temp+"00000000000000000000000000000000000000000000000000000000";
            }
            this.layout = 0b0000000000000000000000000000000000000000000000000000000000000000L;
            this.layout=this.convertToLong(row);
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
            row = "00000000000000000000000000000000000000000000000000000000"+row;
            if(colour==false){
                row = temp+"00000000000000000000000000000000000000000000000000000000";
              }
            this.layout=this.convertToLong(row);
        }
        else{
            if(colour==true){
                layout=0b0000000000000000000000000000000000000000000000001111111100000000L;
            }
            else if(colour==false){
                layout=0b0000000011111111000000000000000000000000000000000000000000000000L;
            }
        }
    }
    public long convertToLong(String binaryString) {
        long result = 0;

        // Iterate through the characters of the binary string from left to right
        for (int i = 0; i < binaryString.length(); i++) {
            char digit = binaryString.charAt(i);

            // Multiply the current result by 2 and add the current digit (0 or 1)
            result = result * 2 + (digit - '0');
        }

        return result;
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
    public String toString() {
        StringBuilder boardBuilder = new StringBuilder();

        for (int i = 63; i >= 0; i--) {
            long bit = (this.layout >> i) & 1L;
            boardBuilder.append(bit).append(" ");

            if (i % 8 == 0) {
                int row = i / 8;
                boardBuilder.append("|" + row);
                boardBuilder.append("\n");
            }
        }
        boardBuilder.append("---------------\n");
        boardBuilder.append("A B C D E F G H");
        return boardBuilder.toString();
    }
    public static void main(String[] args){
        Bitboard x = new Bitboard(false,'Z');
        System.out.println(x.toString());
        
    }


}
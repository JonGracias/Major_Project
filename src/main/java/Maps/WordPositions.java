package Maps;

public record WordPositions(String Word, Integer Pos, Integer Len){
    @Override
    public String toString() {
        int s = 20 - Len;
        String space = String.format("%s","\s".repeat(Math.max(0, s)));
        System.out.println(s);
        return "WP{" +
                "Word= '"+ Word + "'" + space +
                " [Pos=" + Pos +
                ", Len=" + Len + ']' +
                '}';
    }

}

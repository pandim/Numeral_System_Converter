package converter;

public class test {
    public static void main(String[] args) {
//        System.out.println(Character.forDigit (10, 11));
        String string = "0123456789a";
        Character i = Character.forDigit (35, 36);
        System.out.println(i + "   " + string.matches("[0-9a-" + i +"]+"));
    }
}

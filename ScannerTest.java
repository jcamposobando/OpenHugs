class ScannerTest {
    public static void main(String args[]){
        Scanner scanner = new Scanner(args[0]);
        Token token = scanner.Scan();
        while(token.kind != 0 ){
            System.out.println( token.val );
            token = scanner.Scan();
        }
    }
}
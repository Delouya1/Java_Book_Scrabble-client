package test.server;


import java.io.*;


public class BookScrabbleHandler implements ClientHandler {
    BufferedReader in;
    PrintWriter out;



    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {
            DictionaryManager dm = DictionaryManager.get();
            in = new BufferedReader(new InputStreamReader(inFromclient));
            out = new PrintWriter(outToClient);
            //for example: "Q,book1,book2,book3,word"
            String line = in.readLine();
            String[] args = line.split(",");
            String firstLetter = args[0];
            //delete the first letter from the args array
            String[] newArgs = new String[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            if (firstLetter.equals("Q")) {
                boolean t = dm.query(newArgs);
                out.println(t+"\n");
                out.flush();
            } else if (firstLetter.equals("C")) {
                boolean t = dm.challenge(newArgs);
                out.println(t+"\n");
                out.flush();
            }
            close();

        } catch (Exception ignored) {}

    }



    @Override
    public void close() {
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

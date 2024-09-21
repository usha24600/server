import java.net.*;
import java.io.*;
class Server
{

    ServerSocket Server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
     //constructor
    public Server()
    { 
        try{
              Server=new ServerSocket(7777);
              System.out.println("server is ready to accpect connection");
              System.out.println("waiting");
              socket=Server.accept();
              br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
              out=new PrintWriter(socket.getOutputStream());

              startReading();
              startWriting();
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void startReading()
    {
       Runnable r1=()->{
            System.out.println("reader started...");

            while(true)
            {
                try{
                String msg=br.readLine();
                if(msg.equals("block"))
                {
                    System.out.println("clientblock you in the chat");
                    break;
                }
                System.out.println("client :"+msg);
            }catch(Exception e){
                e.printStackTrace();
            }
            }
       };
       new Thread(r1).start();
    }
    
    public void startWriting()
    {
        Runnable r2=()->{
            System.out.println("writer started..");
            while(true)
            {
                try{
                      BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                      String content=br1.readLine();
                      out.println(content);
                      out.flush();

                      

                }catch (Exception e){
                    
                    e.printStackTrace();
                }
            }

        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("This is server going to start server");
        new Server();

    }
}

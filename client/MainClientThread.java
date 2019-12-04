package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MainClientThread extends Thread
{  private Socket           socket   = null;
   private MainClient       client   = null;
   private DataInputStream  streamIn = null;

   public MainClientThread(MainClient _client, Socket _socket)
   {  client   = _client;
      socket   = _socket;
      open();  
      this.start();
   }
   public void open()
   {  try
      {  streamIn  = new DataInputStream(socket.getInputStream());
      }
      catch(IOException ioe)
      {  System.out.println("Error getting input stream: " + ioe);
         client.stop();
      }
   }
   public void close()
   {  try
      {  if (streamIn != null) streamIn.close();
      }
      catch(IOException ioe)
      {  System.out.println("Error closing input stream: " + ioe);
      }
   }
   public void run()
   {  while (true)
      {  try
         {  client.handle(streamIn.readUTF());
         }
         catch(IOException ioe)
         {  System.out.println("Listening error: " + ioe.getMessage());
            client.stop();
         }
      }
   }
}

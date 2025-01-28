package shellsshremoto;

import com.jcraft.jsch.*;
import java.io.IOException;


public class ShellSSHRemoto {

    private static final String USERNAME = "simon";
    private static final String HOST = "localhost";
    private static final int PORT = 22;
    private static final String PASSWORD = "smr1234";
    
    public static void main(String[] args) {
        try {
            SSHConnector sshConnector = new SSHConnector();
            sshConnector.connect(USERNAME, PASSWORD, HOST, PORT);
            sshConnector.executeCommand("ls");
            System.exit(0);
            sshConnector.executeShell();
        } catch (JSchException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    } 
}

class SSHConnector {
    private Session session;
    public void connect(String username, String password, String host, int port)
            throws JSchException, IllegalAccessException {
        if (this.session == null || !this.session.isConnected()) {
            JSch jsch = new JSch();
            this.session = jsch.getSession(username, host, port);
            this.session.setPassword(password);
            this.session.setConfig("StrictHostKeyChecking", "no");
            this.session.connect();
        } else {
            throw new IllegalAccessException("Sesión SSH ya iniciada.");
        }
    }
    


    public final void executeShell()
            throws IllegalAccessException, JSchException, IOException {
        if (this.session != null && this.session.isConnected()) {
            Channel channel = this.session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(3 * 1000);
            
        } else {
            throw new IllegalAccessException("No existe sesion SSH iniciada.");
       }
    }
    
    
        
    }
    

package alvarodelrosal.ftp.modelo;

public class FTPUser {

    private String name;
    private String username;
    private String password;
    private boolean isAdmin;

    public FTPUser(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean is(String username, String password) {
        return (this.username == null ? username == null : this.username.equals(username)) &&
                (this.password == null ? password == null : this.password.equals(password));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FTPUser other = (FTPUser) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 89 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 89 * hash + (this.isAdmin ? 1 : 0);
        return hash;
    }
    
    
    
}

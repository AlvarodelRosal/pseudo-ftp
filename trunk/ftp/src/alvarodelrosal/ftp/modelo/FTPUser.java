package alvarodelrosal.ftp.modelo;

public class FTPUser {
    
    private String name;
    private String user;
    private String password;
    private boolean isAdmin;

    public FTPUser(String name, String user, String password, boolean isAdmin) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }
    
    public boolean checkUserAndPassword(String user, String password) {
        return (this.user == null ? user == null : this.user.equals(user)) &&
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
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
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
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 29 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 29 * hash + (this.isAdmin ? 1 : 0);
        return hash;
    }
    
}

package sait.domain;

public class User
{

    private String username;
    private String password;
    private String salt;
    private String hashedandsaltedpassword;
    private boolean isAdmin;

    public User()
    {
    }

    public User(String username, String password, boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isIsAdmin()
    {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getHashedandsaltedpassword()
    {
        return hashedandsaltedpassword;
    }

    public void setHashedandsaltedpassword(String hashedandsaltedpassword)
    {
        this.hashedandsaltedpassword = hashedandsaltedpassword;
    }
}

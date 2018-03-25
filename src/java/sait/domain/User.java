package sait.domain;

public class User
{

    private String username;
    private String salt;
    private String hashedandsaltedpassword;
    private boolean isAdmin;

    public User()
    {
    }

    public User(String username, String salt, String hashedandsaltedpassword, boolean isAdmin)
    {
        this.username = username;
        this.isAdmin = isAdmin;
        this.salt = salt;
        this.hashedandsaltedpassword = hashedandsaltedpassword;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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

    public String getHashedandsaltedpassword()
    {
        return hashedandsaltedpassword;
    }
}

package jsonprocessing.threeways.databinding.fulldatabinding;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

public class UserConversion
{
    // can be reused and shared globally
    private ObjectMapper om = new ObjectMapper();

    public User convertToObject()
    {
        URL userJsonFile = getClass().getResource("user.json");

        String userJson = readFromFile(userJsonFile);

        User user = null;
        try
        {
            user = om.readValue(userJson, User.class);
        }
        catch (JsonParseException e)
        {
            throw new RuntimeException("Unable to parse given json " + userJson, e);
        }
        catch (JsonMappingException e)
        {
            throw new RuntimeException("Unable to map given json to object(" + User.class + ", json: " + userJson);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return user;
    }

    public String convertToJson(User u) {

        try
        {
            String s = om.writeValueAsString(u);

            return s;
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        UserConversion uc = new UserConversion();

        User user = uc.convertToObject();

        assert user != null;

        // test2

        User u = new User();
        u.setGender(User.Gender.FEMALE);

        User.Name n = new User.Name();
        n.setFirst("fs");
        n.setLast("ls");

        u.setName(n);
        u.setUserImage(new byte[1024]);
        u.setVerified(true);

        String s = uc.convertToJson(u);

        assert s != null;
        assert !s.isEmpty();
    }

    private String readFromFile(URL file)
    {
        try {
            File f = new File(file.toURI());

            try (InputStream fis = new FileInputStream(f);
                 BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                String line;
                StringBuilder sb = new StringBuilder();

                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                return sb.toString();
            }
        } catch (URISyntaxException | IOException e) {
           throw new IllegalArgumentException("Could not read from file " + file);
        }
    }
}

class User {
    public enum Gender { MALE, FEMALE };

    public static class Name {
      private String _first, _last;

      public String getFirst() { return _first; }
      public String getLast() { return _last; }

      public void setFirst(String s) { _first = s; }
      public void setLast(String s) { _last = s; }
    }

    private Gender _gender;
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;

    public Name getName() { return _name; }
    public boolean isVerified() { return _isVerified; }
    public Gender getGender() { return _gender; }
    public byte[] getUserImage() { return _userImage; }

    public void setName(Name n) { _name = n; }
    public void setVerified(boolean b) { _isVerified = b; }
    public void setGender(Gender g) { _gender = g; }
    public void setUserImage(byte[] b) { _userImage = b; }
}
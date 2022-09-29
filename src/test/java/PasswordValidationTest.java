import com.paasword.validation.service.PasswordService;
import com.paasword.validation.service.PasswordServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PasswordValidationTest {

  @Test
  public void checkForPasswordValidForThreeConditions(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<String>(), "1test$");
    String status = passwordService.validatePasswordForAtleastThreeConditions();
    Assert.assertEquals("valid", status);
  }

  @Test
  public void checkForPasswordValidityForlowerCase(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<String>(), "test");
    String status = passwordService.validatePasswordForNotHavingLowerCaseLetters();
    Assert.assertEquals("valid", status);
  }

  @Test
  public void checkForNullPassword(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<>(), null);
    String status = passwordService.validatePasswordForNotNull();
    Assert.assertEquals("not valid", status);
    Assert.assertEquals("Password should not be null", passwordService.getResultStatusMessages().get(0));
  }

  @Test
  public void checkForNOtExceedingEightChars(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<>(), "hgv12");
    String status = passwordService.validatePasswordForEightChars();
    Assert.assertEquals("not valid", status);
    Assert.assertEquals("Password should be more than 8 characters", passwordService.getResultStatusMessages().get(0));
  }

  @Test
  public void checkForNotHavingUppercase(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<>(), "hgv12");
    String status = passwordService.validatePasswordForUpperCaseLetter();
    Assert.assertEquals("not valid", status);
    Assert.assertEquals("Password Does not contain atleast one upper case letter", passwordService.getResultStatusMessages().get(0));
  }

  @Test
  public void checkForNotHavingLowercase(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<>(),"AB12");
    String status = passwordService.validatePasswordForLowerCaseLetter();
    Assert.assertEquals("not valid", status);
    Assert.assertEquals("Password Does not contain atleast one lower case letter", passwordService.getResultStatusMessages().get(0));
  }

  @Test
  public void checkForNotHavingNumber(){
    PasswordService passwordService = new PasswordServiceImpl(new ArrayList<>(),"ABCD");
    String status = passwordService.validatePasswordForNumber();
    Assert.assertEquals("not valid", status);
    Assert.assertEquals("Password Does not contain atleast one number", passwordService.getResultStatusMessages().get(0));
  }

}

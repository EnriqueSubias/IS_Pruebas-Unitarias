package src.data;

import src.exceptions.*;

/**
 * Essential data classes
 */
public final class Password {
    // The tax identification number in the Spanish state.
    private final String password;

    public Password(String password) throws NullPasswordException, NotValidPasswordException {
        if (password == null) {
            throw new NullPasswordException();
        } else {
            boolean upperCheck = false;
            boolean lowerCheck = false;
            boolean digitCheck = false;
            boolean lengthCheck = false;
            for (int i = 0; i < password.length(); i++) // This loop tests string
            {
                char s = password.charAt(i); // char s represents the index
                if (Character.isUpperCase(s)) // This verifies there is a uppercase letter
                {
                    upperCheck = true;
                }
                if (Character.isLowerCase(s)) // This verifies there is a lowercase letter
                {
                    lowerCheck = true;
                }
                if (Character.isDigit(s)) // This verifies there is a digit
                {
                    digitCheck = true;
                }
            }
            // This verifies the password is atleast 6 character and at most 30 characters
            if (password.length() >= 6 && password.length() <= 30) {
                lengthCheck = true;
            }
            if (upperCheck && lowerCheck && digitCheck && lengthCheck) {
                this.password = password;
            } else {
                throw new NotValidPasswordException();
                // it must contain lower and upper case letters and digits
            }
        }
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Password passwordClass = (Password) o;
        return password.equals(passwordClass.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return "Password{" + "password ciudadano='" + password + '\'' + '}';
    }
}

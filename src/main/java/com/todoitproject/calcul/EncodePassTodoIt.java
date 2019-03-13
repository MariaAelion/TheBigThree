package com.todoitproject.calcul;

/**
*  @author TheBigThree
* @version 1.0.1
*
*/
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassTodoIt {
	
	/**
	 * @description Crypter le mot de passe et vérifier qu'il correspond à celui dans le back 
	 * 
	 */
	public static PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new PasswordEncoder() {
			
			public boolean matches(CharSequence arg0, String arg1) {
				return BCrypt.checkpw(arg0.toString(), arg1);
			}
			
			public String encode(CharSequence arg0) {
				String pw_hash = BCrypt.hashpw(arg0.toString(), BCrypt.gensalt(4));
				return pw_hash;
			}
		};
		return encoder;
	}

}

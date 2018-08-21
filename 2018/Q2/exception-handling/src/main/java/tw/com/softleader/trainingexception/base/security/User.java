package tw.com.softleader.trainingexception.base.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.com.softleader.trainingexception.base.auth.ApproveAuth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String acct;

	private ApproveAuth approveAuth;

}

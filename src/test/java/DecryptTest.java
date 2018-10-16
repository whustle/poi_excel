import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class DecryptTest {
	@Test
	public void test() throws Exception {
		String password="e86ZcjbKTgy0vdZtad2isYtk4vx0/VXXnXu4vHH+ZRMpuromuuaDd33EWm3Pq23QPt9Ct3GT2+jYaFHgIB4EpQ==";
		String publicKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKCA5VWSEP2GebHLnzPgIoFZM8wY+T0gGIHo8MWr20Xmj722LwYEUNzB+SxsQukMX6RMdzv9alXhAMXLMgD0ecCAwEAAQ==";
		String pass = ConfigTools.decrypt(publicKey, password);
		System.out.println(pass);
	}
}

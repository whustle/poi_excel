import com.lz.common.util.StringUtil;
import org.junit.Test;

public class StringTOIntTest {
	@Test
	public void test(){
		int i = StringUtil.StringToInt("1.0");
		System.out.println(i);
	}
}

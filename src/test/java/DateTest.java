import com.lz.common.util.DateFormatUtil;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.util.Date;
@Log4j
public class DateTest {
	@Test
	public void test(){
		String s = DateFormatUtil.formatDate(new Date());
		log.info("dsfsf");
		System.out.println(s);

	}
}

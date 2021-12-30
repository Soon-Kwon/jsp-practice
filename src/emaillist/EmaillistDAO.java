package emaillist;

import java.util.List;

public interface EmaillistDAO {
	public int insert(String lastName, String firstName, String email);
	public List<EmaillistDTO> getList();
}

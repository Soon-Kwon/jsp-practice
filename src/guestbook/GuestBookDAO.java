package guestbook;

import java.util.List;

public interface GuestBookDAO {
	public List<GuestBookDTO> getList();
	public int insert(GuestBookDTO dto);
	public int delete(int no, String password);
}

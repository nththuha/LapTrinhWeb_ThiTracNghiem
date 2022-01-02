package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.Mailer;
import ptithcm.entity.CauHoiEntity;
import ptithcm.entity.ChiTietThiEntity;
import ptithcm.entity.GiangVienEntity;
import ptithcm.entity.MonHocEntity;
import ptithcm.entity.SinhVienEntity;

@Transactional
@Controller

public class GiangVienController {
	GiangVienEntity GV;
	int id;
	MonHocEntity monhoc = new MonHocEntity();

	@Autowired
	SessionFactory factory;

	@RequestMapping("login")
	public String login(ModelMap model, HttpSession ss) {
		model.addAttribute("giangVien", new GiangVienEntity());
		return "giangvien/login/login";
	}

	@RequestMapping("quanly")
	public String quanLy(ModelMap model, @ModelAttribute("giangVien") GiangVienEntity gv, HttpSession ss) {
		String maGV = gv.getMaGV();
		String matKhau = gv.getMatKhau();

		Session session = factory.getCurrentSession();
		String hql = "FROM GiangVienEntity WHERE MAGV = :maGV AND MATKHAU = :matKhau";
		Query query = session.createQuery(hql);
		query.setParameter("maGV", maGV);
		query.setParameter("matKhau", matKhau);
		List<GiangVienEntity> list = query.list();
		if (list.size() == 0) {
			model.addAttribute("message", "Đăng nhập thất bại!");
			return "giangvien/login/login";
		}
		GV = list.get(0);
		ss.setAttribute("giangVien", GV.getMaGV());
		model.addAttribute("message", "Xin chào " + GV.getHo() + " " + GV.getTen() + "!");
		return "giangvien/menu/blank";
	}

	public String taoMatKhau() {
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return value + "";
	}

	@RequestMapping("matkhau")
	public String matKhau() {
		return "giangvien/login/password";
	}

	@Autowired
	Mailer mailer;

	@RequestMapping("datlai")
	public String datLaiMK(HttpServletRequest rq, ModelMap model) {
		String email = rq.getParameter("email");
		Session session = factory.getCurrentSession();
		String hql = "FROM GiangVienEntity WHERE EMAIL = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List<GiangVienEntity> list = query.list();
		// session.close();

		if (list.size() == 0) {
			model.addAttribute("message", "Email không tồn tại trong CSDL!");
		} else {
			String mk = taoMatKhau();
			// Cập nhật mật khẩu trong CSDL
			Session session2 = factory.openSession();
			Transaction t = session2.beginTransaction();
			try {
				GiangVienEntity gv = new GiangVienEntity(list.get(0).getMaGV(), list.get(0).getHo(),
						list.get(0).getTen(), email, mk);
				session2.update(gv);
				t.commit();

			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", e);
			} finally {
				session2.close();
			}

			// gửi mail
			try {
				mailer.send("thuhango0204@gmail.com", email, "Khôi phục mật khẩu", "Mật khẩu mới của bạn là: " + mk);
				model.addAttribute("message1", "Mật khẩu mới đã được gửi đến email của bạn!");
			} catch (Exception e) {
				model.addAttribute("message", "Gửi mail thất bại!");
			}
		}
		return "giangvien/login/password";
	}

	public List<GiangVienEntity> getGiangVien() {
		Session session = factory.getCurrentSession();
		String hql = "FROM GiangVienEntity as gv order by gv.maGV desc";
		Query query = session.createQuery(hql);
		List<GiangVienEntity> list = query.list();
		return list;
	}

	@RequestMapping("edit")
	public String edit(HttpServletRequest request, ModelMap model,
			@ModelAttribute("giangVien") GiangVienEntity giangVien) {
		List<GiangVienEntity> DSgiangVien = this.getGiangVien();
		PagedListHolder pagedListHolder = new PagedListHolder(DSgiangVien);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/edit";
	}

	public int insertGiangVien(GiangVienEntity gv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(gv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "edit", params = "btnAdd")
	public String addGiangVien(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("giangVien") GiangVienEntity giangVien, BindingResult errors) {

		int check = this.insertGiangVien(giangVien);
		if (check != 0) {
			model.addAttribute("message1", "Thêm giảng viên thành công!");
			model.addAttribute("giangVien", new GiangVienEntity());
		} else {
			model.addAttribute("message0", "Thêm giảng viên thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getGiangVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("btnStatus", "btnAdd");
		return "giangvien/menu/edit";
	}

	public GiangVienEntity get1GiangVien(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM GiangVienEntity where MAGV = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		GiangVienEntity list = (GiangVienEntity) query.list().get(0);

		return list;
	}

	public int updateGiangVien(GiangVienEntity gv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(gv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "edit", params = "btnEdit")
	public String edit_GiangVien(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("giangVien") GiangVienEntity giangVien, BindingResult errors) {

		int check = this.updateGiangVien(giangVien);
		if (check != 0) {
			model.addAttribute("message1", "Chỉnh sửa giảng viên thành công!");
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("giangVien", new GiangVienEntity());
		} else {
			model.addAttribute("message0", "Chỉnh sửa giảng viên thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getGiangVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "giangvien/menu/edit";
	}

	@RequestMapping(value = "edit/{maGV}.htm", params = "linkEdit")
	public String editGiangVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("giangVien") GiangVienEntity giangVien, @PathVariable("maGV") String maGV) {

		PagedListHolder pagedListHolder = new PagedListHolder(this.getGiangVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);

		model.addAttribute("giangVien", this.get1GiangVien(maGV));
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("btnStatus", "btnEdit");
		return "giangvien/menu/edit";
	}

	public int deleteGiangVien(GiangVienEntity gv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(gv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "/edit/{maGV}.htm", params = "linkDelete")
	public String deleteGiangVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("giangVien") GiangVienEntity giangVien, @PathVariable("maGV") String maGV) {

		int check = this.deleteGiangVien(giangVien);
		if (check != 0) {
			model.addAttribute("message1", "Xóa giảng viên thành công");
		} else {
			model.addAttribute("message0", "Xóa giảng viên thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getGiangVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/edit";
	}

	public List<GiangVienEntity> searchGiangVien(String ten) {
		Session session = factory.getCurrentSession();
		String hql = "FROM GiangVienEntity where ten LIKE :ten";
		Query query = session.createQuery(hql);
		query.setParameter("ten", "%" + ten + "%");
		List<GiangVienEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "edit", params = "btnsearch")
	public String searchGiangVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("giangVien") GiangVienEntity giangVien) {
		PagedListHolder pagedListHolder = new PagedListHolder(
				this.searchGiangVien(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);

		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/edit";
	}

	// --------------------THÊM / XÓA / SỬA SINH VIÊN-----------------------
	public List<SinhVienEntity> getSinhVien() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVienEntity as sv order by sv.maSV desc";
		Query query = session.createQuery(hql);
		List<SinhVienEntity> list = query.list();
		return list;
	}

	@RequestMapping("editsinhvien")
	public String editsinhvien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("sinhVien") SinhVienEntity sinhVien) {
		List<SinhVienEntity> DSSinhVien = this.getSinhVien();
		PagedListHolder pagedListHolder = new PagedListHolder(DSSinhVien);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/editsinhvien";
	}

	public int insertSinhVien(SinhVienEntity sv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(sv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "editsinhvien", params = "btnAdd")
	public String addSinhVien(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("sinhVien") SinhVienEntity sinhVien, BindingResult errors) {
		int check = this.insertSinhVien(sinhVien);
		if (check != 0) {
			model.addAttribute("message1", "Thêm sinh viên thành công!");
			model.addAttribute("sinhVien", new SinhVienEntity());
		} else {
			model.addAttribute("message0", "Thêm sinh viên thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getSinhVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/editsinhvien";
	}

	public SinhVienEntity get1SinhVien(String ma) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVienEntity where MASV = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", ma);
		SinhVienEntity list = (SinhVienEntity) query.list().get(0);

		return list;
	}

	public int updateSinhVien(SinhVienEntity sv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(sv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "editsinhvien", params = "btnEdit")
	public String edit_SinhVien(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("sinhVien") SinhVienEntity sinhVien, BindingResult errors) {

		int check = this.updateSinhVien(sinhVien);
		if (check != 0) {
			model.addAttribute("message1", "Chỉnh sửa sinh viên thành công!");
			model.addAttribute("sinhVien", new SinhVienEntity());
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message0", "Chỉnh sửa sinh viên thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}
		PagedListHolder pagedListHolder = new PagedListHolder(this.getSinhVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/editsinhvien";
	}

	@RequestMapping(value = "editsinhvien/{maSV}.htm", params = "linkEdit")
	public String editSinhVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("sinhVien") SinhVienEntity sinhVien, @PathVariable("maSV") String maSV) {

		PagedListHolder pagedListHolder = new PagedListHolder(this.getSinhVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);

		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("sinhVien", this.get1SinhVien(maSV));
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/editsinhvien";
	}

	public int deleteSinhVien(SinhVienEntity sv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(sv);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "/editsinhvien/{maSV}.htm", params = "linkDelete")
	public String deleteSinhVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("sinhVien") SinhVienEntity sinhVien, @PathVariable("maSV") String maSV) {

		int check = this.deleteSinhVien(sinhVien);
		if (check != 0) {
			model.addAttribute("message1", "Xóa sinh viên thành công");
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message0", "Xóa sinh viên thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getSinhVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/editsinhvien";
	}

	public List<SinhVienEntity> searchSinhVien(String ten) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVienEntity where ten LIKE :ten";
		Query query = session.createQuery(hql);
		query.setParameter("ten", "%" + ten + "%");
		List<SinhVienEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "editsinhvien", params = "btnsearch")
	public String searchSinhVien(HttpServletRequest request, ModelMap model,
			@ModelAttribute("sinhVien") SinhVienEntity sinhVien) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.searchSinhVien(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "giangvien/menu/editsinhvien";
	}

	// --------THÊM / XÓA / SỬA CÂU HỎI--------------------------
	public List<CauHoiEntity> getCauHoi() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CauHoiEntity as ch order by ch.iDCH asc"; //
		Query query = session.createQuery(hql);
		List<CauHoiEntity> list = query.list();
		return list;
	}

	@RequestMapping("editcauhoi")
	public String editCauHoi(HttpServletRequest request, ModelMap model,
			@ModelAttribute("cauHoi") CauHoiEntity cauHoi) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());

		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/editcauhoi";
	}

	@ModelAttribute("monHoc")
	public List<MonHocEntity> getMonHoc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM MonHocEntity";
		Query query = session.createQuery(hql);
		List<MonHocEntity> list = query.list();
		return list;
	}

	@ModelAttribute("dapAn")
	public List<String> dapan() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		return list;
	}

	public List<CauHoiEntity> searchCauHoi(String maMon) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CauHoiEntity where monHoc_CH.maMonHoc = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maMon);
		List<CauHoiEntity> list = query.list();
		return list;
	}

	@RequestMapping(value = "editcauhoi", params = "btnSearch")
	public String searchCauHoi(HttpServletRequest request, ModelMap model,
			@ModelAttribute("cauHoi2") CauHoiEntity cauHoi2) {
		String ma = cauHoi2.getMonHoc_CH().getMaMonHoc();
		model.addAttribute("cHoi", this.searchCauHoi(ma));

		model.addAttribute("cauHoi", new CauHoiEntity());
		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/editcauhoi2";
	}

	@RequestMapping(value = "editcauhoi", params = "btnXemTatCa")
	public String xemTatCa(HttpServletRequest request, ModelMap model, @ModelAttribute("cauHoi") CauHoiEntity cauHoi) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());

		model.addAttribute("btnStatus", "btnAdd");

		return "giangvien/menu/editcauhoi";
	}

	public int insertCauHoi(CauHoiEntity ch) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ch);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "editcauhoi", params = "btnAdd")
	public String addCauHoi(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("cauHoi") CauHoiEntity cauHoi, BindingResult errors) {
		cauHoi.setGiangVien_CH(GV);
		int check = this.insertCauHoi(cauHoi);
		if (check != 0) {
			model.addAttribute("message1", "Thêm câu hỏi thành công!");
			model.addAttribute("cauHoi", new CauHoiEntity());
		} else {
			model.addAttribute("message0", "Thêm câu hỏi thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());
		model.addAttribute("btnStatus", "btnAdd");
		return "giangvien/menu/editcauhoi";
	}

	public int deleteCauHoi(CauHoiEntity ch) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(ch);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "/editcauhoi/{iDCH}.htm", params = "linkDelete")
	public String deleteCauHoi(HttpServletRequest request, ModelMap model,
			@ModelAttribute("cauHoi") CauHoiEntity cauHoi, @PathVariable("iDCH") String iDCH) {

		int check = this.deleteCauHoi(cauHoi);
		if (check != 0) {
			model.addAttribute("message1", "Xóa câu hỏi thành công");
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message0", "Xóa câu hỏi thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());

		return "giangvien/menu/editcauhoi";
	}

	public CauHoiEntity get1CauHoi(int iDCH) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CauHoiEntity where iDCH = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", iDCH);
		CauHoiEntity list = (CauHoiEntity) query.list().get(0);

		return list;
	}

	public int updateCauHoi(CauHoiEntity ch) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ch);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "editcauhoi", params = "btnEdit")
	public String edit_CauHoi(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("cauHoi") CauHoiEntity cauHoi, BindingResult errors) {
		cauHoi.setiDCH(id);
		cauHoi.setGiangVien_CH(GV);
		int check = this.updateCauHoi(cauHoi);
		if (check != 0) {
			model.addAttribute("message1", "Chỉnh sửa câu hỏi thành công!");
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("cauHoi", new CauHoiEntity());
		} else {
			model.addAttribute("message0", "Chỉnh sửa câu hỏi thất bại!");
			model.addAttribute("btnStatus", "btnEdit");
		}
		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());

		return "giangvien/menu/editcauhoi";
	}

	@RequestMapping(value = "editcauhoi/{iDCH}.htm", params = "linkEdit")
	public String editCauHoi(HttpServletRequest request, ModelMap model, @ModelAttribute("cauHoi") CauHoiEntity cauHoi,
			@PathVariable("iDCH") String iDCH) {

		PagedListHolder pagedListHolder = new PagedListHolder(this.getCauHoi());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(7);

		model.addAttribute("btnStatus", "btnEdit");
		int idch = Integer.parseInt(iDCH);
		id = idch;
		model.addAttribute("cauHoi", this.get1CauHoi(idch));
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("cauHoi2", new CauHoiEntity());

		return "giangvien/menu/editcauhoi";
	}

	// ---------------MÔN HỌC-----------------------------------------
	public List<MonHocEntity> getDSMonHoc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM MonHocEntity";
		Query query = session.createQuery(hql);
		List<MonHocEntity> list = query.list();
		return list;
	}

	@RequestMapping("editmonhoc")
	public String monHoc(ModelMap model) {
		List<MonHocEntity> DSMonHoc = this.getDSMonHoc();
		MonHocEntity mh = new MonHocEntity();
		model.addAttribute("mh", mh);
		model.addAttribute("mhoc", DSMonHoc);
		model.addAttribute("btnStatus", "btnAdd");
		return "giangvien/menu/editmonhoc";
	}

	public int insertMonHoc(MonHocEntity mh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(mh);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "editmonhoc", params = "btnAdd")
	public String addMonHoc(ModelMap model, @Validated @ModelAttribute("mh") MonHocEntity mh, BindingResult errors) {
		int check = this.insertMonHoc(mh);
		if (check != 0) {
			model.addAttribute("message1", "Thêm môn học thành công!");
		} else {
			model.addAttribute("message0", "Thêm môn học thất bại!");
		}

		model.addAttribute("mhoc", this.getDSMonHoc());
		model.addAttribute("btnStatus", "btnAdd");
		return "giangvien/menu/editmonhoc";
	}

	public int deleteMonHoc(MonHocEntity mh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(mh);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "/editmonhoc/{maMonHoc}.htm", params = "linkDelete")
	public String deleteMonHoc(ModelMap model, @ModelAttribute("mh") MonHocEntity mh,
			@PathVariable("maMonHoc") String maMonHoc) {

		int check = this.deleteMonHoc(mh);
		if (check != 0) {
			model.addAttribute("message1", "Xóa môn học thành công");
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message0", "Xóa môn học thất bại!");
		}

		model.addAttribute("mhoc", this.getDSMonHoc());

		return "giangvien/menu/editmonhoc";
	}

	// --------CHI TIẾT THI----------------------------------------------------

	public List<ChiTietThiEntity> layDSDiem(MonHocEntity mh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietThiEntity WHERE monHoc_CTT.maMonHoc = :ma order by diem desc";
		Query query = session.createQuery(hql);
		query.setParameter("ma", mh.getMaMonHoc());
		List<ChiTietThiEntity> list = query.list();
		return list;
	}

	public List<MonHocEntity> get1MonHoc(String maMH) {
		Session session = factory.getCurrentSession();
		String hql = "FROM MonHocEntity WHERE maMonHoc = :ma";
		Query query = session.createQuery(hql);
		query.setParameter("ma", maMH);
		List<MonHocEntity> list = query.list();
		return list;
	}

	@RequestMapping("chitietthi")
	public String CTT(ModelMap model) {
		model.addAttribute("MHOC", new MonHocEntity());
		return "giangvien/menu/chitietthi";
	}

	@RequestMapping("chitietthi2")
	public String CTT2(HttpServletRequest request, ModelMap model, @ModelAttribute("MHOC") MonHocEntity mh) {
		List<ChiTietThiEntity> ds = new ArrayList();
		if(mh.getMaMonHoc() != null) {
			ds = layDSDiem(mh);
			monhoc = mh;
		}
		else ds = layDSDiem(monhoc);
		
		if (ds.size() <= 0) {
			model.addAttribute("message", "Chưa có sinh viên nào thi môn này!");
			return "giangvien/menu/chitietthi";
		} else {
			PagedListHolder pagedListHolder = new PagedListHolder(ds);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("mon", this.get1MonHoc(monhoc.getMaMonHoc()).get(0).getTenMonHoc());
			return "giangvien/menu/chitietthi2";
		}

	} 

	// -----------------------ĐĂNG XUẤT----------------------------------------
	@RequestMapping("dangxuat")
	public String dangXuat(ModelMap model, HttpSession ss) {
		GV = new GiangVienEntity();
		monhoc = new MonHocEntity();
		GiangVienEntity g = new GiangVienEntity();
		model.addAttribute("giangVien", g);
		ss.setAttribute("giangVien", g.getMaGV());
		return "giangvien/login/login";
	}
}

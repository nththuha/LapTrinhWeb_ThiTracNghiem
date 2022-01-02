package ptithcm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.expr.NewArray;
import ptithcm.bean.Mailer;
import ptithcm.entity.CauHoiEntity;
import ptithcm.entity.ChiTietThiEntity;
import ptithcm.entity.GiangVienEntity;
import ptithcm.entity.MonHocEntity;
import ptithcm.entity.SinhVienEntity;

@Transactional
@Controller
@RequestMapping("sinhvien")
public class SinhVienController {
	int demCau = -1;
	int soluong = 10;
	int diem = 0;
	List<CauHoiEntity> DS;
	SinhVienEntity SV = new SinhVienEntity();
	MonHocEntity MH;
	@Autowired
	SessionFactory factory;

	@RequestMapping("")
	public String login(ModelMap model) {
		model.addAttribute("sinhVien", new SinhVienEntity());
		return "sinhvien/login/login";
	}

	@RequestMapping("dangnhap")
	public String quanLy(ModelMap model, @ModelAttribute("sinhVien") SinhVienEntity sv, HttpSession ss) {
		if (SV.getMaSV() == null) {
			String maSV = sv.getMaSV();
			String matKhau = sv.getMatKhau();

			Session session = factory.getCurrentSession();
			String hql = "FROM SinhVienEntity WHERE MASV = :maSV AND MATKHAU = :matKhau";
			Query query = session.createQuery(hql);
			query.setParameter("maSV", maSV);
			query.setParameter("matKhau", matKhau);
			List<SinhVienEntity> list = query.list();
			if (list.size() == 0) {
				model.addAttribute("sinhVien", new SinhVienEntity());
				model.addAttribute("message", "Đăng nhập thất bại!");
				return "sinhvien/login/login2";
			}
			SV = list.get(0);
			ss.setAttribute("sinhVien", SV.getMaSV());
			model.addAttribute("tensinhvien", SV.getHo() + " " + SV.getTen() + "!");
			MonHocEntity monHoc = new MonHocEntity();
			model.addAttribute("mh", monHoc);
			demCau = -1;
			return "sinhvien/menu/blank";
		} else {
			ss.setAttribute("sinhVien", SV.getMaSV());
			model.addAttribute("tensinhvien", SV.getHo() + " " + SV.getTen() + "!");
			MonHocEntity monHoc = new MonHocEntity();
			model.addAttribute("mh", monHoc);
			demCau = -1;
			return "sinhvien/menu/blank";
		}
	}

	public String taoMatKhau() {
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return value + "";
	}

	@RequestMapping("matkhau")
	public String matKhau() {
		return "sinhvien/login/password";
	}

	@Autowired
	Mailer mailer;

	@RequestMapping("datlai")
	public String datLaiMK(HttpServletRequest rq, ModelMap model) {
		String email = rq.getParameter("email");
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVienEntity WHERE EMAIL = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List<SinhVienEntity> list = query.list();
		// session.close();
		String mk = "";
		if (list.size() == 0) {
			model.addAttribute("message", "Email không tồn tại trong CSDL!");
		} else {
			// Cập nhật mật khẩu trong CSDL
			mk = taoMatKhau();
			Session session2 = factory.openSession();
			Transaction t = session2.beginTransaction();
			try {
				SinhVienEntity sv = new SinhVienEntity(list.get(0).getMaSV(), list.get(0).getHo(), list.get(0).getTen(),
						email, mk);
				session2.update(sv);
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
		return "sinhvien/login/password";
	}

	@ModelAttribute("monHoc")
	public List<MonHocEntity> getMonHoc() {
		Session session = factory.getCurrentSession();
		String hql = "FROM MonHocEntity";
		Query query = session.createQuery(hql);
		List<MonHocEntity> list = query.list();
		return list;
	}

	public List<MonHocEntity> getMonHoc(MonHocEntity mh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM MonHocEntity WHERE maMonHoc = :mmh";
		Query query = session.createQuery(hql);
		query.setParameter("mmh", mh.getMaMonHoc());
		List<MonHocEntity> list = query.list();
		return list;
	}

	public void getCauHoi(MonHocEntity mh) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CauHoiEntity WHERE monHoc_CH.maMonHoc = :mmh ORDER BY NEWID()";
		Query query = session.createQuery(hql).setMaxResults(soluong);
		query.setParameter("mmh", mh.getMaMonHoc());
		DS = query.list();
	}

	public int getDiem() {
		int d = 0;
		for (int i = 0; i < soluong; i++) {
			if (DS.get(i).getLuaChon().equals("")) {
				DS.get(i).setLuaChon("H");
			} else if (DS.get(i).getLuaChon().equals(DS.get(i).getDapAn()))
				d++;
		}
		return d;
	}

	public void insertCTT(MonHocEntity mh) {
		ChiTietThiEntity ctt = new ChiTietThiEntity(new Date(), diem, SV, mh);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctt);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}

	@RequestMapping("thi")
	public String thi(ModelMap model, @ModelAttribute("mh") MonHocEntity mh,
			@ModelAttribute("cauHoi") CauHoiEntity ch) {
		if (demCau < 0) {
			DS = new ArrayList();
			MH = getMonHoc(mh).get(0);
			getCauHoi(MH);
			if (DS.size() < 10) {
				model.addAttribute("message", "Môn học này chưa nhập đủ câu hỏi để thi!");
				model.addAttribute("tensinhvien", SV.getHo() + " " + SV.getTen() + "!");
				DS = new ArrayList<>();
				return "sinhvien/menu/blank";
			}
			demCau = 0;
		}

		if (demCau >= 0 && demCau < soluong) {
			if (demCau > 0) {
				DS.get(demCau - 1).setLuaChon(ch.getLuaChon());
			}
			model.addAttribute("tenmonhoc", MH.getTenMonHoc());
			model.addAttribute("tt", demCau + 1);
			model.addAttribute("cauHoi", DS.get(demCau));
			demCau++;
			return "sinhvien/menu/thi";
		} else {
			DS.get(demCau - 1).setLuaChon(ch.getLuaChon());
			diem = getDiem();
			insertCTT(MH);
			model.addAttribute("diem", diem);

			for (int i = 0; i < soluong; i++) {
				model.addAttribute("nd" + i, DS.get(i).getNoiDung());

				if (!DS.get(i).getLuaChon().equals(DS.get(i).getDapAn()) && DS.get(i).getLuaChon().equals("A")) {
					model.addAttribute("a" + i + "s", DS.get(i).getA());
				} else if (DS.get(i).getDapAn().equals("A")) {
					model.addAttribute("a" + i + "d", DS.get(i).getA());
				} else
					model.addAttribute("a" + i, DS.get(i).getA());

				if (!DS.get(i).getLuaChon().equals(DS.get(i).getDapAn()) && DS.get(i).getLuaChon().equals("B")) {
					model.addAttribute("b" + i + "s", DS.get(i).getB());
				} else if (DS.get(i).getDapAn().equals("B")) {
					model.addAttribute("b" + i + "d", DS.get(i).getB());
				} else
					model.addAttribute("b" + i, DS.get(i).getB());

				if (!DS.get(i).getLuaChon().equals(DS.get(i).getDapAn()) && DS.get(i).getLuaChon().equals("C")) {
					model.addAttribute("c" + i + "s", DS.get(i).getC());
				} else if (DS.get(i).getDapAn().equals("C")) {
					model.addAttribute("c" + i + "d", DS.get(i).getC());
				} else
					model.addAttribute("c" + i, DS.get(i).getC());

				if (!DS.get(i).getLuaChon().equals(DS.get(i).getDapAn()) && DS.get(i).getLuaChon().equals("D")) {
					model.addAttribute("d" + i + "s", DS.get(i).getD());
				} else if (DS.get(i).getDapAn().equals("D")) {
					model.addAttribute("d" + i + "d", DS.get(i).getD());
				} else
					model.addAttribute("d" + i, DS.get(i).getD());
			}
			return "sinhvien/menu/ketqua";
		}
	}

	@RequestMapping("dangxuat")
	public String dangXuat(ModelMap model, HttpSession ss) {
		SV = new SinhVienEntity();
		demCau = -1;
		MH = new MonHocEntity();
		diem = 0;
		SinhVienEntity s = new SinhVienEntity();
		model.addAttribute("sinhVien", s);
		ss.setAttribute("sinhVien", s.getMaSV());
		return "sinhvien/login/login2";
	}
}

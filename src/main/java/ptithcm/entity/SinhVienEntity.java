package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "SINHVIEN")
public class SinhVienEntity {
	@Id
	@Column(name = "MASV")
	@NotBlank(message = "Mã sinh viên không được để trống!")
	private String maSV;

	@Column(name = "HO")
	@NotBlank(message = "Họ không được để trống!")
	private String ho;

	@Column(name = "TEN")
	@NotBlank(message = "Tên không được để trống!")
	private String ten;

	@Column(name = "EMAIL")
	@NotBlank(message = "Email không được để trống!")
	private String email;

	@Column(name = "MATKHAU")
	@NotBlank(message = "Mật khẩu không được để trống!")
	private String matKhau;

	@OneToMany(mappedBy = "sinhVien_CTT", fetch = FetchType.EAGER)
	private Collection<ChiTietThiEntity> chiTietThi;

	public SinhVienEntity() {
		super();
	}

	public SinhVienEntity(String maSV, String ho, String ten, String email, String matKhau) {
		super();
		this.maSV = maSV;
		this.ho = ho;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
	}

	public SinhVienEntity(String maSV, String ho, String ten, String email, String matKhau, Collection<ChiTietThiEntity> chiTietThi) {
		super();
		this.maSV = maSV;
		this.ho = ho;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
		this.chiTietThi = chiTietThi;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Collection<ChiTietThiEntity> getChiTietThi() {
		return chiTietThi;
	}

	public void setChiTietThi(Collection<ChiTietThiEntity> chiTietThi) {
		this.chiTietThi = chiTietThi;
	}

}

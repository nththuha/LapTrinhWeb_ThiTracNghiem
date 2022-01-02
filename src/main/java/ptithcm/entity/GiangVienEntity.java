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
@Table(name = "GIANGVIEN")
public class GiangVienEntity {
	@Id
	@Column(name = "MAGV")
	@NotBlank(message = "Mã giảng viên không được để trống!")
	private String maGV;

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

	@OneToMany(mappedBy = "giangVien_CH", fetch = FetchType.EAGER)
	private Collection<CauHoiEntity> cauHoi;

	public GiangVienEntity() {
		super();
	}

	public GiangVienEntity(String maGV, String ho, String ten, String email, String matKhau) {
		super();
		this.maGV = maGV;
		this.ho = ho;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
	}


	public GiangVienEntity(String maGV, String ho, String ten, String email, String matKhau, Collection<CauHoiEntity> cauHoi) {
		super();
		this.maGV = maGV;
		this.ho = ho;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
		this.cauHoi = cauHoi;
	}

	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
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

	public Collection<CauHoiEntity> getCauHoi() {
		return cauHoi;
	}

	public void setCauHoi(Collection<CauHoiEntity> cauHoi) {
		this.cauHoi = cauHoi;
	}

}

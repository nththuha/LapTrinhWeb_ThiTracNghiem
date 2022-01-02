package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CAUHOI")
public class CauHoiEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDCH")
	private Integer iDCH;
	@Column(name = "NOIDUNG")
	@NotBlank(message = "Nội dung không được để trống!")
	private String noiDung;
	@Column(name = "DAPAN")
	private String dapAn;
	@Column(name = "A")
	@NotBlank(message = "Phương án A không được để trống!")
	private String a;
	@Column(name = "B")
	@NotBlank(message = "Phương án B không được để trống!")
	private String b;
	@Column(name = "C")
	@NotBlank(message = "Phương án C không được để trống!")
	private String c;
	@Column(name = "D")
	@NotBlank(message = "Phương án D không được để trống!")
	private String d;
	@Column(name = "LUACHON")
	private String luaChon = "";
	@ManyToOne
	@JoinColumn(name = "MAGV")
	private GiangVienEntity giangVien_CH;
	@ManyToOne
	@JoinColumn(name = "MAMH")
	private MonHocEntity monHoc_CH;

	public CauHoiEntity() {
		super();
	}

	public CauHoiEntity(Integer iDCH, String noiDung, String dapAn, String a, String b, String c, String d,
			GiangVienEntity giangVien_CH, MonHocEntity monHoc_CH) {
		super();
		this.iDCH = iDCH;
		this.noiDung = noiDung;
		this.dapAn = dapAn;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.giangVien_CH = giangVien_CH;
		this.monHoc_CH = monHoc_CH;
	}

	public Integer getiDCH() {
		return iDCH;
	}

	public void setiDCH(Integer iDCH) {
		this.iDCH = iDCH;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getDapAn() {
		return dapAn;
	}

	public void setDapAn(String dapAn) {
		this.dapAn = dapAn;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public GiangVienEntity getGiangVien_CH() {
		return giangVien_CH;
	}

	public void setGiangVien_CH(GiangVienEntity giangVien_CH) {
		this.giangVien_CH = giangVien_CH;
	}

	public MonHocEntity getMonHoc_CH() {
		return monHoc_CH;
	}

	public void setMonHoc_CH(MonHocEntity monHoc_CH) {
		this.monHoc_CH = monHoc_CH;
	}

	public String getLuaChon() {
		return luaChon;
	}

	public void setLuaChon(String luaChon) {
		this.luaChon = luaChon;
	}

}

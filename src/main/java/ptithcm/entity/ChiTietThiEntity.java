package ptithcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CHITIETTHI")
public class ChiTietThiEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDCTT")
	private Integer iDCTT;
	@Column(name = "NGAYTHI")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayThi;
	@Column(name = "DIEM")
	private Integer diem;
	@ManyToOne
	@JoinColumn(name = "MASV")
	private SinhVienEntity sinhVien_CTT;

	@ManyToOne
	@JoinColumn(name = "MAMH")
	private MonHocEntity monHoc_CTT;
	
	public ChiTietThiEntity() {
		super();
	}

	public ChiTietThiEntity(Date ngayThi, Integer diem, SinhVienEntity sinhVien_CTT,
			MonHocEntity monHoc_CTT) {
		super();
		this.ngayThi = ngayThi;
		this.diem = diem;
		this.sinhVien_CTT = sinhVien_CTT;
		this.monHoc_CTT = monHoc_CTT;
	}

	public Integer getiDCTT() {
		return iDCTT;
	}

	public void setiDCTT(Integer iDCTT) {
		this.iDCTT = iDCTT;
	}

	public Date getNgayThi() {
		return ngayThi;
	}

	public void setNgayThi(Date ngayThi) {
		this.ngayThi = ngayThi;
	}

	public Integer getDiem() {
		return diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	public SinhVienEntity getSinhVien_CTT() {
		return sinhVien_CTT;
	}

	public void setSinhVien_CTT(SinhVienEntity sinhVien_CTT) {
		this.sinhVien_CTT = sinhVien_CTT;
	}

	public MonHocEntity getMonHoc_CTT() {
		return monHoc_CTT;
	}

	public void setMonHoc_CTT(MonHocEntity monHoc_CTT) {
		this.monHoc_CTT = monHoc_CTT;
	}
	
}

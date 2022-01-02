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
@Table(name = "MONHOC")
public class MonHocEntity {
	@Id
	@Column(name = "MAMH")
	@NotBlank(message = "Mã môn học không được để trống!")
	private String maMonHoc;
	@Column(name = "TENMH")
	@NotBlank(message = "Tên môn học không được để trống!")
	private String tenMonHoc;

	@OneToMany(mappedBy = "monHoc_CH", fetch = FetchType.EAGER)
	private Collection<CauHoiEntity> cauHoi;

	@OneToMany(mappedBy = "monHoc_CTT", fetch = FetchType.EAGER)
	private Collection<ChiTietThiEntity> chiTietThi;

	public MonHocEntity() {
		super();
	}

	public MonHocEntity(String maMonHoc, String tenMonHoc, Collection<CauHoiEntity> cauHoi,
			Collection<ChiTietThiEntity> chiTietThi) {
		super();
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.cauHoi = cauHoi;
		this.chiTietThi = chiTietThi;
	}

	public String getMaMonHoc() {
		return maMonHoc;
	}

	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}

	public String getTenMonHoc() {
		return tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}

	public Collection<CauHoiEntity> getCauHoi() {
		return cauHoi;
	}

	public void setCauHoi(Collection<CauHoiEntity> cauHoi) {
		this.cauHoi = cauHoi;
	}

	public Collection<ChiTietThiEntity> getChiTietThi() {
		return chiTietThi;
	}

	public void setChiTietThi(Collection<ChiTietThiEntity> chiTietThi) {
		this.chiTietThi = chiTietThi;
	}
	
}
